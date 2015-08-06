package com.pojo;
import java.util.Date;
//视频中myeclipse 可以自动根据实体类 生成xml文件，可惜我这里没有该向导，所以该实体类的xml映射文件为手动敲的
public class Students{
	
	private int sid;
	private String sname;
	private String gender;
	private Date birthday;
	private String address;
	
	public Students() {
	}

//	便于构造
	public Students(int sid, String sname, String gender, Date birthday,
			String address) {
		super();
		this.sid = sid;
		this.sname = sname;
		this.gender = gender;
		this.birthday = birthday;
		this.address = address;
	}

	public int getSid() {
		return sid;
	}

	public void setSid(int sid) {
		this.sid = sid;
	}

	public String getSname() {
		return sname;
	}

	public void setSname(String sname) {
		this.sname = sname;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public java.util.Date getBirthday() {
		return birthday;
	}

	public void setBirthday(java.util.Date birthday) {
		this.birthday = birthday;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	@Override
	public String toString() {
		return "Student [address=" + address + ", birthday=" + birthday
				+ ", gender=" + gender + ", sid=" + sid + ", sname=" + sname
				+ "]";
	}
	
//	便于测试
	
	
}