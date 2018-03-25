package model;

import java.sql.Date;

public class student {
	
	private Integer id;
	private String studyid;
	private String name;
	private String sex;
	private Integer age;
	private Date birthday;
	private String email;
	private String phonenum;
	private String fdyname;
	private String grade;
	private String address;
	private String from;
	private Date create_date;
	private Date update_date;
	private String stdclass;
	private String college;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getStudyid() {
		return studyid;
	}
	public void setStudyid(String studyid) {
		this.studyid = studyid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public Integer getAge() {
		return age;
	}
	public void setAge(Integer age) {
		this.age = age;
	}
	public Date getBirthday() {
		return birthday;
	}
	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPhonenum() {
		return phonenum;
	}
	public void setPhonenum(String phonenum) {
		this.phonenum = phonenum;
	}
	public String getFdyname() {
		return fdyname;
	}
	public void setFdyname(String fdyname) {
		this.fdyname = fdyname;
	}
	public String getGrade() {
		return grade;
	}
	public void setGrade(String grade) {
		this.grade = grade;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getFrom() {
		return from;
	}
	public void setFrom(String from) {
		this.from = from;
	}
	public Date getCreate_date() {
		return create_date;
	}
	public void setCreate_date(Date create_date) {
		this.create_date = create_date;
	}
	public Date getUpdate_date() {
		return update_date;
	}
	public void setUpdate_date(Date update_date) {
		this.update_date = update_date;
	}
	public String getStdclass() {
		return stdclass;
	}
	public void setStdclass(String stdclass) {
		this.stdclass = stdclass;
	}
	public String getCollege() {
		return college;
	}
	public void setCollege(String college) {
		this.college = college;
	}
	@Override
	public String toString() {
		return "\n 学号：" + studyid + "\n 姓名：" + name + "\n 性别：" + sex + "\n 年龄：" + age
				+ "\n 生日：" + birthday + "\n 邮箱：" + email + "\n 电话：" + phonenum  + "\n 学院：" + college+ "\n 辅导员：" + fdyname
				+ "\n 年级：" + grade+ "\n 班级：" + stdclass + "\n 地址：" + address + "\n 来自：" + from + "\n 创建时间：" + create_date
				+ "\n 修改时间：" + update_date ;
	}
	
}
