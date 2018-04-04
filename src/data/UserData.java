package data;

public class UserData {

	String userid;

	String passward;

	public UserData(String userid, String passward) {
		this.userid = userid;
		this.passward = passward;
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public String getPassward() {
		return passward;
	}

	public void setPassward(String passward) {
		this.passward = passward;
	}

	@Override
	public String toString() {
		return "UserData [userid=" + userid + ", passward=" + passward + "]";
	}


}
