package main.action;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
import org.apache.struts.actions.DispatchAction;

import com.ibatis.sqlmap.client.SqlMapClient;

import ibatis.MyAppSqlConfig;
import ibatis.dto.Library;
import ibatis.dto.Stock;
import main.form.DetailForm;

public class DetailAction extends DispatchAction {


	//デフォルト
	public ActionForward unspecified(ActionMapping mapping,
			ActionForm form,
			HttpServletRequest req,
			HttpServletResponse res) throws SQLException {

		DetailForm detailForm = (DetailForm)form;

		SqlMapClient sqlMap = MyAppSqlConfig.getSqlMapInstance();

		Library library =  (Library)sqlMap.queryForObject("getLibrary", Integer.parseInt(req.getParameter("id")));

		detailForm.setLibrary(library);

		@SuppressWarnings("unchecked")
		List<Stock> stockList = (List<Stock>) sqlMap.queryForList("getStockDataEachTitle",
				Integer.parseInt(req.getParameter("id")));

		detailForm.setStockList(stockList);

		return (mapping.findForward("success"));
	}

	//削除チェック
	public ActionForward deleteCheck(ActionMapping mapping,
			ActionForm form,
			HttpServletRequest req,
			HttpServletResponse res) throws SQLException {

		DetailForm detailForm = (DetailForm) form;

		// 押下時にDBアクセス (表示データが最新とは限らない為)
		SqlMapClient sqlMap = MyAppSqlConfig.getSqlMapInstance();

		Library library =  (Library)sqlMap.queryForObject("getLibrary",Integer.parseInt(req.getParameter("id")));

		detailForm.setLibrary(library);

		String result = "success";
		ActionMessages errors = new ActionMessages();

		if (req.getParameter("selectList").isEmpty()) {
			errors.add("delete", new ActionMessage("errors.required.select", "削除対象"));
			result = "error";
		}

		@SuppressWarnings("unchecked")
		List<Stock> updateList = (List<Stock>) sqlMap.queryForList("getStockDataEachTitle",
				Integer.parseInt(req.getParameter("id")));

		detailForm.setStockList(updateList);

		if (result != "error") {
			req.setAttribute("deleteCheck", "true");
		} else {
			req.setAttribute("deleteCheck", "false");
		}

		saveErrors(req, errors);

		return (mapping.findForward(result));
	}


	//削除
	public ActionForward delete(ActionMapping mapping,
			ActionForm form,
			HttpServletRequest req,
			HttpServletResponse res) throws NumberFormatException, SQLException  {

		DetailForm detailForm = (DetailForm)form;

		// 押下時にDBアクセス (表示データが最新とは限らない為)
		SqlMapClient sqlMap = MyAppSqlConfig.getSqlMapInstance();

		Library library =  (Library)sqlMap.queryForObject("getLibrary",Integer.parseInt(req.getParameter("id")));

		detailForm.setLibrary(library);

		@SuppressWarnings("unchecked")
		List<Stock> stockList = (List<Stock>) sqlMap.queryForList("getStockDataEachTitle",
				Integer.parseInt(req.getParameter("id")));
		Map<String,Stock> volumeMap = convertMap(stockList);

		String result = "success";
		ActionMessages errors = new ActionMessages();

		//削除処理
		if(result != "error") {
			for(String selectNo : Arrays.asList(req.getParameter("selectList").split(","))) {
				try {
					sqlMap.delete("deleteStock", volumeMap.get(selectNo));
				} catch (SQLException e) {
					result = "error";
				}
			}
		}

		if (result.equals("success")) {
			req.setAttribute("registComplete", "true");
		} else {
			req.setAttribute("registComplete", "false");
		}

		@SuppressWarnings("unchecked")
		List<Stock> updateList = (List<Stock>) sqlMap.queryForList("getStockDataEachTitle",
				Integer.parseInt(req.getParameter("id")));

		if(updateList.size() == 0) {
			try {
			sqlMap.delete("deleteLibrary", library.getId());
			} catch (SQLException e) {
				result = "error";
			}
		}

		detailForm.setStockList(updateList);

		saveErrors(req, errors);

		return (mapping.findForward(result));
	}

