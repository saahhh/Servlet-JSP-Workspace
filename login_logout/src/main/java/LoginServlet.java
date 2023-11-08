

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
			//memail : �α����� �� ���̵� ������ ����� �̸���
			//mno : �α����� �� ��й�ȣ ������ ����� �ѹ�
			String memail = request.getParameter("memail");
			String mno = request.getParameter("mno");
			
			//select�� ����ؼ� ��ġ�ϴ� ������ �����ϴ��� Ȯ���غ���
			String sql = "SELECT * FROM MemberInfo WHERE MEmail = ? AND Mno = ?";
			PreparedStatement preparedstatement = connection.prepareStatement(sql);
			preparedstatement.setString(1, memail);
			preparedstatement.setString(2, mno);
			
			ResultSet resultSet = preparedstatement.executeQuery();
			
			//�α����� �ϴ� ������ �Ѹ��̱� ������ if���� Ȱ���Ͽ� �ϳ��� ������ ��ȸ�Ѵ�.
			if(resultSet.next()) {
				//��ġ�ϴ� ���� �����ϸ� �α��� ������ ���� ������ ������ �´�.
				//HttpSession : ������ Ŭ���̾�Ʈ�� ���� ���� ������ �����ϰ� �����ϴµ� ���
				//���� http��û�� ���� ������ ������ �´�.
				//ó�� http�� session ��û�� ó�� ���� ó�� �� �� ������ ������ ���ο� ������ �����ϰ� �̹� ������ �����ϸ� �ش� ������ ������ �´�.(�̹� ����Ǿ� ������ ������ �´�)
				HttpSession session = request.getSession(); //�ӽ������� �����س��ڴ�
				session.setAttribute("mno", resultSet.getInt("MNO")); //db���� ������ mno������ �����ϰڴ�
				session.setAttribute("mname", resultSet.getString("MName"));
				session.setAttribute("memail", memail);
				session.setAttribute("mbirth", resultSet.getDate("Mbirth"));
				
				//�α��� �ð��� 30������ ����, 30�� �� �ڵ� �α׾ƿ�
				//Inactive : ��Ȱ��ȭ, Ȱ������ �ʴ� ����
				//Interval : ����
				session.setMaxInactiveInterval(1800); // 30�� = 1800��
				
				//�α��ο� �����ϸ� ������ ���� �����͸� login_success�� �������ش�
				response.sendRedirect("login_success.jsp");
				
			} else {
				//���� �������� ������ �α��� ����
				request.setAttribute("loginError", "true"); //���࿡ �α����� �ȵȴٸ� �Ӽ��� loginError��� �̸����� �Ӽ��� �����ϰ�, loginError��� �̸��� ���ϰ� loginError�� true�� ������ �߱� ������ �α��� ������ �߻������� ��Ÿ���� �Ӽ��̸��� �Ӽ����� �߰�(����)���ִ� �� 
				
				//getRequestDispatcher(���) : �츮�� ������ ��η� �̵��ϱ� ���� ��ü ��ȯ�̴�.
				//forward(request, response) : ���� �������� ������ �ߴܵȴ�.
				//���ݱ��� ������ �ִ� �����͸� Ŭ���̾�Ʈ���� �������� ������ ����� ǥ���Ѵ�.
				request.getRequestDispatcher("home.jsp").forward(request, response); //������ �� �������� ���ư�����
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
