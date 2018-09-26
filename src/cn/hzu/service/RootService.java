package cn.hzu.service;

import cn.hzu.dao.RootDao;
import cn.hzu.domain.Root;

// User的业务逻辑层
public class RootService {
	
	private RootDao rootDao = new RootDao();
	
	// 注册功能
	public void regist(Root form) throws UserException {
		Root root = RootDao.findByRid(form.getRid());
		if(root != null) throw new UserException("该工号已被注册过！"); 
		rootDao.addRoot(form);
	}

	// 登录功能
	public Root login(Root form) throws UserException {
		Root root = rootDao.findByRid(form.getRid());
		if(root == null) throw new UserException("工号或密码错误！请重新输入。");
		if(!form.getRpassword().equals(root.getRpassword())) throw new UserException("工号或密码错误！请重新输入。");
		return root;
	}

}
