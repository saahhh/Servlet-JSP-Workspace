<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>�α���</title>
</head>
<body>
<h3>�α���</h3>
<form action="LoginServlet" method="post">
	<label for="memail">�̸��� : </label>
	<input type="email" id="meamil" name="memail" required><br>
	
	<label for="mno">�α����� ��й�ȣ �ѹ� : </label>
	<input type="password" id="mno" name="mno" required><br>
	
	<input type="submit" value="�α���">
	
</form>
</body>
</html>
