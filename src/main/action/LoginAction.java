package main.action;

import java.sql.SQLException;

import javax.naming.NamingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;

import data.UserData;
import logic.DBDataGetter;

public class LoginAction extends Action {
	public ActionForward execute(ActionMapping mapping,
			ActionForm form,
			HttpServletRequest req,
			HttpServletResponse res) throws NamingException, SQLException {

		String result = "success";

		ActionMessages errors = new ActionMessages();

		DBDataGetter getter = new DBDataGetter();

		UserData dbData = getter.getUserData(req.getParameter("userId"));

		if(req.getParameter("userId").isEmpty()) {
			errors.add("userId",new ActionMessage("errors.required","ユーザID"));
			result = "error";
		}

		if(req.getParameter("password").isEmpty()) {
			errors.add("password",new ActionMessage("errors.required","パスワード"));
			result = "error";
		}

		if(result != "error" && dbData.getUserid() == null) {
			errors.add("userId",new ActionMessage("errors.notRegistrerd","ユーザID"));
			result = "error";
		} else if (result != "error" && !dbData.getPassward().equals(req.getParameter("password"))) {
			errors.add("password",new ActionMessage("errors.invalid","パスワード"));
			result = "error";
		}

		saveErrors(req, errors);
		return (mapping.findForward(result));
	}
}