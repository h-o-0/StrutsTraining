package main.action;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
import org.apache.struts.actions.DispatchAction;

import logic.DBOperationLogic;

public class DeleteAction extends DispatchAction {

	//削除チェック
	public ActionForward deleteCheck(ActionMapping mapping,
			ActionForm form,
			HttpServletRequest req,
			HttpServletResponse res) throws SQLException {

		String result = "success";
		ActionMessages errors = new ActionMessages();

		if (req.getParameter("id").isEmpty()) {
			errors.add("delete", new ActionMessage("errors.required.select", "削除対象"));
			result = "error";
		}

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
			HttpServletResponse res) throws SQLException {

		String result = "success";
		ActionMessages errors = new ActionMessages();

		//削除処理
		try {
			DBOperationLogic.deleteLibrary(Integer.parseInt(req.getParameter("id")));
		} catch (SQLException e) {
			result = "error";
		}

		if (result.equals("success")) {
			req.setAttribute("registComplete", "true");
		} else {
			req.setAttribute("registComplete", "false");
		}

		saveErrors(req, errors);

		return (mapping.findForward(result));
	}
}
