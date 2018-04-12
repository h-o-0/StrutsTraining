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
		List<Stock> stockList = (List<Stock>)sqlMap.queryForList("getStockDataEachTitle", Integer.parseInt(req.getParameter("id")));

		detailForm.setStockList(stockList);

		return (mapping.findForward("detail"));
	}

	//削除
	public ActionForward delete(ActionMapping mapping,
			ActionForm form,
			HttpServletRequest req,
			HttpServletResponse res) throws SQLException {

		DetailForm detailForm = (DetailForm)form;

		// 押下時にDBアクセス (表示データが最新とは限らない為)
		SqlMapClient sqlMap = MyAppSqlConfig.getSqlMapInstance();

		Library library =  (Library)sqlMap.queryForObject("getLibrary",Integer.parseInt(req.getParameter("id")));

		detailForm.setLibrary(library);

		@SuppressWarnings("unchecked")
		List<Stock> stockList = (List<Stock>)sqlMap.queryForList("getStockDataEachTitle", Integer.parseInt(req.getParameter("id")));
		Map<String,Stock> volumeMap = convertMap(stockList);

		String result = "success";
		ActionMessages errors = new ActionMessages();

		if(req.getParameter("selectList").isEmpty()) {
			errors.add("delete",new ActionMessage("errors.required.select","削除対象"));
			result = "error";
		}

		//削除処理
		// TODO ポップアップの処理に記載予定
		if(result != "error") {
			for(String selectNo : Arrays.asList(req.getParameter("selectList").split(","))) {
				sqlMap.delete("deleteStock", volumeMap.get(selectNo));
			}


		}

		@SuppressWarnings("unchecked")
		List<Stock> updateList = (List<Stock>)sqlMap.queryForList("getStockDataEachTitle", Integer.parseInt(req.getParameter("id")));

		detailForm.setStockList(updateList);

		saveErrors(req, errors);

		return (mapping.findForward(result));
	}

	//貸出返却
	public ActionForward validate(ActionMapping mapping,
			ActionForm form,
			HttpServletRequest req,
			HttpServletResponse res) throws SQLException {

		DetailForm detailForm = (DetailForm)form;

		// 押下時にDBアクセス (表示データが最新とは限らない為)
		SqlMapClient sqlMap = MyAppSqlConfig.getSqlMapInstance();

		Library library =  (Library)sqlMap.queryForObject("getLibrary", Integer.parseInt(req.getParameter("id")));

		detailForm.setLibrary(library);

		@SuppressWarnings("unchecked")
		List<Stock> stockList = (List<Stock>)sqlMap.queryForList("getStockDataEachTitle", Integer.parseInt(req.getParameter("id")));

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
				req.setAttribute("noError","true");
			}else {
				req.setAttribute("noError","false");
			}
		}

		if(result != "error") {
			result = "detail";
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
		List<Stock> stockList = (List<Stock>)sqlMap.queryForList("getStockDataEachTitle", Integer.parseInt(req.getParameter("id")));

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

			// 返却
			if(nowStatus == 0 && result != "error") {
				for(String selectNo : selectList) {
					sqlMap.update("updateStatusLendable", volumeMap.get(selectNo));
				}
			}

			// 貸出
			if(nowStatus == 1 && result != "error") {
				for(String selectNo : selectList) {

					Stock update = volumeMap.get(selectNo);
					update.setLoan_comment(req.getParameter("loan_comment"));
					sqlMap.update("updateStatusOnLoan", update);
				}
			}

		}

		@SuppressWarnings("unchecked")
		List<Stock> updateList = (List<Stock>)sqlMap.queryForList("getStockDataEachTitle", Integer.parseInt(req.getParameter("id")));

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
			map.put(stockData.getVolume(), stockData);

		}

		return map;
	}

}
