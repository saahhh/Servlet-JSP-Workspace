package paginationDAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

import paginationDAO.Products.Product;

public class ProductsDAO {

	private static final String jdbcURL = "jdbc:oracle:thin:@localhost:1521:xe";
	private static final String jdbcUsername = "khcafe";
	private static final String jdbcPassword = "kh1234";
	
	//1. 모든 제품을 가지고 오는 메서드
	//페이지 번호와 페이지 크기를 받아와서 리스트에 추가하는 역할을 하는 메서드
	public List<Product> getAllProducts(int pageNumber, int pageSize){
		List<Product> productList = 		
	}
	
	
	
	
	//2. 전체 제품 수를 가지고 오는 메서드(단순한 총 숫자만 가지고 오는 것)
	public int getTotalProducts() {
		int totalProducts = 0;
		try {
			Class.forName("oracle.jdbc.OracleDriver");
			Connection conn = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword); //} catch (Exception e) 로 바꾸면 오류사라짐
			
			String sql = "SELECT COUNT(*) AS total FROM products";
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			
			if(rs.next()) {
				totalProducts = rs.getInt("total");
				
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return totalProducts;
	}
	
}
