package cn.hzu.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import cn.hzu.domain.PageBean;
import cn.hzu.util.StringUtil;

public class FloorDao {

	public ResultSet floorList(Connection con,PageBean pageBean,String fid) throws Exception{
		StringBuffer sb=new StringBuffer("select * from floor where 1=1");
		if(StringUtil.isNotEmpty(fid)){
			sb.append(" and fid like '%"+fid+"%'"); 
		} 
		if(pageBean!=null){
			sb.append(" limit "+pageBean.getStart()+","+pageBean.getRows());
		}
		PreparedStatement state = con.prepareStatement(sb.toString());
		return state.executeQuery();
	}
	
	public int floorCount(Connection con,String fid)throws Exception{
		StringBuffer sb=new StringBuffer("select count(*) as total from floor where 1=1");
			if(StringUtil.isNotEmpty(fid)){
			sb.append(" and fid like '%"+fid+"%'"); 
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
