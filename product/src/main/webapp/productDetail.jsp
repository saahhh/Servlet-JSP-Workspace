<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>    
<%@ page import="com.kh.product.Product" %>
<%@ page import="com.kh.product.ProductDAO" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>��ǰ �� ������</title>
</head>
<body>
<h1>��ǰ �� ����</h1>

<!-- �� db���� �����;��ϱ� ������ -->
<%
	//String = id �� ������ ���ڴ�.
	String productIdValue = request.getParameter("productId");
	int productId = Integer.parseInt(productIdValue);
	//DAO �۾�
	ProductDAO productDAO = new ProductDAO();
	Product product = productDAO.getProductId(productId);
	
%>
<p>��ǰ ID : <%= product.getProductId() %>
<p>��ǰ�� : <%= product.getProductName() %>
<P>ī�װ� : <%=product.getCategory() %>
<p>���� : <%=product.getPrice() %>
<p>������ : <%=product.getstockQuantity() %>
</body>
</html>