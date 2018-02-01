<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'add.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
  
  <body>
  <form action="findAllServlet" method="get" name="form1">
   	<table width="400" border="1">
   		<tr>
   			<input type="hidden" name="action" value="addAddress"/>
   			<td>姓名</td> <td><input type="text" name="username"/></td>
   			
   		</tr>
   		<tr>
   			<td>email</td><td><input type="text" name="email"/>
   		</tr>
   		<tr>
   			<td colspan="2"><input type="submit" value="保存"/></td>
   		</tr>
   		</table>
   </form>		
  </body>
</html>