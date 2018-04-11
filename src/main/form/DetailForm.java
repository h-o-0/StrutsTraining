package main.form;

import java.util.ArrayList;
import java.util.List;

import org.apache.struts.action.ActionForm;

import ibatis.dto.Library;
import ibatis.dto.Stock;

public class DetailForm extends ActionForm {
	private List<Stock> stockList = new ArrayList<Stock>();
	private String selectList;
	private String id;
	private Library library;

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

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Library getLibrary() {
		return library;
	}

	public void setLibrary(Library library) {
		this.library = library;
	}

}
