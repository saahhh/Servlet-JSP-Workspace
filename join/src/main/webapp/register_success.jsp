<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ȸ������ �����ϼ̽��ϴ�.</title>
</head>
<body>
<h3>ȸ�����Կ� ����!</h3>
<h4>ȸ������ ���� : </h4>
<p> ȸ����ȣ : <%= session.getAttribute("mno")%></p>
<p> �̸� : <%= session.getAttribute("mname")%></p>
<p> �̸��� : <%= session.getAttribute("memail")%></p>
<p> ������� : <%= session.getAttribute("mbirth")%></p>
</body>
</html>