package main.form;

import org.apache.struts.action.ActionForm;

public class EditForm extends ActionForm {

	private String id;
	private String title;
	private String volume;
	private String publisher;
	private String author;
	private boolean isNewBook;

	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getVolume() {
		return volume;
	}
	public void setVolume(String volume) {
		this.volume = volume;
	}
	public String getPublisher() {
		return publisher;
	}
	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public boolean isIsNewBook() {
		return isNewBook;
	}
	public void setIsNewBook(boolean isNewBook) {
		this.isNewBook = isNewBook;
	}
}
