package ibatis.dto;

public class User {

	private String id;

	private String password;

	private int admin;

	public User() {}

	public User(String id, String password, int admin) {
		super();
		this.id = id;
		this.password = password;
		this.admin = admin;
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


	public int getAdmin() {
		return admin;
	}

	public void setAdmin(int admin) {
		this.admin = admin;
	}

	@Override
	public String toString() {
		return "UserData [userid=" + id + ", password=" + password + "]";
	}


}
