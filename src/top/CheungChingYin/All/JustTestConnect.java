package top.CheungChingYin.All;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JustTestConnect {

	Connection con;
	public Connection getConnection(){
		try{
			Class.forName("com.mysql.jdbc.Driver");
		}catch(ClassNotFoundException e){
			e.printStackTrace();
		}
		try {
			con=DriverManager.getConnection("jdbc:mysql://localhost:3306/studentmanagement","root","123456");
			System.out.println("数据库连接成功");
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return con;
	}
	public static void main(String[] args) {
		JustTestConnect c=new JustTestConnect();
		c.getConnection();
	}
}
