<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<%
	String rootPath=request.getContextPath();
	request.setAttribute("ctx",request.getContextPath());
%>

<script language="javascript" >
	var default_tab_title = "个人中心";
	var rootPath = '<%=rootPath%>';
	var imgDir = rootPath+'/images/';
</script>