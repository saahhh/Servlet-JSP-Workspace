<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Ȩ������</title>
<script>
	function displayLoginFail(){
		alert("�α��ο� �����Ͽ����ϴ�."); 
	}
</script>
</head>
<body>
<h2>Ȩ�������� ���Ű� ȯ���մϴ�.</h2>


<%

	if(session.getAttribute("mno") != null) {		
%>
	<a href="logout.jsp">�α׾ƿ�</a>
<%
	} else {	
%>
	<a href="login.jsp">�α���</a>
<%
	}
%>

<%

	String loginError = (String)request.getAttribute("loginError");
	

	if(loginError != null) {	
%>
<script>
	displayLoginFail();
</script>
<%
	}
%>





</body>
</html>