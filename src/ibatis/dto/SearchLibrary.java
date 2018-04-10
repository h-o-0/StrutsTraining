package ibatis.dto;

import java.util.List;

public class SearchLibrary {

	private List<String> titleList;

	private List<String> publisherList;

	private List<String> authorList;

	public SearchLibrary() {

	}

	public SearchLibrary(List<String> titleList, List<String> publisherList, List<String> authorList) {
		this.titleList = titleList;
		this.publisherList = publisherList;
		this.authorList = authorList;
	}

	public List<String> getTitle() {
		return titleList;
	}

	public void setTitle(List<String> titleList) {
		this.titleList = titleList;
	}

	public List<String> getPublisher() {
		return publisherList;
	}

	public void setPublisher(List<String> publisherList) {
		this.publisherList = publisherList;
	}

	public List<String> getAuthor() {
		return authorList;
	}

	public void setAuthor(List<String> authorList) {
		this.authorList = authorList;
	}

}
