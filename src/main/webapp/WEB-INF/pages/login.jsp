<%@ page language="java" contentType="application/json; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/common/taglibs.jsp"%>

{"title":"登陆结果", "message":"${result eq true ? "登陆成功" : "登录失败"}", "status" : "${result}"}