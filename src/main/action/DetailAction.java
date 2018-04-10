package main.action;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
import org.apache.struts.actions.DispatchAction;

import ibatis.dto.Stock;
import main.form.DetailForm;

public class DetailAction extends DispatchAction {
	//デフォルト
	public ActionForward unspecified(ActionMapping mapping,
			ActionForm form,
			HttpServletRequest req,
			HttpServletResponse res) {

		DetailForm detailForm = (DetailForm)form;

		List<Stock> stockList = new ArrayList<Stock>();

		for(int i=0;i<10;i++) {
			stockList.add(new Stock("ドラゴンボール",String.valueOf(i+1),1));
		}
		for(int i=10;i<25;i++) {
			stockList.add(new Stock("ドラゴンボール",String.valueOf(i+1),0));
		}

		detailForm.setStockList(stockList);
		detailForm.setTitle(req.getParameter("title"));

		return (mapping.findForward("detail"));
	}

	//削除
		public ActionForward delete(ActionMapping mapping,
				ActionForm form,
				HttpServletRequest req,
				HttpServletResponse res) {

			DetailForm detailForm = (DetailForm)form;

			List<Stock> stockList1 = new ArrayList<Stock>();

			for(int i=0;i<10;i++) {
				stockList1.add(new Stock("ドラゴンボール",String.valueOf(i+1),1));
			}
			for(int i=10;i<25;i++) {
				stockList1.add(new Stock("ドラゴンボール",String.valueOf(i+1),0));
			}

			detailForm.setStockList(stockList1);


			String result = "success";
			ActionMessages errors = new ActionMessages();

			if(req.getParameter("selectList").isEmpty()) {
				errors.add("delete",new ActionMessage("errors.required.select","削除対象"));
				result = "error";
			}

			//削除処理

			detailForm.setTitle(req.getParameter("title"));
			saveErrors(req, errors);

			return (mapping.findForward(result));
		}

	//貸出返却
		public ActionForward lend(ActionMapping mapping,
				ActionForm form,
				HttpServletRequest req,
				HttpServletResponse res) {

			DetailForm detailForm = (DetailForm)form;

			List<Stock> stockList1 = new ArrayList<Stock>();

			for(int i=0;i<10;i++) {
				stockList1.add(new Stock("ドラゴンボール",String.valueOf(i+1),1));
			}
			for(int i=10;i<25;i++) {
				stockList1.add(new Stock("ドラゴンボール",String.valueOf(i+1),0));
			}

			detailForm.setStockList(stockList1);

			String result = "success";
			ActionMessages errors = new ActionMessages();

			if(req.getParameter("selectList").isEmpty()) {
				errors.add("delete",new ActionMessage("errors.required.select","貸出/返却対象"));
				result = "error";
			}

			if(result != "error") {
				List<Stock> stockList = detailForm.getStockList();
				List<String> selectList = Arrays.asList(req.getParameter("selectList").split(","));

				int nowStatus = 0;
				for(int i=0; i<selectList.size(); i++) {
					int status = stockList.get(Integer.parseInt(selectList.get(i))).getStatus();
					if(i == 0) {
						nowStatus = status;
					}
					if(nowStatus != status) {
						errors.add("lend",new ActionMessage("errors.lend.select"));
						result = "error";
						break;
					}
				}
			}

			//貸出返却処理

			saveErrors(req, errors);

			return (mapping.findForward(result));
		}
}
