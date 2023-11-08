<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원가입 성공하셨습니다.</title>
</head>
<body>
<h3>회원가입에 성공!</h3>
<h4>회원가입 정보 : </h4>
<p> 회원번호 : <%= session.getAttribute("mno")%></p>
<p> 이름 : <%= session.getAttribute("mname")%></p>
<p> 이메일 : <%= session.getAttribute("memail")%></p>
<p> 생년월일 : <%= session.getAttribute("mbirth")%></p>
</body>
</html>