package cn.hzu.service;


import cn.hzu.dao.UserDao; 
import cn.hzu.domain.User;

// User的业务逻辑层
public class UserService {
	private UserDao userDao = new UserDao();
	
	// 注册功能
	public void regist(User user) throws UserException {
		
		// 1. 使用用户名去查询，如果返回null，完成添加
		// 2. 如果返回的不是null，抛出异常！
		User _user = userDao.findByStuid(user.getStuid());
		if(_user != null) throw new UserException("该学号已被注册过！"); 
		userDao.addUser(user);
	}

	// 登录功能
	public User login(User form) throws UserException {

		// 1. 使用form中的username进行查询，得到User user
		User user = userDao.findByStuid(form.getStuid());
	
		// 2. 如果返回null，说明用户名不存在，抛出异常，异常信息为“用户名不存在”
		if(user == null) throw new UserException("学号或密码错误！请重新输入。");
		
		// 3. 比较user的password和form的password，如果不同，抛出异常，异常信息为“密码错误！”
		if(!form.getPassword().equals(user.getPassword())) throw new UserException("学号或密码错误！请重新输入。");		
		
		// 返回数据库中查询出来的user，而不是form，因为form中只有用户名和密码，而user是所有用户信息！
		return user;
	}
	
	
}
