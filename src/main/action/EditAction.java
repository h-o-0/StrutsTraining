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

import ibatis.dto.Library;
import logic.DBOperationLogic;

public class EditAction extends DispatchAction {
	//デフォルト
	public ActionForward unspecified(ActionMapping mapping,
			ActionForm form,
			HttpServletRequest req,
			HttpServletResponse res) {

		String id = req.getParameter("id");

		System.out.println(id);


		return (mapping.findForward("success"));
	}

	//画面内 登録ボタンクリック時：入力チェック
	public ActionForward validate(ActionMapping mapping,
			ActionForm form,
			HttpServletRequest req,
			HttpServletResponse res) throws SQLException {

		String result = "success";
		ActionMessages errors = new ActionMessages();

		String title = req.getParameter("title");
		String publisher = req.getParameter("publisher");
		String author = req.getParameter("author");

		if(title.isEmpty()) {
			errors.add("title",new ActionMessage("errors.required","タイトル"));
			result = "error";
		}
		if(publisher.isEmpty()) {
			errors.add("publisher",new ActionMessage("errors.required","出版社"));
			result = "error";
		}
		if(author.isEmpty()) {
			errors.add("author",new ActionMessage("errors.required","著者"));
			result = "error";
		}

		if(result.equals("success")) {
			req.setAttribute("noError","true");
		}else {
			req.setAttribute("noError","false");
		}

		saveErrors(req, errors);

		return (mapping.findForward(result));
	}


	//登録処理(INSERT)
	public ActionForward insertRegist(ActionMapping mapping,
			ActionForm form,
			HttpServletRequest req,
			HttpServletResponse res) {

		String result = "success";

		//登録処理
		Library addData
				= new Library(req.getParameter("title"), req.getParameter("publisher"), req.getParameter("author"));
		try {
			DBOperationLogic.addLibrary(addData);
		} catch (SQLException e) {
			result = "error";
		}

		if(result.equals("success")) {
			req.setAttribute("registComplete", "true");
		}else {
			req.setAttribute("registComplete", "false");
		}

		return (mapping.findForward(result));
	}

	//登録処理(UPDATE)
	public ActionForward updateRegist(ActionMapping mapping,
			ActionForm form,
			HttpServletRequest req,
			HttpServletResponse res) {

		String result = "success";

		//登録処理
		Library editData
			= new Library(req.getParameter("title"), req.getParameter("publisher"), req.getParameter("author"));

		editData.setId(Integer.parseInt(req.getParameter("id")));
		try {
			DBOperationLogic.editLibrary(editData);
		} catch (SQLException e) {
			result = "error";
		}

		if(result.equals("success")) {
			req.setAttribute("registComplete", "true");
		}else {
			req.setAttribute("registComplete", "false");
		}

		return (mapping.findForward(result));
	}
}
