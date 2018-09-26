package cn.hzu.web.servlet;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.hzu.domain.Root;
import cn.hzu.service.RootService;
import cn.hzu.service.UserException;
import cn.itcast.commons.CommonUtils;

public class RootRegistServlet extends HttpServlet {

	public void doPost(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
		
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		
		RootService rootService = new RootService();		
		Root form = CommonUtils.toBean(request.getParameterMap(), Root.class);	
		Map<String,String> r_errors = new HashMap<String,String>();
		
		// 对工号进行校验
		String rid = form.getRid();
		if(rid == null || rid.trim().isEmpty()) {
			r_errors.put("rid", "工号不能为空！");
		} else if(rid.length() < 3 || rid.length() > 15) {
			r_errors.put("rid", "工号长度必须在3~15之间！");
		}
		
		// 对用户名进行校验
		String rname = form.getRname();
		if(rname == null || rname.trim().isEmpty()) {
			r_errors.put("rname", "用户名不能为空！");
		} else if(rname.length() < 2 || rname.length() > 15) {
			r_errors.put("rname", "用户名长度必须在2~15之间！");
		}

		// 对密码进行校验
		String rpassword = form.getRpassword();
		if(rpassword == null || rpassword.trim().isEmpty()) {
			r_errors.put("rpassword", "密码不能为空！");
		} else if(rpassword.length() < 3 || rpassword.length() > 15) {
			r_errors.put("rpassword", "密码长度必须在3~15之间！");
		}
		
		// 判断map是否为空，不为空，说明存在错误
		if(r_errors != null && r_errors.size() > 0) {
			request.setAttribute("r_errors", r_errors);
			request.setAttribute("root", form);
			request.getRequestDispatcher("/root_regist.jsp").forward(request, response);
			return;
		}
		try {
			rootService.regist(form);
			response.getWriter().print("<h1>注册成功！</h1><a href='" + request.getContextPath() + "/root_login.jsp" + "'>点击这里去登录</a>");
		} catch (UserException e) {
			request.setAttribute("msg", e.getMessage());
			request.setAttribute("root", form);
			request.getRequestDispatcher("/root_regist.jsp").forward(request, response);
		}
	}

}
