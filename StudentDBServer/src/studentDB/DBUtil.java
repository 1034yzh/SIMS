package studentDB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DBUtil {

	private static final String URL = "jdbc:mysql://127.0.0.1:3306/javadb?useUnicode=true&characterEncoding=UTF-8&useSSL=true";
	private static final String USER = "root";
	private static final String PASSWORD = "123456";
	private static Connection conn;
	
	static{
		try {
			//1.加载驱动程序
			Class.forName("com.mysql.jdbc.Driver");
			//2.获得数据库连接
			conn =  DriverManager.getConnection(URL, USER,PASSWORD);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static Connection  getconnection(){
		return conn;
	}
	
	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		
		//1.加载驱动程序
		Class.forName("com.mysql.jdbc.Driver");
		//2.获得数据库连接
		Connection conn =  DriverManager.getConnection(URL, USER,PASSWORD);
		//3.通过数据库的连接实现对数据库的增删改查
		Statement stmt = conn.createStatement();
		ResultSet rs = stmt.executeQuery("select * from student");
		while(rs.next())
		{
			System.out.println(rs.getInt("id")+"\t"+
			rs.getString("studyid")+"\t"+
			rs.getString("name")+"\t"+
			rs.getString("sex")+"\t"+
			rs.getInt("age")+"\t"+
			rs.getDate("birthday")+"\t"+
			rs.getString("email")+"\t"+
			rs.getString("phonenum")+"\t"+
			rs.getString("fdyname")+"\t"+
			rs.getString("grade")+"\t"+
			rs.getString("address")+"\t"+
			rs.getString("fromwhere")+"\t"+
			rs.getDate("create_date")+"\t"+
			rs.getDate("update_date")+"\t"+
			rs.getString("class")+"\t"+
			rs.getString("college"));
		}
	}

}
