package ibatis.dto;

public class User {

	private String id;

	private String password;

	public User() {}

	public User(String id, String password) {
		this.id = id;
		this.password = password;
	}

	public String getUserid() {
		return id;
	}

	public void setUserid(String id) {
		this.id = id;
	}

	public String getPassward() {
		return password;
	}

	public void setPassward(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "UserData [userid=" + id + ", password=" + password + "]";
	}


}
