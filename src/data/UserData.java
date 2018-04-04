package data;

public class UserData {

	String userid;

	String password;

	public UserData(String userid, String passward) {
		this.userid = userid;
		this.password = passward;
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public String getPassward() {
		return password;
	}

	public void setPassward(String passward) {
		this.password = passward;
	}

	@Override
	public String toString() {
		return "UserData [userid=" + userid + ", passward=" + password + "]";
	}


}
