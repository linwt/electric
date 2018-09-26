package cn.hzu.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import cn.hzu.domain.Elec;
import cn.hzu.domain.PageBean;
import cn.hzu.domain.Root;
import cn.hzu.domain.Stu;
import cn.hzu.util.StringUtil;

public class StuDao {

	public ResultSet stuList(Connection con,PageBean pageBean,Stu stu)throws Exception{
		StringBuffer sb=new StringBuffer("select * from stu where 1=1");
		if(StringUtil.isNotEmpty(stu.getStuid())){
			sb.append(" and stuid like '%"+stu.getStuid()+"%'"); 
		} 
		if(StringUtil.isNotEmpty(stu.getFid())){
			sb.append(" and fid like '%"+stu.getFid()+"%'"); 
		}
		if(StringUtil.isNotEmpty(stu.getDid())){
			sb.append(" and did like '%"+stu.getDid()+"%'"); 
		}
		if(pageBean!=null){
			sb.append(" limit "+pageBean.getStart()+","+pageBean.getRows());
		}
		PreparedStatement pstmt=con.prepareStatement(sb.toString());
		return pstmt.executeQuery();
	}
	
	public int stuCount(Connection con,Stu stu)throws Exception{
		StringBuffer sb=new StringBuffer("select count(*) as total from stu where 1=1");
		if(StringUtil.isNotEmpty(stu.getStuid())){
			sb.append(" and stuid like '%"+stu.getStuid()+"%'"); 
		}
		if(StringUtil.isNotEmpty(stu.getFid())){
			sb.append(" and fid like '%"+stu.getFid()+"%'"); 
		}
		if(StringUtil.isNotEmpty(stu.getDid())){
			sb.append(" and did like '%"+stu.getDid()+"%'"); 
		}
		PreparedStatement pstmt=con.prepareStatement(sb.toString());
		ResultSet rs=pstmt.executeQuery();
		if(rs.next()){
			return rs.getInt("total");
		}else{
			return 0;
		}
	}
	
	public void addStu(Connection con,Stu form)throws Exception {
		String sql = "INSERT INTO stu VALUES(?,?,?,?,?,?,?,?,?)";
		PreparedStatement state = con.prepareStatement(sql);
		state.setString(1, form.getStuid());
		state.setString(2, form.getName());
		state.setString(3, form.getSex());
		state.setString(4, form.getAge());
		state.setString(5, form.getDepart());
		state.setString(6, form.getMajor());
		state.setString(7, form.getPhone());
		state.setString(8, form.getFid());
		state.setString(9, form.getDid());
		state.executeUpdate();
	}

	public Stu findByStuid(String stuid) {
		Connection con = null;
		PreparedStatement state = null;
		ResultSet rs = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/electric","root","123");

			String sql = "SELECT * FROM stu WHERE stuid=?"; 
			state = con.prepareStatement(sql);
			state.setString(1, stuid);		
			rs = state.executeQuery();
	
			if(rs == null) {
				return null;
			}
			if(rs.next()) {
				Stu stu = new Stu();
				stu.setStuid(rs.getString("stuid"));
				stu.setName(rs.getString("name"));
				stu.setSex(rs.getString("sex"));
				stu.setAge(rs.getString("age"));
				stu.setDepart(rs.getString("depart"));
				stu.setMajor(rs.getString("major"));
				stu.setFid(rs.getString("fid"));
				stu.setDid(rs.getString("did"));
				stu.setPhone(rs.getString("phone"));
				return stu;
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
	
	
	public void modifyStu(Connection con,Stu form) throws Exception {
		
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/electric","root","123");
			
			String sql = "update stu set name=?,sex=?,age=?,depart=?,major=?,fid=?,did=?,phone=? where stuid=?";
			PreparedStatement state = con.prepareStatement(sql);
			state.setString(1, form.getName());
			state.setString(2, form.getSex());
			state.setString(3, form.getAge());
			state.setString(4, form.getDepart());
			state.setString(5, form.getMajor());
			state.setString(6, form.getFid());
			state.setString(7, form.getDid());
			state.setString(8, form.getPhone());
			state.setString(9, form.getStuid());
			state.executeUpdate();
	}
	
	public int delStu(String delIds) {
		Connection con = null;
		PreparedStatement state = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/electric","root","123");
			
			String sql = "delete from stu where stuid in("+delIds+")";
			state = con.prepareStatement(sql);
			return state.executeUpdate();
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
