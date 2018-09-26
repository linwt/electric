<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>学生宿舍电量管理系统注册</title>
</head>
<body> 

<p style="padding-left:270px; font-size:70px;">学生宿舍电量管理系统</p>  

<table border="0" width="800px" style="margin-top:80px; margin-left:300px">    

	<tr>
		<td rowspan="5" width="340px"><img src="images/1.jpg" height="200" style="margin-left:50px"></td> 
		<td style="padding-left:55px"><h1>管理员注册</h1></td>
	</tr>
 
	<form action="rootregist" method="post">

		<tr> 
			<td align="left" height="30">工　号：<input type="text" name="rid" value="${root.rid }"/>&nbsp<font color="red">${r_errors.rid }${msg }</font></td>	
		</tr> 
		 
		<tr>
			<td align="left" height="30">用户名：<input type="text" name="rname" value="${root.rname }"/>&nbsp<font color="red">${r_errors.rname }</font></td>
		</tr>
 
		<tr>
			<td align="left" height="30">密　码：<input type="password" name="rpassword" value="${root.rpassword }"/>&nbsp<font color="red">${r_errors.rpassword }</font></td>
		</tr>
		   
		<tr>
			<td style="padding-left:80px" height="30"><input type="submit" value="注册" /> &nbsp&nbsp <input type="reset" value="重置"/></td> 
		</tr>

	</form>
	
</table>

</body>
</html>