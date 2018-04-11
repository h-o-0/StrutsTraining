package main.form;

import org.apache.struts.action.ActionForm;

import ibatis.dto.Library;

public class AddForm extends ActionForm {
	private String id;
	private Library library;
	private String volume;

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
	public String getVolume() {
		return volume;
	}
	public void setVolume(String volume) {
		this.volume = volume;
	}
}
