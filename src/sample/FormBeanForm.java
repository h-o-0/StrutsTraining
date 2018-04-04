package sample;

import org.apache.struts.action.ActionForm;

public final class FormBeanForm extends ActionForm {

	private String title;
	private int volume;
	private String publisher;
	private int status;

	public String getTitle() { return title; }
	public void setTitle(String title) { this.title = title; }

	public int getVolume() { return volume; }
	public void setVolume(int volume) { this.volume = volume; }

	public String getPublisher() { return publisher; }
	public void setPublisher(String publisher) { this.publisher = publisher; }

	public int getStatus() { return status; }
	public void setStatus(int status) { this.status = status; }
}
