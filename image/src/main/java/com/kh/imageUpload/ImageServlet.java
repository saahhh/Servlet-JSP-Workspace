package com.kh.imageUpload;

import java.io.IOException;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ImageServlet
 */
@WebServlet("/ImageServlet")
public class ImageServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String jdbcUrl = "jdbc:oracle:thin:@localhost:1521:xe";
		String jdbcUser = "khcafe";
		String jdbcPw = "kh1234";
		
		try {
			Class.forName("oracle.jdbc.OracleDriver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			Connection conn = DriverManager.getConnection(jdbcUrl, jdbcUser, jdbcPw);
			//sql
			String sql = "SELECT image FROM board";
			
			PreparedStatement ps = conn.prepareStatement(sql);
			
			ResultSet rs = ps.executeQuery();
			
			if(rs.next()) {
				//Result image�� �ش��ϴ� �����͸� ������ͼ� blob���� �����ϴ� ��
				//blob�� ���� ������ �츮�� image �����͸� sql���� blob�� �������شٰ� ��������� ������ blob��ü�� ������ �� ��
				Blob blob = rs.getBlob("image");
				//blob���� ��쿡�� �����̱� ������ ������ �ɰ��� �ɰ��� byteŸ������ ���� ���� �迭�� ���� byte���� ��� �����ϴ� ��
				//getBytes(1, (int) blob.length()) : 1���� ������ ������ �´ٴ� ��
				//getBytes(����, ����)
				byte[] imageData = blob.getBytes(1, (int) blob.length());
				//setContentType("image/jpeg") : ���� ������ image���� ��Ÿ���� 
				response.setContentType("image/jpeg");
				response.getOutputStream().write(imageData);
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}


}
