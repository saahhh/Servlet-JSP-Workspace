<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>    
<%@ page import="com.kh.product.Product" %>
<%@ page import="com.kh.product.ProductDAO" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>제품 상세 페이지</title>
</head>
<body>
<h1>제품 상세 정보</h1>

<!-- 내 db에서 가져와야하기 때문에 -->
<%
	//String = id 값 가지고 오겠다.
	String productIdValue = request.getParameter("productId");
	int productId = Integer.parseInt(productIdValue);
	//DAO 작업
	ProductDAO productDAO = new ProductDAO();
	Product product = productDAO.getProductId(productId);
	
%>
<p>제품 ID : <%= product.getProductId() %>
<p>제품명 : <%= product.getProductName() %>
<P>카테고리 : <%=product.getCategory() %>
<p>가격 : <%=product.getPrice() %>
<p>재고수량 : <%=product.getstockQuantity() %>
</body>
</html>