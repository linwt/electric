package cn.hzu.web.servlet;

import java.io.IOException;
import java.sql.Connection;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.hzu.dao.StuDao;
import cn.hzu.domain.Elec;
import cn.hzu.domain.PageBean;
import cn.hzu.domain.Stu;
import cn.hzu.util.DbUtil;
import cn.hzu.util.JsonUtil;
import cn.hzu.util.ResponseUtil;
import cn.itcast.commons.CommonUtils;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;



public class StuListServlet extends HttpServlet{
	DbUtil dbUtil=new DbUtil();
	StuDao studao = new StuDao();
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doPost(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		Stu stu = CommonUtils.toBean(request.getParameterMap(), Stu.class);
		
		String page=request.getParameter("page");
		String rows=request.getParameter("rows");
		PageBean pageBean=new PageBean(Integer.parseInt(page),Integer.parseInt(rows));
		Connection con=null;
		try{
			con=dbUtil.getCon();
			JSONObject result=new JSONObject();
			JSONArray jsonArray=JsonUtil.formatRsToJsonArray(studao.stuList(con, pageBean,stu));
			int total=studao.stuCount(con,stu);
			result.put("rows", jsonArray);
			result.put("total", total);
			ResponseUtil.write(response, result);
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			try {
				dbUtil.closeCon(con);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

}
