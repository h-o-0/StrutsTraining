package ibatis.dto;

public class Stock {

	private String title;

	private String volume;

    private int status;

    public Stock() {

    }

	public Stock(String title, String volume, int status) {
		this.title = title;
		this.volume = volume;
		this.status = status;
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

}
