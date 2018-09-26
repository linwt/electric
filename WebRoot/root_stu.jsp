<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <title>电量信息管理</title>
	<link rel="stylesheet" type="text/css" href="jquery-easyui-1.3.3/themes/default/easyui.css">
	<link rel="stylesheet" type="text/css" href="jquery-easyui-1.3.3/themes/icon.css">
	<script type="text/javascript" src="jquery-easyui-1.3.3/jquery.min.js"></script>
	<script type="text/javascript" src="jquery-easyui-1.3.3/jquery.easyui.min.js"></script>
	<script type="text/javascript" src="jquery-easyui-1.3.3/locale/easyui-lang-zh_CN.js"></script>
	<script type="text/javascript">
	
	var url;
	
	function resetValue(){
		$("#stuid").val("");
		$("#name").val("");
		$("#sex").combobox("setValue","");
		$("#age").val("");
		$("#depart").val("");
		$("#major").val("");
		$("#fid").val("");
		$("#did").val("");
		$("#phone").val(""); 
	}
	
	function closeDialog(){
		$("#add_edit_stu").dialog("close");
		resetValue();
	}

	function searchstu(){
		$('#tablehead').datagrid('load',{
			stuid:$('#fun_stuid').val(),
			fid:$('#fun_fid').val(),
			did:$('#fun_did').val()
		});
	} 
	
	function openStudentAddDialog(){
		$("#add_edit_stu").dialog("open").dialog("setTitle","添加学生信息");
		url="stusave?choose=a";
	}
	
	function openStudentModifyDialog(){
		var selectedRows=$("#tablehead").datagrid('getSelections');
		if(selectedRows.length!=1){
			$.messager.alert("系统提示","请选择一条要编辑的数据！");
			return;
		}
		var row=selectedRows[0];
		$("#add_edit_stu").dialog("open").dialog("setTitle","编辑学生信息");
		$("#add_edit_form").form("load",row);
		document.getElementById("stuid").readOnly="true";
		url="stusave?choose=m";
	}
	
	function saveStudent(){
		$("#add_edit_form").form("submit",{
			url:url,
			onSubmit:function(){
				if($('#sex').combobox("getValue")==""){
					$.messager.alert("系统提示","请选择性别");
					return false;
				}
				return $(this).form("validate");
			},
			success:function(result){
				var result = eval('('+result+')');
				if(result.errorMsg){
					$.messager.alert("系统提示",result.errorMsg);
					return;
				}else{
					$.messager.alert("系统提示","保存成功");
					resetValue();
					$("#add_edit_stu").dialog("close");
					$("#tablehead").datagrid("reload");
				}
			}
		});
	}

	
	function deleteStudent(){
		var selectedRows=$("#tablehead").datagrid('getSelections');
		if(selectedRows.length==0){
			$.messager.alert("系统提示","请选择要删除的数据！");
			return;
		}
		var stuids=[];
		for(var i=0;i<selectedRows.length;i++){
			stuids.push(selectedRows[i].stuid);
		}
		var ids=stuids.join(",");
		$.messager.confirm("系统提示","您确认要删掉这<font color=red>"+selectedRows.length+"</font>条数据吗？",function(r){
			if(r){
				$.post("studel",{delIds:ids},function(result){
					if(result.success){
						$.messager.alert("系统提示","您已成功删除<font color=red>"+result.delNums+"</font>条数据！");
						$("#tablehead").datagrid("reload");
					}else{
						$.messager.alert('系统提示',result.errorMsg);
					}
				},"json");
			}
		});
	}
	
	
	$.extend($.fn.validatebox.defaults.rules, {   
		eqPassword:{
			validator:function(value, param){  
				return value == $(param[0]).val();   
			},   
		message:''   
		}   
	});  
	
	</script>
  </head>
  
  <body style="margin:5px">
  
   	<table id="tablehead" title="学生信息" class="easyui-datagrid" fitColumns="false"
	 pagination="true" rownumbers="true" url="stuList" fit="true" toolbar="#fun">
		<thead>
			<tr>
				<th field="cb" checkbox="true"></th>
				<th field="stuid" width="120" align="center">学号</th>
				<th field="name" width="120" align="center">姓名</th>
				<th field="sex" width="120" align="center">性别</th>
				<th field="age" width="120" align="center">年龄</th>
				<th field="depart" width="120" align="center">系别</th> 
				<th field="major" width="120" align="center">专业</th> 
				<th field="fid" width="120" align="center">栋号</th>
				<th field="did" width="120" align="center">宿舍号</th>
				<th field="phone" width="120" align="center">手机号</th>
			</tr>
		</thead>
	</table>
	
	<div id="fun">
		<div>
			<a href="javascript:openStudentAddDialog()" class="easyui-linkbutton" iconCls="icon-add" plain="true">添加</a>
			<a href="javascript:openStudentModifyDialog()" class="easyui-linkbutton" iconCls="icon-edit" plain="true">修改</a>
			<a href="javascript:deleteStudent()" class="easyui-linkbutton" iconCls="icon-remove" plain="true">删除</a>
			<!-- <a href="javascript:openPwModifyDialog()" class="easyui-linkbutton" iconCls="icon-edit" plain="true">修改密码</a> -->
		</div>
		<div>
			&nbsp;学号：&nbsp;<input type="text" name="fun_stuid" id="fun_stuid" style="width:100px" />
			&nbsp;栋号：&nbsp;<input type="text" name="fun_fid" id="fun_fid" style="width:70px" />
			&nbsp;宿舍号：&nbsp;<input type="text" name="fun_did" id="fun_did" style="width:70px" />
			<a href="javascript:searchstu()" class="easyui-linkbutton" iconCls="icon-search" plain="true">搜索</a>
		</div>
	</div> 
	
	<div id="add_edit_stu" class="easyui-dialog" style="width:600px; height:320px; padding: 10px 20px" closed="true" buttons="#add_edit_stu-buttons">
		<form id="add_edit_form" method="post">
			<table border="0">
				<tr>
					<td width="140px" height="40px">学　号：</td>
					<td width="290px"><input type="text" name="stuid" id="stuid" class="easyui-validatebox" required="true"/></td>
					<td width="130px">姓　名：</td>
					<td width="250px"><input type="text" name="name" id="name" class="easyui-validatebox" required="true"/></td>
				</tr>
				<tr>
					<td height="40px">性　别：</td>
					<td>
						<select class="easyui-combobox" id="sex" name="sex" editable="false" panelHeight="auto" style="width: 155px">
						    <option value="">请选择...</option>
							<option value="男">男</option>
							<option value="女">女</option>
						</select>
					</td>
					<td>年　龄：</td>
					<td><input type="text" name="age" id="age" class="easyui-validatebox" required="true"/></td>
				</tr>
				<tr>
					<td height="40px">系　别：</td>
					<td><input type="text" name="depart" id="depart" class="easyui-validatebox" required="true"/></td>
					<td>专　业:</td>
					<td><input type="text" name="major" id="major" class="easyui-validatebox" required="true" /></td>
				</tr>
				<tr>
					<td height="40px">栋　号：</td>
					<td><input type="text" name="fid" id="fid" class="easyui-validatebox" required="true"/></td>
					<td>宿舍号:</td>
					<td><input type="text" name="did" id="did" class="easyui-validatebox" required="true" /></td>
				</tr>
				<tr>
					<td height="40px">手机号：</td>
					<td><input type="text" name="phone" id="phone" class="easyui-validatebox" required="true" /></td>
				</tr>
			</table>
		</form>
	</div>
	
	<div id="add_edit_stu-buttons">
		<a href="javascript:saveStudent()" class="easyui-linkbutton" iconCls="icon-ok">保存</a>
		<a href="javascript:closeDialog()" class="easyui-linkbutton" iconCls="icon-cancel">关闭</a>
	</div>
	
  </body>
</html>
