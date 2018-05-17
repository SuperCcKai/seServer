package _bean.loginSystem;

import org.apache.ibatis.type.Alias;

@Alias(value = "Teacher")
public class Teacher {
	private String teaID;
	private String teaName;
	private String teaPassword;
	
	public Teacher() {
		//
	}

	public String getTeaID() {
		return teaID;
	}

	public void setTeaID(String teaID) {
		this.teaID = teaID;
	}

	public String getTeaName() {
		return teaName;
	}

	public void setTeaName(String teaName) {
		this.teaName = teaName;
	}

	public String getTeaPassword() {
		return teaPassword;
	}

	public void setTeaPassword(String teaPassword) {
		this.teaPassword = teaPassword;
	}
	
}
