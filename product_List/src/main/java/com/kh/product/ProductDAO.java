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
	
	//Class.forName("oracle.jdbc.OracleDriver"); �������ִ°� ��� ������ ����������
	public ProductDAO() {
		try {
			Class.forName("oracle.jdbc.OracleDriver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public List<Product> getAllProducts() { //getAllProducts() : ���δ� ������ ���� ��
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
		//select�ؼ� �� ���� �� �� �ִ� ���� �ۼ��ϰ� new product �̿��ؼ� �� ��������
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
	
    //������Ʈ�� �ϱ� ���� ������ �߰�
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
    
    //����� �߰��ϴ� DB��, INSERT��
    public void addcomment(ProductComment comment) {
    	try {
			Connection connection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
			//CURRENT_TIMESTAMP = ���� �ð��� �˷��ְڴ�
			String sql = "INSERT INTO product_comments (comment_id, product_id, commenter_name, comment_text, comment_date) VALUES (?, ?, ?, ?, CURRENT_TIMESTAMP)";
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setInt(1, comment.getProductId()); //ProductComment comment�� ���� ���� �޾ƿ;��ϱ� ����
			ps.setInt(2, comment.getProductId());
			ps.setString(3, comment.getCommenterName());
			ps.setString(4, comment.getCommentText());
			
			ps.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    }
    
    //��� ��ü ����ϴ� �޼���
    public ArrayList<ProductComment> getCommentsByProductId(int ProductId) {
    	ArrayList<ProductComment> commentList = new ArrayList<>();
    	
    	try {
			Connection connection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
			
			String sql = "SELECT * FROM product_comments WHERE product_id = ?";
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setInt(1, ProductId); //ProductId �ϳ��� ��ü�� ��ȸ
			
			ResultSet resultSet = ps.executeQuery(); //����� ���ְ�
			
			while(resultSet.next()) { //��ü��ȸ
				int commentId = resultSet.getInt("comment_Id");
				int productId = resultSet.getInt("product_Id");
				String commenterName = resultSet.getString("commenterName");
				String commentText = resultSet.getString("commentText");
				Timestamp commentDate = resultSet.getTimestamp("comment_date");
				
				ProductComment comment = new ProductComment(commentId, productId, commenterName, commentText, commentDate);
				
				commentList.add(comment); //����Ʈ�� �߰����ֱ�
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    	return commentList; //getCommentsByProductId(int ProductId)�� ������ �����ֱ� ���� ���� ����..
    	
    }
}
