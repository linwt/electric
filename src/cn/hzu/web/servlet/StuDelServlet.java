package cn.hzu.web.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.hzu.dao.StuDao;
import cn.hzu.util.ResponseUtil;
import net.sf.json.JSONObject;

public class StuDelServlet extends HttpServlet{
	
	StuDao studao = new StuDao();
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doPost(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String delIds=request.getParameter("delIds");
		try{
			JSONObject result=new JSONObject();
			int delNums = studao.delStu(delIds);
			if(delNums > 0) {
				result.put("success", "true");
				result.put("delNums", delNums);
			} else{
				result.put("success", "true");
				result.put("errorMsg", "删除失败");
			}
			ResponseUtil.write(response, result);
		}catch(Exception e){
			
		}
	}

	
	
}
