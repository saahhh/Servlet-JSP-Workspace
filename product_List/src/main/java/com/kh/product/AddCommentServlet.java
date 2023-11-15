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
	//댓글 추가니까 post
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//jsp 폼에서 전송한 데이터 가지고 오기
		int productId = Integer.parseInt(request.getParameter("productId"));
		String commenterName = request.getParameter("commenterName");
		String commentText = request.getParameter("commentText");
		Timestamp commentDate = new Timestamp(System.currentTimeMillis());
		
		//ProductCommnet 에 대한 객체 생성 후 값 담기
		ProductComment comment = new ProductComment(0, productId, commenterName, commentText, commentDate);
				
		//ProductDAO를 사용해서 댓글 추가
		ProductDAO productDAO = new ProductDAO();
		productDAO.addcomment(comment);
		
		//댓글 추가한 다음 상세 페이지로 다시 전송하는 redirect 작성
		response.sendRedirect("ProductDetailPage.jsp?ProductId=" + productId);
	}

}
