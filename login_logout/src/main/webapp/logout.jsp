<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<% 
	//������ ���ּ� �α׾ƿ� ó���ϱ�
	session.invalidate();
	response.sendRedirect("home.jsp");
%>