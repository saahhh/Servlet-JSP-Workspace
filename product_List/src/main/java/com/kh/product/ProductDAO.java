package com.kh.product;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class ProductDAO {
	private static final String jdbcURL = "jdbc:oracle:thin:@localhost:1521:xe";
	private static final String jdbcUsername = "khcafe";
	private static final String jdbcPassword = "kh1234";
	
	//Class.forName("oracle.jdbc.OracleDriver"); 실행해주는게 없어서 생성자 만들어줘야함
	public ProductDAO() {
		try {
			Class.forName("oracle.jdbc.OracleDriver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public List<Product> getAllProducts() { //getAllProducts() : 전부다 가지고 오는 것
		List<Product> products = new ArrayList<>();
		try {
			Connection connection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
			String sql = "SELECT * FROM products";
			PreparedStatement ps = connection.prepareStatement(sql);
			
			ResultSet resultSet = ps.executeQuery();
			
			while(resultSet.next()) {
				int productId = resultSet.getInt("product_id");
				String productName = resultSet.getString("product_name");
				String category = resultSet.getString("category");
				double price = resultSet.getDouble("price");
				int stockQuantity = resultSet.getInt("stock_quantity");
				
				Product product = new Product(productId, productName, category, price, stockQuantity);
				products.add(product);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return products;
		
	}
	
	public Product getProductId(int ProductId) {
		
		Product product = null;
		//select해서 한 개만 볼 수 있는 쿼리 작성하고 new product 이용해서 값 가져오기
		try {
			Connection connection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
			String sql = "SELECT * FROM products WHERE product_id = ?";
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setInt(1, ProductId);
			
			ResultSet resultSet = ps.executeQuery();
			
			if(resultSet.next()) {
				ProductId = resultSet.getInt("product_id");
				String productName = resultSet.getString("product_name");
				String category = resultSet.getString("category");
				double price = resultSet.getDouble("price");
				int stockQuantity = resultSet.getInt("stock_quantity");
				
				product = new Product(ProductId, productName, category, price, stockQuantity);
				
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return product;
	}
	private int getProductId() {
		// TODO Auto-generated method stub
		return 0;
	}
	
    //업데이트를 하기 위한 쿼리문 추가
    public void updateProduct(Product product) {
        String UPDATE_PRODUCT = "UPDATE products SET product_name=?, category=?, price=?, stock_quantity=? WHERE product_id=?";
    	try {
			Connection conn = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
			PreparedStatement ps = conn.prepareStatement(UPDATE_PRODUCT);
			ps.setString(1, product.getProductName());
			ps.setString(2, product.getCategory());
			ps.setDouble(3, product.getPrice());
			ps.setInt(4, product.getstockQuantity());
			ps.setInt(5, product.getProductId());
			
			ps.executeUpdate();
    	} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    }
    
    //댓글을 추가하는 DB문, INSERT문
    public void addcomment(ProductComment comment) {
    	try {
			Connection connection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
			//CURRENT_TIMESTAMP = 현재 시간을 알려주겠다
			String sql = "INSERT INTO product_comments (comment_id, product_id, commenter_name, comment_text, comment_date) VALUES (?, ?, ?, ?, CURRENT_TIMESTAMP)";
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setInt(1, comment.getProductId()); //ProductComment comment에 대한 값을 받아와야하기 때문
			ps.setInt(2, comment.getProductId());
			ps.setString(3, comment.getCommenterName());
			ps.setString(4, comment.getCommentText());
			
			ps.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    }
    
    //댓글 전체 출력하는 메서드
    public ArrayList<ProductComment> getCommentsByProductId(int ProductId) {
    	ArrayList<ProductComment> commentList = new ArrayList<>();
    	
    	try {
			Connection connection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
			
			String sql = "SELECT * FROM product_comments WHERE product_id = ?";
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setInt(1, ProductId); //ProductId 하나로 전체를 조회
			
			ResultSet resultSet = ps.executeQuery(); //결과를 내주고
			
			while(resultSet.next()) { //전체조회
				int commentId = resultSet.getInt("comment_Id");
				int productId = resultSet.getInt("product_Id");
				String commenterName = resultSet.getString("commenterName");
				String commentText = resultSet.getString("commentText");
				Timestamp commentDate = resultSet.getTimestamp("comment_date");
				
				ProductComment comment = new ProductComment(commentId, productId, commenterName, commentText, commentDate);
				
				commentList.add(comment); //리스트에 추가해주기
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    	return commentList; //getCommentsByProductId(int ProductId)의 오류를 없애주기 위해 먼저 생성..
    	
    }
}
