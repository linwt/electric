package cn.hzu.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import cn.hzu.domain.Dorm;
import cn.hzu.domain.PageBean;
import cn.hzu.util.StringUtil;

public class DormDao {

	public ResultSet dormList(Connection con,PageBean pageBean,Dorm dorm) throws Exception{
		StringBuffer sb=new StringBuffer("select * from dorm where 1=1");
		if(StringUtil.isNotEmpty(dorm.getFid())){
			sb.append(" and fid like '%"+dorm.getFid()+"%'"); 
		} 
		if(StringUtil.isNotEmpty(dorm.getDid())){
			sb.append(" and did like '%"+dorm.getDid()+"%'"); 
		} 
		if(pageBean!=null){
			sb.append(" limit "+pageBean.getStart()+","+pageBean.getRows());
		}
		PreparedStatement state = con.prepareStatement(sb.toString());
		return state.executeQuery();
	}
	
	public int dormCount(Connection con,Dorm dorm)throws Exception{
		StringBuffer sb=new StringBuffer("select count(*) as total from dorm where 1=1");
		if(StringUtil.isNotEmpty(dorm.getFid())){
			sb.append(" and fid like '%"+dorm.getFid()+"%'"); 
		} 
		if(StringUtil.isNotEmpty(dorm.getDid())){
			sb.append(" and did like '%"+dorm.getDid()+"%'"); 
		} 
		PreparedStatement state=con.prepareStatement(sb.toString());
		ResultSet rs=state.executeQuery();
		if(rs.next()){
			return rs.getInt("total");
		}else{
			return 0;
		}
	} 

}
