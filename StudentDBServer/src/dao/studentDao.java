package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.mysql.jdbc.PreparedStatement;

import model.student;
import studentDB.DBUtil;

public class studentDao {

	// 增
	public void addStudent(student s) throws SQLException {
		Connection conn = null;
		try {
			conn = DBUtil.getconnection();
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("数据库连接出错！");
		}
		String sql = " " + "insert into student"
				+ "(studyid,name,sex,age,birthday,email,phonenum,fdyname,grade,address,fromwhere,create_date,update_date,class,college)"
				+ "values(" 
				+ "?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		PreparedStatement ptmt = (PreparedStatement) conn.prepareStatement(sql);
		ptmt.setString(1, s.getStudyid());
		System.out.println("add1");
		ptmt.setString(2, s.getName());
		System.out.println("add2");
		ptmt.setString(3, s.getSex());
		System.out.println("add3");
		ptmt.setInt(4, s.getAge());
		System.out.println("add4");
		ptmt.setDate(5, s.getBirthday());
		System.out.println("add5");
		ptmt.setString(6, s.getEmail());
		System.out.println("add6");
		ptmt.setString(7, s.getPhonenum());
		System.out.println("add7");
		ptmt.setString(8, s.getFdyname());
		System.out.println("add8");
		ptmt.setString(9, s.getGrade());
		System.out.println("add9");
		ptmt.setString(10, s.getAddress());
		System.out.println("add10");
		ptmt.setString(11, s.getFrom());
		System.out.println("add11");
		ptmt.setDate(12, s.getCreate_date());
		System.out.println("add12");
		ptmt.setDate(13, s.getUpdate_date());
		System.out.println("add13");
		ptmt.setString(14, s.getStdclass());
		System.out.println("add14");
		ptmt.setString(15, s.getCollege());
		System.out.println("add15");

		try {
			ptmt.execute();
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("sql语句执行出错！");
		}
	}

