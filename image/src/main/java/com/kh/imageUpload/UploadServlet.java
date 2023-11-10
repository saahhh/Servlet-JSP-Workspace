package com.kh.imageUpload;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

/**
 * Servlet implementation class UploadServlet
 */
@WebServlet("/UploadServlet")
@MultipartConfig
/*
@MultipartConfig(
		//���� �����͸� ��ũ�� ����� �����ϱ� ���� �޸𸮿� �����Ǵ� �ִ� ũ��(1MB)
		fileSizeThreshold = 1024 * 1024, 
		maxFileSize = 1024 * 1024 * 5, // ���ε��� ������ �ִ� ũ��(5MB)
		maxRequestSize = 1024 * 1024 * 10, //��û �������� �ִ� ũ��(10MB)
		location="/tmp" //������ ��ũ�� ����� �ӽ� ���͸�
		)
*/

//������ �ø��� ���� ���� ���� �����ϴ� ����
public class UploadServlet extends HttpServlet {

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String jdbcUrl = "jdbc:oracle:thin:@localhost:1521:xe";
		String jdbcUser = "khcafe";
		String jdbcPw = "kh1234";
		
		/*
		 ����ڰ� ��û�� �� �����͸� ó���ϴµ� ����ϴ� ���� (=request.getParameter(""))
		 jsp title �Ķ���͸� ������ͼ� title�̶�� ���ڿ� ������ �����ؼ� �� ������ ������ �Էµ� ������ ��Ÿ����.
		 String title = request.getParameter("title");
		 Part ����Ǿ��ִ� import
		*/
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		Part imagePart = request.getPart("image");
		
		try {
			Class.forName("oracle.jdbc.OracleDriver");
				
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block 
			e.printStackTrace();
		}
		
		try {
			Connection conn = DriverManager.getConnection(jdbcUrl, jdbcUser, jdbcPw);
			String sql = "INSERT INTO board (board_id, title, content, image, created_at, author)" + "VALUES (board_sequence.nextval, ?, ?, ?, ?, ?)"; //board id�� �������̸�.nextval=���� �������� �ʾƵ� �ڵ����� ��ȣ�� �ο���
			PreparedStatement ps = conn.prepareStatement(sql);
			//ps.setString(1, board_id); //�������� �ڵ� ��ȣ �ο�
			ps.setString(1, title);
			ps.setString(2, content);
			ps.setBinaryStream(3, imagePart.getInputStream(), (int) imagePart.getSize()); //getSize�� �⺻������ long�̱� ������ (int)�� �����ش�.
			ps.setTimestamp(4, new Timestamp(new Date().getTime())); //date -> util, timestamp -> sql import
			ps.setString(5, "author name");
			
			ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		response.sendRedirect("imageList.jsp");
		
	}

}
