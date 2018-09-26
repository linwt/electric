package cn.hzu.dao;

import java.sql.Connection;   
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import cn.hzu.domain.User;

/**
 * 针对数据库的实现
 * @author cxf
 *
 */
public class UserDao {
	
	public void addUser(User form) {
		Connection con = null;
		PreparedStatement state = null;
		try {
			// 一、得到连接
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/electric","root","123");
			
			// 二、准备sql模板，得到state
			String sql = "INSERT INTO user VALUES(?,?)";
			state = con.prepareStatement(sql);

			// 三、为state中的问号赋值
			state.setString(1, form.getStuid());
			state.setString(2, form.getPassword());
			
			// 四、执行
			state.executeUpdate();
		} catch(Exception e) {
			throw new RuntimeException(e);
		} finally {
			try {
				if(state != null) state.close();
				if(con != null) con.close();
			} catch(SQLException e) {}
		}
	}

	public User findByStuid(String stuid) {
		Connection con = null;
		PreparedStatement state = null;
		ResultSet rs = null;
		try {

			 // 一、得到连接
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/electric","root","123");

			// 二、准备sql模板，得到state
			String sql = "SELECT * FROM user inner join stu on user.stuid=stu.stuid INNER JOIN dorm on stu.fid=dorm.fid and stu.did=dorm.did WHERE user.stuid=?"; 
			state = con.prepareStatement(sql);
	
			// 三、为state中的问号赋值
			state.setString(1, stuid);		
			
			// 四、执行
			rs = state.executeQuery();
			// 五、把rs转换成User类型，返回！
			if(rs == null) {
				System.out.println("hehe");
				return null;
			}
			if(rs.next()) {
				//转换成User对象，并返回
				// ORM --> 对象关系映射！ hibernate!
				User user = new User();
				user.setStuid(rs.getString("stuid"));
				user.setPassword(rs.getString("password"));		
				user.setFid(rs.getString("fid"));
				user.setDid(rs.getString("did"));
				user.setDmoney(rs.getDouble("dmoney"));
				user.setName(rs.getString("name"));
				return user;
			} else {
				return null;
			}
		} catch(Exception e) {
			throw new RuntimeException(e);
		} finally {
			try {
				if(state != null) state.close();
				if(con != null) con.close();
			} catch(SQLException e) {}
		}
	}
	
	public void updateMoney(String fid, String did, double pay) {
		Connection con = null;
		PreparedStatement state = null;
		ResultSet rs = null;
		try {

			 // 一、得到连接
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/electric","root","123");

			// 二、准备sql模板，得到state
			String sql = "update dorm set dmoney=(dmoney-?) where fid=? and did=?"; 
			state = con.prepareStatement(sql);
	
			// 三、为state中的问号赋值
			state.setDouble(1, pay);
			state.setString(2, fid);	
			state.setString(3, did);
			
			// 四、执行
			state.executeUpdate();
					
		} catch(Exception e) {
			throw new RuntimeException(e);
		} finally {
			try {
				if(state != null) state.close();
				if(con != null) con.close();
			} catch(SQLException e) {}
		}
	}
	
	public void changePw(String stuid,String newPw) {
		Connection con = null;
		PreparedStatement state = null;
		ResultSet rs = null;
		try {

			 // 一、得到连接
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/electric","root","123");

			// 二、准备sql模板，得到state
			String sql = "update user set password=? where stuid=?"; 
			state = con.prepareStatement(sql);
	
			// 三、为state中的问号赋值
			state.setString(1, newPw);
			state.setString(2, stuid);	
			
			// 四、执行
			state.executeUpdate();
					
		} catch(Exception e) {
			throw new RuntimeException(e);
		} finally {
			try {
				if(state != null) state.close();
				if(con != null) con.close();
			} catch(SQLException e) {}
		}
	}
}
