package main.action;

import java.sql.SQLException;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
import org.apache.struts.action.RedirectingActionForward;
import org.apache.struts.actions.DispatchAction;

import com.ibatis.sqlmap.client.SqlMapClient;

import ibatis.MyAppSqlConfig;
import ibatis.dto.Library;
import logic.DBOperationLogic;
import main.form.AddForm;

public class AddAction extends DispatchAction {
	//デフォルト
	public ActionForward unspecified(ActionMapping mapping,
			ActionForm form,
			HttpServletRequest req,
			HttpServletResponse res) throws SQLException {
		AddForm addForm = (AddForm)form;

		if(req.getParameter("id") != null) {
			SqlMapClient sqlMap = MyAppSqlConfig.getSqlMapInstance();

			Library library =  (Library)sqlMap.queryForObject("getLibrary", Integer.parseInt(req.getParameter("id")));

			addForm.setLibrary(library);

			addForm.setVolume(req.getParameter("volume"));
		}


		return (mapping.findForward("add"));
	}

	//入力チェック・登録
	public ActionForward regist(ActionMapping mapping,
			ActionForm form,
			HttpServletRequest req,
			HttpServletResponse res) throws SQLException {
		AddForm addForm = (AddForm)form;
		String result = "success";
		ActionMessages errors = new ActionMessages();

		SqlMapClient sqlMap = MyAppSqlConfig.getSqlMapInstance();

		Library library =  (Library)sqlMap.queryForObject("getLibrary", Integer.parseInt(req.getParameter("id")));

		addForm.setLibrary(library);

		String volume = req.getParameter("volume");

		if(volume.isEmpty()) {
			errors.add("volume",new ActionMessage("errors.required","巻"));
			result = "error";
		}else if(!volumeCheck(volume)) {
			errors.add("volume",new ActionMessage("errors.volume.format"));
			result = "error";
		}

		if(result.equals("success")) {
			try {
				DBOperationLogic.addStock(library.getId(), volume);
			} catch (SQLException e) {
				result = "error";
				saveErrors(req, errors);
			}
			return new RedirectingActionForward("/detail.do?id=" + req.getParameter("id"));
		}

		saveErrors(req, errors);

		return (mapping.findForward(result));
	}

	private boolean volumeCheck (String volume) {
		if(Pattern.compile("\\-").matcher(volume).find()) {
			String[] volumes = volume.split("-");
			for(int i=0;i<volumes.length;i++) {
				if(Pattern.compile("[^\\d]").matcher(volumes[i]).find()) {
					return false;
				}
			}
		}
		return true;
	}
}