	// 改
	public void updateStudent(student s) throws SQLException {
		Connection conn = DBUtil.getconnection();
		String sql = " " + "update student "
				+ "set name=?,sex=?,age=?,birthday=?,email=?,phonenum=?,fdyname=?,grade=?,address=?,fromwhere=?,update_date=?,class=?,college=? "
				+ // 字符串末尾加空格
				"where studyid=?";
		int i= 0;
		System.out.println(i++);
		PreparedStatement ptmt = (PreparedStatement) conn.prepareStatement(sql);
		System.out.println(i++);
		ptmt.setString(1, s.getName());
		System.out.println(i++);
		ptmt.setString(2, s.getSex());
		System.out.println(i++);
		ptmt.setInt(3, s.getAge());
		System.out.println(i++);
		ptmt.setDate(4, new Date(s.getBirthday().getTime()));
		System.out.println(i++);
		ptmt.setString(5, s.getEmail());
		System.out.println(i++);
		ptmt.setString(6, s.getPhonenum());
		System.out.println(i++);
		ptmt.setString(7, s.getFdyname());
		System.out.println(i++);
		ptmt.setString(8, s.getGrade());
		System.out.println(i++);
		ptmt.setString(9, s.getAddress());
		System.out.println(i++);
		ptmt.setString(10, s.getFrom());
		System.out.println(i++);
		ptmt.setDate(11, new Date(s.getUpdate_date().getTime()));
		System.out.println(i++);
		ptmt.setString(12, s.getStdclass());
		System.out.println(i++);
		ptmt.setString(13, s.getCollege());
		System.out.println(i++);
		ptmt.setString(14, s.getStudyid());
		System.out.println(i++);
		try {
			ptmt.execute();
			System.out.println(i++);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	// 删
	public void delStudent(String studyid) throws SQLException {
		Connection conn = DBUtil.getconnection();
		String sql = " " + "delete from student " + "where studyid=?";
		PreparedStatement ptmt = (PreparedStatement) conn.prepareStatement(sql);
		ptmt.setString(1, studyid);
		ptmt.execute();
	}

	// 查询数据库所有数据
	public List<student> query() throws SQLException {
		Connection conn = DBUtil.getconnection();
		Statement stmt = conn.createStatement();
		ResultSet rs = stmt.executeQuery("select * from student ");
		List<student> ss = new ArrayList<student>();
		student s = null;
		while (rs.next()) {
			s = new student();
			s.setStudyid(rs.getString("studyid"));
			s.setName(rs.getString("name"));
			s.setSex(rs.getString("sex"));
			s.setAge(rs.getInt("age"));
			s.setBirthday(rs.getDate("birthday"));
			s.setEmail(rs.getString("email"));
			s.setPhonenum(rs.getString("phonenum"));
			s.setFdyname(rs.getString("fdyname"));
			s.setGrade(rs.getString("grade"));
			s.setAddress(rs.getString("address"));
			s.setFrom(rs.getString("fromwhere"));
			s.setCreate_date(rs.getDate("create_date"));
			s.setUpdate_date(rs.getDate("update_date"));
			s.setStdclass(rs.getString("class"));
			s.setCollege(rs.getString("college"));
			ss.add(s);

		}
		return ss;
	}

	// 根据name查
	public student name_get(String name) throws SQLException {
		student s = null;
		Connection conn = DBUtil.getconnection();
		String sql = " " + "select * from student " + // 注意末尾空格，若没有则testclass1与where相连了
				"where name=? ";
		PreparedStatement ptmt = (PreparedStatement) conn.prepareStatement(sql);
		ptmt.setString(1, name);
		// ptmt.execute();这是更改数据库的操作
		System.out.println("name:" + name);
		// 下面是查询操作
		ResultSet rs = ptmt.executeQuery();
		System.out.println("sql语句成功执行！");
		try {
			rs.next();
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("sql语句执行出错！");
			return null;
		}
		do{
			s = new student();
			s.setId(rs.getInt("id"));
			s.setStudyid(rs.getString("studyid"));
			s.setName(rs.getString("name"));
			s.setSex(rs.getString("sex"));
			s.setAge(rs.getInt("age"));
			s.setBirthday(rs.getDate("birthday"));
			s.setEmail(rs.getString("email"));
			s.setPhonenum(rs.getString("phonenum"));
			s.setFdyname(rs.getString("fdyname"));
			s.setGrade(rs.getString("grade"));
			s.setAddress(rs.getString("address"));
			s.setFrom(rs.getString("fromwhere"));
			s.setCreate_date(rs.getDate("create_date"));
			s.setUpdate_date(rs.getDate("update_date"));
			s.setStdclass(rs.getString("class"));
			s.setCollege(rs.getString("college"));
		}while(rs.next());
		return s;
	}

	// 根据studyid查
	public student studyid_get(String studyid) throws SQLException {
		student s = null;
		Connection conn = DBUtil.getconnection();
		String sql = " " + "select * from student " + // 注意末尾空格，若没有则testclass1与where相连了
				"where studyid=?";
		PreparedStatement ptmt = (PreparedStatement) conn.prepareStatement(sql);
		ptmt.setString(1, studyid);
		System.out.println("studyid:"+studyid);
		// 下面是查询操作
		ResultSet rs = ptmt.executeQuery();
		System.out.println("sql语句执行！");
		try {
			rs.next();
		} catch (Exception e) {
			System.out.println("sql语句执行出错！");
			return null;
		}
		do{
			s = new student();
			s.setId(rs.getInt("id"));
			s.setStudyid(rs.getString("studyid"));
			s.setName(rs.getString("name"));
			s.setSex(rs.getString("sex"));
			s.setAge(rs.getInt("age"));
			s.setBirthday(rs.getDate("birthday"));
			s.setEmail(rs.getString("email"));
			s.setPhonenum(rs.getString("phonenum"));
			s.setFdyname(rs.getString("fdyname"));
			s.setGrade(rs.getString("grade"));
			s.setAddress(rs.getString("address"));
			s.setFrom(rs.getString("fromwhere"));
			s.setCreate_date(rs.getDate("create_date"));
			s.setUpdate_date(rs.getDate("update_date"));
			s.setStdclass(rs.getString("class"));
			s.setCollege(rs.getString("college"));
		}while(rs.next());
		return s;
	}
}
