package ibatis.dto;

public class Stock {

	private int id;

	private int parent_id;

	private String title;

	private String volume;

    private int status;

    private String loan_comment;

    public Stock() {

    }

    public Stock(int parent_id, String title, String volume) {
		this(0, parent_id, title, volume, 1, null);
	}

    public Stock(int id, int parent_id, String title, String volume, int status, String loan_comment) {
		this.id = id;
		this.parent_id = parent_id;
		this.title = title;
		this.volume = volume;
		this.status = status;
		this.loan_comment = loan_comment;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getParent_id() {
		return parent_id;
	}

	public void setParent_id(int parent_id) {
		this.parent_id = parent_id;
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

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getLoan_comment() {
		return loan_comment;
	}

	public void setLoan_comment(String loan_comment) {
		this.loan_comment = loan_comment;
	}



}
