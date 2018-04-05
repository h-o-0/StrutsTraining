package main.action;

import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;

public class AddAction extends Action {
	public ActionForward execute(ActionMapping mapping,
			ActionForm form,
			HttpServletRequest req,
			HttpServletResponse res) {

		String result = "success";

		ActionMessages errors = new ActionMessages();

		String title = req.getParameter("title");
		String volume = req.getParameter("volume");
		String publisher = req.getParameter("publisher");
		String author = req.getParameter("author");

		if(title.isEmpty()) {
			errors.add("title",new ActionMessage("errors.required","タイトル"));
			result = "error";
		}
		if(volume.isEmpty()) {
			errors.add("volume",new ActionMessage("errors.required","巻"));
			result = "error";
		}else if(!volumeCheck(volume)) {
			errors.add("volume",new ActionMessage("errors.volume.format"));
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

		req.setAttribute("title", req.getParameter("title"));
		req.setAttribute("volume", req.getParameter("volume"));
		req.setAttribute("publisher", req.getParameter("publisher"));
		req.setAttribute("author", req.getParameter("author"));

		saveErrors(req, errors);
		return (mapping.findForward(result));
	}

	private boolean volumeCheck (String volume) {
		System.out.print(Pattern.compile("\\-").matcher(volume).find());
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
