package cn.hzu.dao;

import java.sql.Connection; 
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import cn.hzu.domain.Elec;
import cn.hzu.domain.PageBean;
import cn.hzu.util.StringUtil;

public class ElecDao {

	public ResultSet elecList(Connection con,PageBean pageBean,Elec elec)throws Exception{
		StringBuffer sb=new StringBuffer("select * from ele where 1=1");
		if(StringUtil.isNotEmpty(elec.getFid())){
			sb.append(" and fid like '%"+elec.getFid()+"%'"); 
		} 
		if(StringUtil.isNotEmpty(elec.getDid())){ 
			sb.append(" and did like '%"+elec.getDid()+"%'"); 
		}
		if(StringUtil.isNotEmpty(elec.getYear())){
			sb.append(" and year like '%"+elec.getYear()+"%'");
		}
		if(StringUtil.isNotEmpty(elec.getMonth())){
			sb.append(" and month like '%"+elec.getMonth()+"%'");
		}
		if(pageBean!=null){
			sb.append(" limit "+pageBean.getStart()+","+pageBean.getRows());
		}
		PreparedStatement pstmt=con.prepareStatement(sb.toString());
		return pstmt.executeQuery();
	}
	
	public int elecCount(Connection con,Elec elec)throws Exception{
		StringBuffer sb=new StringBuffer("select count(*) as total from ele where 1=1");
		if(StringUtil.isNotEmpty(elec.getFid())){
			sb.append(" and fid like '%"+elec.getFid()+"%'"); 
		}
		if(StringUtil.isNotEmpty(elec.getDid())){ 
			sb.append(" and did like '%"+elec.getDid()+"%'");
		}
		if(StringUtil.isNotEmpty(elec.getYear())){
			sb.append(" and year like '%"+elec.getYear()+"%'");
		}
		if(StringUtil.isNotEmpty(elec.getMonth())){
			sb.append(" and month like '%"+elec.getMonth()+"%'");
		}
		PreparedStatement pstmt=con.prepareStatement(sb.toString());
		ResultSet rs=pstmt.executeQuery();
		if(rs.next()){
			return rs.getInt("total");
		}else{
			return 0;
		}
	}
	
}
