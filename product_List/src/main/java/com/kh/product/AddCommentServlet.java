package com.kh.product;

import java.io.IOException;
import java.sql.Timestamp;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class AddCommentServlet
 */
@WebServlet("/AddCommentServlet")
public class AddCommentServlet extends HttpServlet {
	//��� �߰��ϱ� post
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//jsp ������ ������ ������ ������ ����
		int productId = Integer.parseInt(request.getParameter("productId"));
		String commenterName = request.getParameter("commenterName");
		String commentText = request.getParameter("commentText");
		Timestamp commentDate = new Timestamp(System.currentTimeMillis());
		
		//ProductCommnet �� ���� ��ü ���� �� �� ���
		ProductComment comment = new ProductComment(0, productId, commenterName, commentText, commentDate);
				
		//ProductDAO�� ����ؼ� ��� �߰�
		ProductDAO productDAO = new ProductDAO();
		productDAO.addcomment(comment);
		
		//��� �߰��� ���� �� �������� �ٽ� �����ϴ� redirect �ۼ�
		response.sendRedirect("ProductDetailPage.jsp?ProductId=" + productId);
	}

}
