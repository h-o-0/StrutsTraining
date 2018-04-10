package ibatis.dto;

public class Library {

	private int id;

	private String title;

	private String publisher;

	private String author;

	public Library() {

	}

	/**
	 * 登録用
	 * @param title
	 * @param publisher
	 * @param author
	 */
	public Library(String title, String publisher, String author) {
		this(0, title, publisher, author);
	}

	public Library(int id, String title, String publisher, String author) {
		this.id = id;
		this.title = title;
		this.publisher = publisher;
		this.author = author;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
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

	@Override
	public String toString() {
		return "Library [id=" + id + ", title=" + title + ", publisher=" + publisher + ", author=" + author + "]";
	}



}
