package UserInfo;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.spi.CollatorProvider;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import zerozerotwo.dbutil.ConnectionProvider;

@WebServlet("/gogo/login/loginbtn")
public class LoginServlet extends HttpServlet{
 
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	 HttpSession sesstion = req.getSession();
		
		LogInDAOImpl dao = new LogInDAOImpl();
		
		String inputId = req.getParameter("inputId");
		System.out.println("id: " + inputId);
		String inputPw = req.getParameter("inputPw");
		System.out.println("pw: " + inputPw);
		
		int userInput = dao.inputSelect(inputId, inputPw);
		if(userInput == 1  ) {
			
			
			UserInfoService user =new  UserInfoServiceImpl(new UserInfoDAOImpl());
			
			
			
			sesstion.setAttribute("nickName",user.selectNickname( inputId));
			
		}
		
		
		String json = "{\"userInput\":" + userInput + "}";
		System.out.println("응답: " + json);
		PrintWriter pw = resp.getWriter();
		pw.println(json);
		pw.flush();
	
	}
	
	

}