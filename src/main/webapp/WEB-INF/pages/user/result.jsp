<%@ page language="java" contentType="application/json; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/common/taglibs.jsp"%>

{"title":"操作结果", "message":"${result eq true ? "操作成功" : "操作失败"}", "status" : "${result}"}