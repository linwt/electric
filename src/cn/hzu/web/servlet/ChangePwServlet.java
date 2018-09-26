package cn.hzu.web.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.hzu.dao.UserDao;
import cn.hzu.domain.User;
import cn.hzu.service.UserService;
import cn.hzu.util.ResponseUtil;
import net.sf.json.JSONObject;


@SuppressWarnings("serial")
public class ChangePwServlet extends HttpServlet{
	
	UserDao userdao = new UserDao();
	UserService userservice = new UserService();
	
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doPost(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		request.setCharacterEncoding("utf-8");
		String oldPw=request.getParameter("oldPw");
		String newPw=request.getParameter("newPw");
		User user = (User) request.getSession().getAttribute("currentUser");
		String password = user.getPassword();
		try {
			JSONObject result=new JSONObject();
			if(user.getPassword().equals(oldPw)) {
				userdao.changePw(user.getStuid(),newPw);
				result.put("success", "true");
			} else{
				result.put("success", "true");
				result.put("errorMsg", "旧密码错误，保存失败！");
			}
			ResponseUtil.write(response, result);
		} catch (Exception e) {}
	}
	
}
