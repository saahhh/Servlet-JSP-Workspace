<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ page import="java.util.List" %>
<%@ page import="paginationDAO.ProductsDAO" %>
<%@ page import="paginationDAO.*" %>
 
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>제품 목록</title>
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
		<th>제품 명 : </th>
		<th>제품 이름 : </th>
		<th>카테고리 : </th>
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
		//1. 페이지네이션 링크를 생성해줄 것, 링크는 page 값에 따라서 다르게 보일 것이다.
		int totalProducts = pDAO.getTotalProducts(); //전체 제품의 숫자를 가져온 것
		int totalPages = (int) Math.ceil((double)totalProducts/pageSize);
		
		for(int i = 1; i <= totalPages; i++){
			
	%>
		<a href="<%= request.getRequestURI() %>?page<%= i %>"><%= i %></a>
	<%
		}
	%>
</body>
</html>