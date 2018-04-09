package main.form;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.struts.action.ActionForm;

public class AddForm extends ActionForm {

	private List<String> titleList = new ArrayList<String>(Arrays.asList("はじめの一歩","働きマン"));
	private String title;
	private List<String> volumeList = new ArrayList<String>(Arrays.asList("1","2"));
	private String volume;
	private List<String> publisherList = new ArrayList<String>(Arrays.asList("講談社","集英社"));
	private String publisher;
	private List<String> authorList = new ArrayList<String>(Arrays.asList("安野モヨコ","森川ジョージ"));
	private String author;
	private boolean copyData;

	public List<String> getTitleList() {
		return titleList;
	}
	public void setTitleList(List<String> titleList) {
		this.titleList = titleList;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public List<String> getVolumeList() {
		return volumeList;
	}
	public void setVolumeList(List<String> volumeList) {
		this.volumeList = volumeList;
	}
	public String getVolume() {
		return volume;
	}
	public void setVolume(String volume) {
		this.volume = volume;
	}
	public List<String> getPublisherList() {
		return publisherList;
	}
	public void setPublisherList(List<String> publisherList) {
		this.publisherList = publisherList;
	}
	public String getPublisher() {
		return publisher;
	}
	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}
	public List<String> getAuthorList() {
		return authorList;
	}
	public void setAuthorList(List<String> authorList) {
		this.authorList = authorList;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public boolean getCopyData() {
		return copyData;
	}
	public void setCopyData(boolean copyData) {
		this.copyData = copyData;
	}

}
