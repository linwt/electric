package cn.hzu.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import cn.hzu.domain.Root;
import cn.hzu.domain.User;

/**
 * 针对数据库的实现
 * @author cxf
 *
 */
public class RootDao {
	
	public void addRoot(Root form) {
		Connection con = null;
		PreparedStatement state = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/electric","root","123");
			
			String sql = "INSERT INTO root VALUES(?,?,?)";
			state = con.prepareStatement(sql);
			state.setString(1, form.getRid());
			state.setString(2, form.getRname());
			state.setString(3, form.getRpassword());
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

	public static Root findByRid(String rid) {
		Connection con = null;
		PreparedStatement state = null;
		ResultSet rs = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/electric","root","123");

			String sql = "SELECT * FROM root WHERE rid=?"; 
			state = con.prepareStatement(sql);
			state.setString(1, rid);		
			rs = state.executeQuery();
	
			if(rs == null) {
				return null;
			}
			if(rs.next()) {
				Root root = new Root();
				root.setRid(rs.getString("rid"));
				root.setRname(rs.getString("rname"));		
				root.setRpassword(rs.getString("rpassword"));
				return root;
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
