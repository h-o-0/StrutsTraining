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

		//TODO 登録処理：ポップアップが実装されたら移動
		if(result != "error") {
			Library addData = new Library(title, publisher, author);
			DBOperationLogic.addLibrary(addData);
		}


		return (mapping.findForward(result));
	}


	//登録処理(INSERT)
	public ActionForward insertRegist(ActionMapping mapping,
			ActionForm form,
			HttpServletRequest req,
			HttpServletResponse res) {

		String result = "success";

		//登録処理

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

		if(result.equals("success")) {
			req.setAttribute("registComplete", "true");
		}else {
			req.setAttribute("registComplete", "false");
		}

		return (mapping.findForward(result));
	}
}
