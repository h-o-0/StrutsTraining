package main.action;

import java.sql.SQLException;

import javax.naming.NamingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;

import com.ibatis.sqlmap.client.SqlMapClient;

import ibatis.MyAppSqlConfig;
import ibatis.dto.User;

public class LoginAction extends Action {
	public ActionForward execute(ActionMapping mapping,
			ActionForm form,
			HttpServletRequest req,
			HttpServletResponse res) throws NamingException, SQLException {

		String result = "success";

		ActionMessages errors = new ActionMessages();

		// ibatis
		SqlMapClient sqlMap = MyAppSqlConfig.getSqlMapInstance();

		User user = (User) sqlMap.queryForObject("getUser", req.getParameter("userId"));

		if(req.getParameter("userId").isEmpty()) {
			errors.add("userId",new ActionMessage("errors.required","ユーザID"));
			result = "error";
		}

		if(req.getParameter("password").isEmpty()) {
			errors.add("password",new ActionMessage("errors.required","パスワード"));
			result = "error";
		}

		if(result != "error" && user.getUserid() == null) {
			errors.add("userId",new ActionMessage("errors.notRegistrerd","ユーザID"));
			result = "error";
		} else if (result != "error" && !user.getPassward().equals(req.getParameter("password"))) {
			errors.add("password",new ActionMessage("errors.invalid","パスワード"));
			result = "error";
		}

		if(result != "error") {
			HttpSession session = req.getSession();

			session.invalidate();
			session = req.getSession(true);

			session.setAttribute("admin", user.getAdmin());
		}

		saveErrors(req, errors);

		return (mapping.findForward(result));
	}
}