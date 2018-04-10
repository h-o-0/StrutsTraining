package main.form;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.struts.action.ActionForm;

import ibatis.dto.Library;
import logic.DBOperationLogic;

public class SearchForm extends ActionForm {

	private static List<Library> libraryList = new ArrayList<Library>();
	private String searchCategory;
	private String searchWord;
	private boolean selectBook;

	static {
		try {
			libraryList = DBOperationLogic.serchLiblary(null, null);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public String getSearchCategory() {
		return searchCategory;
	}
	public void setSearchCategory(String searchCategory) {
		this.searchCategory = searchCategory;
	}
	public String getSearchWord() {
		return searchWord;
	}
	public void setSearchWord(String searchWord) {
		this.searchWord = searchWord;
	}
	public List<Library> getLibraryList() {
		return libraryList;
	}
	public void setLibraryList(List<Library> libraryList) {
		SearchForm.libraryList = libraryList;
	}
	public boolean isSelectBook() {
		return selectBook;
	}
	public void setSelectBook(boolean selectBook) {
		this.selectBook = selectBook;
	}
}
