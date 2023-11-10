<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>이미지 목록</title>
</head>
<body>
	<h1>이미지 업로드</h1>
	<!-- 
		action : form데이터가 제출될 때 해당 데이터를 처리할 서블릿을 지정하는 속성
				지정된 서블릿으로 form데이터가 전송된다.
		method : method="post" 폼데이터를 서버로 제출할 때 HTTP 메서드를 지정하는 속성
		enctype : 폼 데이터가 파일 업로드와 같은 바이너리 데이터를 포함할 때 사용되는 인코딩 타입을 지정하는 속성
					multipart/form-data : 파일 업로드를 위한 표준 인코딩 방식
					파일이나 일반 텍스트를 함께 전송할 수 있다.
	 -->
	<form action="UploadServlet" method="post" enctype="multipart/form-data">
		<label for="title">제목 : </label>
		<input type="text" name="title" id="title" required><br>
		<label for="content">내용 : </label>
		<input type="text" name="content" id="content" required><br>
		
		<label for="image">이미지 : </label>
		<input type="file" name="file" id="file" required><br>
		
		<button type="submit">업로드</button>
		
	</form>
</body>
</html>
<!--required: 필수로 적어야 하는 곳에 적어준다
	blob :이미지를 저장하는 공간(위치)이 불확실할 때
			한 열당 2G~4G까지 저장가능
			세미플젝에서 blob을 사용하는 이유는 위치가 불확실하기 때문 -->