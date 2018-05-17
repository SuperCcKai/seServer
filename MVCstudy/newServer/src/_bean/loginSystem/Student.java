package _bean.loginSystem;

import org.apache.ibatis.type.Alias;

@Alias(value = "Student")
public class Student {
	private String stuID;
	private String stuName;
	private String stuPassword;
	
	public Student() {
		//
	}

	public String getStuID() {
		return stuID;
	}

	public void setStuID(String stuID) {
		this.stuID = stuID;
	}

	public String getStuName() {
		return stuName;
	}

	public void setStuName(String stuName) {
		this.stuName = stuName;
	}

	public String getStuPassword() {
		return stuPassword;
	}

	public void setStuPassword(String stuPassword) {
		this.stuPassword = stuPassword;
	}
	
}
