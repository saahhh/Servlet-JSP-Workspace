package com.kh.product;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ProductServlet
 */
@WebServlet("/ProductServlet")
public class ProductServlet extends HttpServlet {
	
	private static final String jdbcURL = "jdbc:oracle:thin:@localhost:1521:xe";
	private static final String jdbcUsername = "khcafe";
	private static final String jdbcPassword = "kh1234";
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	Connection connection = null; //초기화해줘야 오류가 안남
	//데이터베이스 연결
	//Class.forName("oracle.jdbc.driver.OracleDriver"); - 구버전
	//Class.forName("oracle.jdbc.OracleDriver"); - 개편된 버전
	try {
		Class.forName("oracle.jdbc.OracleDriver");
		connection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
		
		//SQL 쿼리
		String sql = "SELECT * FROM products";
		PreparedStatement ps = connection.prepareStatement(sql);
		ResultSet resultSet = ps.executeQuery();
		
		//제품목록을 저장할 장바구니같은 ArrayList 생성
		ArrayList<Product> ProductList = new ArrayList<>();
		
		
		while (resultSet.next()) {		
			int productId = resultSet.getInt("product_id");
			String productName = resultSet.getString("product_name");
			String category = resultSet.getString("category");
			double price = resultSet.getDouble("price");
			int stockQuantity = resultSet.getInt("stock_quantity");
			
			Product product = new Product(productId, productName, category, price, stockQuantity);
			//ProductList에 제품들을 하나씩 add해서 넣어준다.
			ProductList.add(product);
			
		}
		
		//JSP 제품 목록 페이지로 제품 목록을 전달하자
		request.setAttribute("productList", ProductList);
		request.getRequestDispatcher("/productList.jsp").forward(request, response); //getRequestDispatcher : 성공할 떄도 사용
		
		
	} catch (ClassNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	}


}