	//貸出返却チェック
	public ActionForward lendCheck(ActionMapping mapping,
			ActionForm form,
			HttpServletRequest req,
			HttpServletResponse res) throws SQLException {

		DetailForm detailForm = (DetailForm)form;

		// 押下時にDBアクセス (表示データが最新とは限らない為)
		SqlMapClient sqlMap = MyAppSqlConfig.getSqlMapInstance();

		Library library =  (Library)sqlMap.queryForObject("getLibrary", Integer.parseInt(req.getParameter("id")));

		detailForm.setLibrary(library);

		@SuppressWarnings("unchecked")
		List<Stock> stockList = (List<Stock>) sqlMap.queryForList("getStockDataEachTitle",
				Integer.parseInt(req.getParameter("id")));

		String result = "success";
		ActionMessages errors = new ActionMessages();

		// バリデーション
		if(req.getParameter("selectList").isEmpty()) {
			errors.add("delete",new ActionMessage("errors.required.select","貸出/返却対象"));
			result = "error";
		}

		if(result != "error") {
			List<String> selectList = Arrays.asList(req.getParameter("selectList").split(","));

			Map<String,Stock> volumeMap = convertMap(stockList);

			Integer nowStatus = null;
			for(String selectNo : selectList) {
				int status = volumeMap.get(selectNo).getStatus();
				if(nowStatus == null) {
					nowStatus = status;
				}
				if(nowStatus != status) {
					errors.add("lend",new ActionMessage("errors.lend.select"));
					result = "error";
					break;
				}
			}

			if(result != "error") {
				req.setAttribute("status",nowStatus);
				req.setAttribute("lendCheck", "true");
			}else {
				req.setAttribute("lendCheck", "false");
			}
		}

		if(result != "error") {
			result = "success";
		}

		detailForm.setStockList(stockList);

		saveErrors(req, errors);

		return (mapping.findForward(result));
	}

	//貸出返却
	public ActionForward lend(ActionMapping mapping,
			ActionForm form,
			HttpServletRequest req,
			HttpServletResponse res) throws SQLException {

		DetailForm detailForm = (DetailForm)form;

		// 押下時にDBアクセス (表示データが最新とは限らない為)
		SqlMapClient sqlMap = MyAppSqlConfig.getSqlMapInstance();

		Library library =  (Library)sqlMap.queryForObject("getLibrary", Integer.parseInt(req.getParameter("id")));

		detailForm.setLibrary(library);

		@SuppressWarnings("unchecked")
		List<Stock> stockList = (List<Stock>) sqlMap.queryForList("getStockDataEachTitle",
				Integer.parseInt(req.getParameter("id")));

		String result = "success";
		ActionMessages errors = new ActionMessages();

		List<String> selectList = Arrays.asList(req.getParameter("selectList").split(","));

		Map<String,Stock> volumeMap = convertMap(stockList);;

		int status = volumeMap.get(selectList.get(0)).getStatus();

		// 返却
		if(status == 0 && result != "error") {
			for(String selectNo : selectList) {
				try {
					sqlMap.update("updateStatusLendable", volumeMap.get(selectNo));
				} catch (SQLException e) {
					result = "error";
				}
			}
		}

		// 貸出
		if(status == 1 && result != "error") {
			try {
				for(String selectNo : selectList) {
					Stock update = volumeMap.get(selectNo);
					update.setLoan_comment(req.getParameter("loan_comment"));
					sqlMap.update("updateStatusOnLoan", update);
				}
			} catch (SQLException e) {
				result = "error";
			}
		}

		if (result.equals("success")) {
			req.setAttribute("registComplete", "true");
		} else {
			req.setAttribute("registComplete", "false");
		}

		@SuppressWarnings("unchecked")
		List<Stock> updateList = (List<Stock>) sqlMap.queryForList("getStockDataEachTitle",
				Integer.parseInt(req.getParameter("id")));

		detailForm.setStockList(updateList);

		saveErrors(req, errors);

		return (mapping.findForward(result));
	}


	/**
	 * Stockのリストをkeyが巻数のMapに変換します
	 * @param list Stockのリスト
	 * @return 変換後のmap
	 */
	private Map<String,Stock> convertMap(List<Stock> list) {
		Map<String,Stock> map = new HashMap<String,Stock>();

		for(Stock stockData : list) {
			map.put(String.valueOf(stockData.getId()) , stockData);

		}

		return map;
	}

}
