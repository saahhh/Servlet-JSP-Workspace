<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>�̹��� ���</title>
</head>
<body>
	<h1>�̹��� ���ε�</h1>
	<!-- 
		action : form�����Ͱ� ����� �� �ش� �����͸� ó���� ������ �����ϴ� �Ӽ�
				������ �������� form�����Ͱ� ���۵ȴ�.
		method : method="post" �������͸� ������ ������ �� HTTP �޼��带 �����ϴ� �Ӽ�
		enctype : �� �����Ͱ� ���� ���ε�� ���� ���̳ʸ� �����͸� ������ �� ���Ǵ� ���ڵ� Ÿ���� �����ϴ� �Ӽ�
					multipart/form-data : ���� ���ε带 ���� ǥ�� ���ڵ� ���
					�����̳� �Ϲ� �ؽ�Ʈ�� �Բ� ������ �� �ִ�.
	 -->
	<form action="UploadServlet" method="post" enctype="multipart/form-data">
		<label for="title">���� : </label>
		<input type="text" name="title" id="title" required><br>
		<label for="content">���� : </label>
		<input type="text" name="content" id="content" required><br>
		
		<label for="image">�̹��� : </label>
		<input type="file" name="file" id="file" required><br>
		
		<button type="submit">���ε�</button>
		
	</form>
</body>
</html>
<!--required: �ʼ��� ����� �ϴ� ���� �����ش�
	blob :�̹����� �����ϴ� ����(��ġ)�� ��Ȯ���� ��
			�� ���� 2G~4G���� ���尡��
			������������ blob�� ����ϴ� ������ ��ġ�� ��Ȯ���ϱ� ���� -->