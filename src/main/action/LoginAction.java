package main.action;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;


public class LoginAction extends Action {
	public ActionForward execute(ActionMapping mapping,
			ActionForm form,
			HttpServletRequest req,
			HttpServletResponse res) throws NamingException, SQLException {

		String result = "success";

		InitialContext context = new InitialContext();
		DataSource ds = (DataSource) context.lookup("java:comp/env/jdbc/myapp");

		Connection connection = ds.getConnection();

		//TODO 取得メソッド、ENUMは別に作成
		String sql = "SELECT * FROM pelibrary.userdata;";
		PreparedStatement statement = connection.prepareStatement(sql);

		ResultSet queryResult = statement.executeQuery();
		queryResult.next();

		 System.out.println(queryResult.getString("id"));
         System.out.println(queryResult.getString("passward"));

		//ActionMessages errors = new ActionMessages();
		if(req.getParameter("userId").isEmpty()) {
			//errors.add("userId",new ActionMessage("errors.required","ユーザID"));
			result = "error";
		}
		if(req.getParameter("password").isEmpty()) {
			//errors.add("password",new ActionMessage("errors.required","パスワード"));
			result = "error";
		}
		return (mapping.findForward(result));
	}
}