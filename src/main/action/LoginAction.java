package main.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;

public class LoginAction extends Action {
	public ActionForward execute(ActionMapping mapping,
			ActionForm form,
			HttpServletRequest req,
			HttpServletResponse res) {

		String result = "success";

		ActionMessages errors = new ActionMessages();

		if(req.getParameter("userId").isEmpty()) {
			errors.add("userId",new ActionMessage("errors.required","ユーザID"));
			result = "error";
		}
		if(req.getParameter("password").isEmpty()) {
			errors.add("password",new ActionMessage("errors.required","パスワード"));
			result = "error";
		}

		saveErrors(req, errors);
		return (mapping.findForward(result));
	}
}