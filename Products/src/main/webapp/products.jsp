<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ page import="java.util.List" %>
<%@ page import="paginationDAO.ProductsDAO" %>
<%@ page import="paginationDAO.*" %>
 
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>��ǰ ���</title>
</head>
<body>
	<%
		//int pageNumber = (request.getParameter("page") !=null ) ? Integer.parseInt(s, beginIndex, endIndex, radix)
		int pageNumber = (request.getParameter("page") !=null ) ? Integer.parseInt(request.getParameter("page")) : 1;
		int pageSize = 2;
		
		ProductsDAO pDAO = new ProductsDAO();
		List<Products> pl = pDAO.getAllProducts(pageNumber, pageSize);
		
	%>
	<table border="1">
	<tr>
		<th>��ǰ �� : </th>
		<th>��ǰ �̸� : </th>
		<th>ī�װ� : </th>
	</tr>
	<%
		for(Product p : pl){
	%>
	<tr>
		<td><%= p.getProductId() %></td>
		<td><%= p.getProductName() %></td>
	</tr>
	</table>
	<%
		//1. ���������̼� ��ũ�� �������� ��, ��ũ�� page ���� ���� �ٸ��� ���� ���̴�.
		int totalProducts = pDAO.getTotalProducts(); //��ü ��ǰ�� ���ڸ� ������ ��
		int totalPages = (int) Math.ceil((double)totalProducts/pageSize);
		
		for(int i = 1; i <= totalPages; i++){
			
	%>
		<a href="<%= request.getRequestURI() %>?page<%= i %>"><%= i %></a>
	<%
		}
	%>
</body>
</html>