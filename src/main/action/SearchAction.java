package main.action;

import java.sql.SQLException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import ibatis.dto.Library;
import logic.DBOperationLogic;
import main.form.SearchForm;

public class SearchAction extends Action {
	//デフォルト
	public ActionForward execute(ActionMapping mapping,
			ActionForm form,
			HttpServletRequest req,
			HttpServletResponse res) throws SQLException {

		List<Library> dataList
				= DBOperationLogic.serchLiblary(
						req.getParameter("searchCategory"),
						req.getParameter("searchWord"));

		SearchForm f = (SearchForm)form;

		f.setLibraryList(dataList);

		return (mapping.findForward("success"));
	}

}
