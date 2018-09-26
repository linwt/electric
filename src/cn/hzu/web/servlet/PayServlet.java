package cn.hzu.web.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.hzu.dao.UserDao;
import cn.hzu.domain.User;
import cn.hzu.util.ResponseUtil;
import net.sf.json.JSONObject;

public class PayServlet extends HttpServlet{
	UserDao userdao=new UserDao();
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doPost(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		request.setCharacterEncoding("utf-8");
		
		String spay=request.getParameter("pay");
		Double pay = Double.parseDouble(spay);
		
		User user = (User) request.getSession().getAttribute("currentUser");
		
		try {
			JSONObject result=new JSONObject();
			if(pay > user.getDmoney()) {
				result.put("success", "true"); 
				result.put("errorMsg", "支付金额高于欠费总额，请重新输入！");
			} else{
				userdao.updateMoney(user.getFid(), user.getDid(), pay);
				result.put("success", "true");
			}
			
			
			ResponseUtil.write(response, result);
		} catch (Exception e) {}
	}

	
	
}
