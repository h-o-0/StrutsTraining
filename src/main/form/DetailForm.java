package main.form;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import ibatis.dto.Stock;

public class DetailForm {
	private List<Stock> stockList = new ArrayList<Stock>(Arrays.asList(new Stock("ドラゴンボール","1",1),new Stock("ドラゴンボール","2",1),new Stock("ドラゴンボール","3",0)));
	private String title = "ドラゴンボール";

	public List<Stock> getStockList() {
		return stockList;
	}

	public void setStockList(List<Stock> stockList) {
		this.stockList = stockList;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

}
