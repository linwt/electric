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

	function searchelec(){
		$('#tablehead').datagrid('load',{
			fid:$('#fid').val(),
			did:$('#did').val(),
			year:$('#year').val(),
			month:$('#month').val()
		});
	}
	
	function openPayDialog(){
		$("#paydiv").dialog("open").dialog("setTitle","交费信息");	
	}
	
	function closeDialog(){
		$("#pwdiv").dialog("close");
		$("#paydiv").dialog("close");
		resetValue();
	}
	
	function resetValue(){
		$("#oldPw").val("");
		$("#newPw").val("");
		$("#firmPw").val("");
		$("#pay").val("");
	}	
	
	function savePay(){
		$("#payform").form("submit",{
			url:"payservlet",
			onSubmit:function(){
				return $(this).form("validate");
			},
			success:function(result){
				var result = eval('('+result+')');
				if(result.errorMsg){
					$.messager.alert("系统提示",result.errorMsg);
					return;
				}else{
					$.messager.alert("系统提示","提交成功");
					resetValue();
					$("#paydiv").dialog("close");
					$("#tablehead").datagrid("reload");
				}
			}
		});
	}
	
	function openPwModifyDialog(){
		$("#pwdiv").dialog("open").dialog("setTitle","修改密码");
	}
	
	function saveChange(){
		$("#pwform").form("submit",{
			url:"changePw",
			onSubmit:function(){
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
					$("#pwdiv").dialog("close");
					$("#tablehead").datagrid("reload");
				}
			}
		});
	}
	
	$.extend($.fn.validatebox.defaults.rules, {   
		eqPassword:{
			validator:function(value, param){  
				return value == $(param[0]).val();   
			},   
		message:'两次输入密码不匹配'   
		}   
	});  
	
	</script>
  </head>
  
  <body style="margin:5px">
  
   	<table id="tablehead" title="电量信息" class="easyui-datagrid" fitColumns="false"
	 pagination="true" rownumbers="true" url="elecList" fit="true" toolbar="#fun">
		<thead>
			<tr>
				<!-- <th field="cb" checkbox="true"></th> -->
				<th field="fid" width="100" align="center">栋号</th>
				<th field="did" width="100" align="center">宿舍号</th>
				<th field="year" width="100" align="center">年份</th>
				<th field="month" width="100" align="center">月份</th>
				<th field="electric" width="100" align="center">用电度数</th> 
				<th field="fare" width="100" align="center">用电费用</th> 
			</tr>
		</thead>
	</table>
	
	<div id="fun">
		<div>
			<a href="javascript:openPayDialog()" class="easyui-linkbutton" iconCls="icon-add" plain="true">交电费</a>
			<a href="javascript:openPwModifyDialog()" class="easyui-linkbutton" iconCls="icon-edit" plain="true">修改密码</a>
		</div>
		<div>
			&nbsp;栋号：&nbsp;<input type="text" name="fid" id="fid" style="width:70px" />
			&nbsp;宿舍号：&nbsp;<input type="text" name="did" id="did" style="width:70px" />
			&nbsp;年份：&nbsp;<input type="text" name="year" id="year" style="width:70px" />
			&nbsp;月份：&nbsp;<input type="text" name="month" id="month" style="width:70px" />
			<a href="javascript:searchelec()" class="easyui-linkbutton" iconCls="icon-search" plain="true">搜索</a>
		</div>
	</div> 
	
	<div id="pwdiv" class="easyui-dialog" style="width: 400px;height: 280px;padding: 10px 20px"
		closed="true" buttons="#pwdiv-buttons">
		<form id="pwform" method="post">
			<table>
				<tr>
					<td style="height:30px">旧密码：</td>
					<td><input type="password" name="oldPw" id="oldPw" class="easyui-validatebox" required="true" missingMessage="请填写旧密码"/></td>
				</tr>
				<tr>
					<td style="height:30px">新密码：</td>
					<td><input type="password" name="newPw" id="newPw" class="easyui-validatebox" required="true" missingMessage="请填写新密码"/></td>
				</tr>
				<tr>
					<td style="height:30px">确认密码：</td>
					<td><input type="password" name="firmPw" id="firmPw" class="easyui-validatebox" required="true" missingMessage="请确认密码" validType="eqPassword['#newPw']" /></td>
				</tr>
			</table>
		</form>
	</div>
	
	<div id="pwdiv-buttons">
		<a href="javascript:saveChange()" class="easyui-linkbutton" iconCls="icon-ok">保存</a>
		<a href="javascript:closeDialog()" class="easyui-linkbutton" iconCls="icon-cancel">关闭</a>
	</div>
	
	<div id="paydiv" class="easyui-dialog" style="width:320px; height:200px;padding: 10px 20px"
		closed="true" buttons="#paydiv-buttons">
		<form id="payform" method="post">
			<table>
				<tr>
					<td style="height:30px">支付金额：</td>
					<td><input type="text" name="pay" id="pay" class="easyui-validatebox" required="true" missingMessage="请填写支付金额"/></td>
				</tr>
			</table>
		</form>
	</div>
	
	<div id="paydiv-buttons">
		<a href="javascript:savePay()" class="easyui-linkbutton" iconCls="icon-ok">提交</a>
		<a href="javascript:closeDialog()" class="easyui-linkbutton" iconCls="icon-cancel">关闭</a>
	</div>
	
  </body>
</html>
