

import java.io.IOException;
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
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String jdbcURL = "jdbc:oracle:thin:@localhost:1521:xe";
		String jdbcUsername = "khcafe";
		String jdbcPassword = "kh1234";
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			Connection connection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
			//login
			//memail : 로그인할 때 아이디 값으로 사용할 이메일
			//mno : 로그인할 때 비밀번호 값으로 사용할 넘버
			String memail = request.getParameter("memail");
			String mno = request.getParameter("mno");
			
			//select문 사용해서 일치하는 유저가 존재하는지 확인해보기
			String sql = "SELECT * FROM MemberInfo WHERE MEmail = ? AND Mno = ?";
			PreparedStatement preparedstatement = connection.prepareStatement(sql);
			preparedstatement.setString(1, memail);
			preparedstatement.setString(2, mno);
			
			ResultSet resultSet = preparedstatement.executeQuery();
			
			//로그인을 하는 유저는 한명이기 떄문에 if문을 활용하여 하나의 유저만 조회한다.
			if(resultSet.next()) {
				//일치하는 값이 존재하면 로그인 성공에 따라 정보를 가지고 온다.
				//HttpSession : 웹에서 클라이언트와 서버 간에 정보를 유지하고 공유하는데 사용
				//현재 http요청에 대한 세션을 가지고 온다.
				//처음 http에 session 요청이 처음 오면 처음 올 때 세션이 없으면 새로운 세션을 생성하고 이미 세션이 존재하면 해당 세션을 가지고 온다.(이미 저장되어 있으면 가지고 온다)
				HttpSession session = request.getSession(); //임시적으로 저장해놓겠다
				session.setAttribute("mno", resultSet.getInt("MNO")); //db에서 가져온 mno정보를 저장하겠다
				session.setAttribute("mname", resultSet.getString("MName"));
				session.setAttribute("memail", memail);
				session.setAttribute("mbirth", resultSet.getDate("Mbirth"));
				
				//로그인 시간을 30분으로 설정, 30분 후 자동 로그아웃
				//Inactive : 비활성화, 활동하지 않는 상태
				//Interval : 간격
				session.setMaxInactiveInterval(1800); // 30분 = 1800초
				
				//로그인에 성공하면 성공에 대한 데이터를 login_success에 전달해준다
				response.sendRedirect("login_success.jsp");
				
			} else {
				//값이 존재하지 않으면 로그인 실패
				request.setAttribute("loginError", "true"); //만약에 로그인이 안된다면 속성에 loginError라는 이름으로 속성을 저장하고, loginError라는 이름을 정하고 loginError를 true로 설정을 했기 때문에 로그인 오류가 발생했음을 나타내는 속성이름과 속성값을 추가(저장)해주는 것 
				
				//getRequestDispatcher(경로) : 우리가 설정한 경로로 이동하기 위한 객체 반환이다.
				//forward(request, response) : 현재 페이지에 실행이 중단된다.
				//지금까지 가지고 있는 데이터를 클라이언트한테 응답으로 보내고 결과를 표시한다.
				request.getRequestDispatcher("home.jsp").forward(request, response); //실패할 시 메인으로 돌아가게함
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
