package main.form;

import org.apache.struts.action.ActionForm;

public class DeleteForm extends ActionForm {
	private String id;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
}
