package main.form;

import java.util.ArrayList;
import java.util.List;

import org.apache.struts.action.ActionForm;

import ibatis.dto.Stock;

public class DetailForm extends ActionForm {
	private List<Stock> stockList = new ArrayList<Stock>();
	private String selectList;
	private String title;

	public List<Stock> getStockList() {
		return stockList;
	}

	public void setStockList(List<Stock> stockList) {
		this.stockList = stockList;
	}

	public String getSelectList() {
		return selectList;
	}

	public void setSelectList(String selectList) {
		this.selectList = selectList;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

}
