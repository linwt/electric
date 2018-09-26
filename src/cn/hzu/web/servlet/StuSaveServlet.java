package cn.hzu.web.servlet;

import java.io.IOException;
import java.sql.Connection;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.hzu.dao.StuDao;
import cn.hzu.domain.Stu;
import cn.hzu.util.DbUtil;
import cn.hzu.util.ResponseUtil;
import cn.itcast.commons.CommonUtils;
import net.sf.json.JSONObject;


@SuppressWarnings("serial")
public class StuSaveServlet extends HttpServlet{
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
		
		request.setCharacterEncoding("utf-8"); 
		Stu form = CommonUtils.toBean(request.getParameterMap(), Stu.class);
		Connection con = null;
		try {
			con = dbUtil.getCon();
			JSONObject result=new JSONObject(); 
			if(form.getChoose().toString().equals("m".toString())){
				studao.modifyStu(con,form);
				result.put("success", "true");
			} else{
				if(studao.findByStuid(form.getStuid())!=null) {
					result.put("success", "true");
					result.put("errorMsg", "该学号已存在！");
				} else{
					studao.addStu(con,form);
					result.put("success", "true");
				}
			}
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
