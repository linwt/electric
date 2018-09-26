package cn.hzu.util;

import java.sql.Connection; 
import java.sql.DriverManager;

public class DbUtil {

	private String dbUrl="jdbc:mysql://localhost:3306/electric";
	private String dbUserName="root";
	private String dbPassword="123";
	private String jdbcName="com.mysql.jdbc.Driver";
	static Connection con;
	
	/**
	 * 获取数据库连接
	 * @return
	 * @throws Exception
	 */
	public Connection getCon() throws Exception{
		Class.forName(jdbcName);
		con=DriverManager.getConnection(dbUrl,dbUserName,dbPassword);
		return con;
	}
	
	/**
	 * 关闭数据库连接
	 * @param con
	 * @throws Exception
	 */
	public void closeCon(Connection con) throws Exception{
		if(con!=null){
			con.close();
		}
	}
	
	public static void main(String[] args) {
		DbUtil dbUtil=new DbUtil();
		try {
			dbUtil.getCon();
			System.out.println("数据库连接成功");
			dbUtil.closeCon(con);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
