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
	function searchfloor(){
		$('#tablehead').datagrid('load',{
			fid:$('#fun_fid').val(),
		});
	}
	
	</script>

  </head>
  
  <body style="margin:5px">
    <table id="tablehead" title="宿舍楼信息信息" class="easyui-datagrid" fitColumns="false"
	 pagination="true" rownumbers="true" url="floorList" fit="true" toolbar="#fun">
		<thead>
			<tr>
				<th field="cb" checkbox="true"></th>
				<th field="fid" width="120" align="center">栋号</th>
				<th field="flevel" width="120" align="center">楼层数</th> 
				<th field="froom" width="120" align="center">房间数</th> 
				<th field="fexpect" width="120" align="center">可住人数</th>
				<th field="freal" width="120" align="center">实住人数</th>
			</tr>
		</thead>
	</table>
	
	<div id="fun">
		<div>			
			&nbsp;栋号：&nbsp;<input type="text" name="fun_fid" id="fun_fid" style="width:70px" />
			<a href="javascript:searchfloor()" class="easyui-linkbutton" iconCls="icon-search" plain="true">搜索</a>
		</div>
	</div> 
  </body>
</html>
