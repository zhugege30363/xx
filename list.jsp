<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<%@ taglib prefix="p" uri="http://edu.sxt.org/tag/page" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>显示所有的通讯录信息</title>
    
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
  <form action="findByPageServlet" method="get" name="form1">
  当前在线人数 ${applicationScope.count} 
   	<table width="900" border="1">
   		<tr>
   			<td>编号</td>
   			<td>姓名</td>
   			<td>email</td>
   		</tr>
   		<input type="hidden" name="action" id="action"/>
   		<c:forEach var="address" items="${pagelist}">
   		
   		<tr>
   			<td>${address.id}</td>
   			<td>${address.username}</td>
   			<td>${address.email}</td>
   		</tr>
   		
   		</c:forEach>
   		
   		<%-- 分页信息的展示--%>
   		<tr>   			
   			<td colspan="3">
   				<p:page pageName="pageBean" formName="form1"/>
   			</td>
   		</tr>
   	</table>
   	</form>
   	<a href="add.jsp">新增</a>
  </body>
</html>
