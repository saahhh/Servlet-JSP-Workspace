package paginationDAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;



public class ProductsDAO {

	private static final String jdbcURL = "jdbc:oracle:thin:@localhost:1521:xe";
	private static final String jdbcUsername = "khcafe";
	private static final String jdbcPassword = "kh1234";
	
	//1. 모든 제품을 가지고 오는 메서드
	//페이지 번호와 페이지 크기를 받아와서 리스트에 추가하는 역할을 하는 메서드
	public List<Products> getAllProducts(int pageNumber, int pageSize){
		List<Products> productList = new ArrayList<>();
		//시작 페이지와 끝 페이지를 위한 변수 처리
		int start = PaginationUtil.paginationStart(pageNumber, pageSize);
		int end = PaginationUtil.paginationEnd(pageNumber, pageSize);
		
		try {
			Class.forName("oracle.jdbc.OracleDriver");
			Connection conn = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
			
			 String sql = "SELECT * FROM (SELECT p.*, ROWNUM AS rnum FROM (SELECT * FROM products ORDER BY product_id) p WHERE ROWNUM <= ?) WHERE rnum >= ?";
			/*
			String sql ="SELECT * FROM products ORDER BY product_id OFFSET ? ROWS FETCH NEXT ? ROWS ONLY";
			
			 SELECT * FROM products : products 테이블에서
			 ORDER BY product_id : product_id 기준으로 정렬할 것임
			 지정된 OFFSET FETCH NEXT 페이지를 가지고 옴
			 
			 OFFSET : 가져오기를 시작할 행의 위치를 나타냄
			 
			 ROWS FETCH NEXT ? : 다음에 가져올 행의 수를 지정
			 ROWS ONLY : 
			 */
			
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, start);
			ps.setInt(2, end);
			
			ResultSet rs = ps.executeQuery();
			
			while (rs.next()) {
				Products products = new Products();
				products.setProductId(rs.getInt("product_id"));
				products.setProductName(rs.getString("product_name"));
				productList.add(products);
				
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return productList;
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
