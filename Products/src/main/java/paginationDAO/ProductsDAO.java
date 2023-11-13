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
	
	//1. ��� ��ǰ�� ������ ���� �޼���
	//������ ��ȣ�� ������ ũ�⸦ �޾ƿͼ� ����Ʈ�� �߰��ϴ� ������ �ϴ� �޼���
	public List<Product> getAllProducts(int pageNumber, int pageSize){
		List<Product> productList = 		
	}
	
	
	
	
	//2. ��ü ��ǰ ���� ������ ���� �޼���(�ܼ��� �� ���ڸ� ������ ���� ��)
	public int getTotalProducts() {
		int totalProducts = 0;
		try {
			Class.forName("oracle.jdbc.OracleDriver");
			Connection conn = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword); //} catch (Exception e) �� �ٲٸ� ���������
			
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
