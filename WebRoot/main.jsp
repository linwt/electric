<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>学生宿舍电量管理系统主界面</title>

<link rel="stylesheet" type="text/css" href="jquery-easyui-1.3.3/themes/default/easyui.css">
<link rel="stylesheet" type="text/css" href="jquery-easyui-1.3.3/themes/icon.css">
<script type="text/javascript" src="jquery-easyui-1.3.3/jquery.min.js"></script>
<script type="text/javascript" src="jquery-easyui-1.3.3/jquery.easyui.min.js"></script>
<script type="text/javascript" src="jquery-easyui-1.3.3/locale/easyui-lang-zh_CN.js"></script>
<script type="text/javascript">
	$(function(){
		// 数据
		var treeData=[{
			text:"用户",
			children:[{
				text:"电量信息管理",
				attributes:{
					url:"electric.jsp"
				}
			}]
		}];
		
		// 实例化树菜单
		$("#tree").tree({
			data:treeData,
			lines:true,
			onClick:function(node){
				if(node.attributes){
					openTab(node.text,node.attributes.url);
				}
			}
		});
		
		// 新增Tab
		function openTab(text,url){
			if($("#tabs").tabs('exists',text)){
				$("#tabs").tabs('select',text);
			}else{
				var content="<iframe frameborder='0' scrolling='auto' style='width:100%;height:100%' src="+url+"></iframe>";
				$("#tabs").tabs('add',{
					title:text,
					closable:true,
					content:content
				});
			}
		}
	});
</script>
</head>
<body class="easyui-layout">

	<div region="north" style="height: 80px">
		<table border="0" width="1100px" height="80px" style="margin-left:40px"> 
			<tr> 
				<td width="100px"><img src="images/1.jpg" width="70px" height="70px"></td> 
				<td width="800px"><font size="10px">学生宿舍电量管理系统</font></td> 
			</tr>
		</table>  		
	</div>
	
	<div region="center">
		<div class="easyui-tabs" fit="true" border="false" id="tabs">
			<div title="首页" >
				<div align="center" style="padding-top: 100px;"><font color="red" size="10">欢迎使用本系统^_^</font></div>
			</div>
		</div>
	</div>
	
	<div region="west" style="width: 200px;" title="导航菜单" split="true">
		<ul id="tree"></ul>
	</div>	
	
	<div region="south" style="height:100px" title="基本信息" split="true" align="center">
		<table border="0" width="800px" style="margin-top:20px; align:center">
			<tr>
				<td style="heigth:40px; width:30px"><font size="4">当前用户：</font></td>  
				<td style="width:60px"><font size="4" color="red"><b>${currentUser.name }</b></font></td>
			
				<td style="heigth:40px; width:30px"><font size="4">所在宿舍：</font></td>
				<td style="width:60px"><font size="4" color="red"><b>${currentUser.fid }${currentUser.did }</b></font></td>
			
				<td style="heigth:40px; width:30px"><font size="4">欠费总额：</font></td>
				<td style="width:60px"><font size="4" color="red"><b>${currentUser.dmoney }元</b></font></td> 
			</tr>
		</table>
	</div>
	
</body>
</html>