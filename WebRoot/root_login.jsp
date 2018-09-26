<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>学生宿舍电量管理系统登录</title>
</head>
<body> 

<p style="padding-left:270px; font-size:70px;">学生宿舍电量管理系统</p>  

<table border="0" width="700px" style="margin-top:80px; margin-left:300px">  

<tr>
	<td rowspan="6" width="340px"><img src="images/1.jpg" height="200" style="margin-left:50px"></td> 
	<td style="padding-left:53px"><h1>管理员登录</h1></td>
</tr>

<form action="rootlogin" method="post">

<tr> 
  	<td align="left" height="30">工	号：<input type="text" name="rid" value="${root.rid }"/></td>	
</tr> 

<tr>
 	<td align="left" height="30">密	 码：<input type="password" name="rpassword" value="${root.rpassword }"/></td>
</tr>
   
<tr>
  	<td style="padding-left:80px" height="30"><input type="submit" value="登录" />&nbsp&nbsp<input type="reset" value="重置"/></td> 
</tr>

<tr>
  	<td height="30"><font color="red">${msg }</font></td> 
</tr>

</form>
</table>

</body>
</html>
