package main.form;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import ibatis.dto.Library;

public class SearchForm {
	Library library1 = new Library(1, "テスト1", "１社", "イチ子");
	Library library2 = new Library(2, "テスト2", "2社", "ジロー");

	private List<Library> libraryList = new ArrayList<Library>(Arrays.asList(library1, library2));
	private String searchCategory;
	private String searchWord;
	private boolean selectBook;

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
		this.libraryList = libraryList;
	}
	public boolean isSelectBook() {
		return selectBook;
	}
	public void setSelectBook(boolean selectBook) {
		this.selectBook = selectBook;
	}
}
