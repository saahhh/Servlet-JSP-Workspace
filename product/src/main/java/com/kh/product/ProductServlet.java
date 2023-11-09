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
		
	Connection connection = null; //�ʱ�ȭ����� ������ �ȳ�
	//�����ͺ��̽� ����
	//Class.forName("oracle.jdbc.driver.OracleDriver"); - ������
	//Class.forName("oracle.jdbc.OracleDriver"); - ����� ����
	try {
		Class.forName("oracle.jdbc.OracleDriver");
		connection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
		
		//SQL ����
		String sql = "SELECT * FROM products";
		PreparedStatement ps = connection.prepareStatement(sql);
		ResultSet resultSet = ps.executeQuery();
		
		//��ǰ����� ������ ��ٱ��ϰ��� ArrayList ����
		ArrayList<Product> ProductList = new ArrayList<>();
		
		
		while (resultSet.next()) {		
			int productId = resultSet.getInt("product_id");
			String productName = resultSet.getString("product_name");
			String category = resultSet.getString("category");
			double price = resultSet.getDouble("price");
			int stockQuantity = resultSet.getInt("stock_quantity");
			
			Product product = new Product(productId, productName, category, price, stockQuantity);
			//ProductList�� ��ǰ���� �ϳ��� add�ؼ� �־��ش�.
			ProductList.add(product);
			
		}
		
		//JSP ��ǰ ��� �������� ��ǰ ����� ��������
		request.setAttribute("productList", ProductList);
		request.getRequestDispatcher("/productList.jsp").forward(request, response); //getRequestDispatcher : ������ ���� ���
		
		
	} catch (ClassNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	}


}
