<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
	<script type="text/javascript">
		function goToPage(){
			document.getElementById("action").value="findByPage";
			form1.submit();
		}
		function toPage(num){
			document.getElementById("curPage").value=num;
			
			goToPage();
		}
	
	</script>
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
   				总共${pageBean.totalSize}条|总共${pageBean.totalPage}页
   				<c:choose>
   				  <c:when test="${pageBean.currentPage==1}">
   				            首页|上一页|
   				      <a href="javascript:toPage(${pageBean.currentPage+1})">下一页</a>|
   				      <a href="javascript:toPage(${pageBean.totalPage})">末页</a>
   				  </c:when>
   				<c:when test="${pageBean.currentPage==pageBean.totalPage}">
   				          <a href="javascript:toPage(1)">首页</a>|
   				          <a href="javascript:toPage(${pageBean.currentPage-1})">上一页</a>|
   				                       下一页|末页
   				  </c:when>
   				
   				         <c:otherwise>
   				          <a href="javascript:toPage(1)">首页</a>|
   				          <a href="javascript:toPage(${pageBean.currentPage-1})">上一页</a>|
   			              <a href="javascript:toPage(${pageBean.currentPage+1})">下一页</a>|
   				          <a href="javascript:toPage(${pageBean.totalPage})">末页</a>
   				         </c:otherwise>
   				         </c:choose>
   				每页<input type="text" name="pageSize" value="${pageBean.pageSize}"/>条
   				第<input type="text" id="curPage" name="currentPage" value="${pageBean.currentPage}"/>页
   				<input type="button" value="go" onclick="goToPage()"/>
   			</td>
   		</tr>
   	</table>
   	</form>
   	<a href="add.jsp">新增</a>
  </body>
</html>
