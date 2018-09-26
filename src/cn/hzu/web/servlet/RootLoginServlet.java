package cn.hzu.web.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.hzu.domain.Root;
import cn.hzu.service.RootService;
import cn.hzu.service.UserException;
import cn.itcast.commons.CommonUtils;

public class RootLoginServlet extends HttpServlet {
	
	public void doPost(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");					//请求编码(POST)
		response.setContentType("text/html;charset=utf-8");		//响应编码
		
		RootService rootService = new RootService();
		
		Root form = CommonUtils.toBean(request.getParameterMap(), Root.class);
		try {
			Root root = rootService.login(form); 
			request.getSession().setAttribute("currentRoot", root);
			response.sendRedirect(request.getContextPath() + "/root_main.jsp");
		} catch(UserException e) {
			request.setAttribute("msg", e.getMessage());
			request.setAttribute("root", form);
			request.getRequestDispatcher("/root_login.jsp").forward(request, response);
		}
		
	}
}
