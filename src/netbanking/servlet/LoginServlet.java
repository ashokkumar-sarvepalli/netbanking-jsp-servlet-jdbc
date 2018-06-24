package netbanking.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.security.auth.login.AccountNotFoundException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import netbanking.dao.BalanceDAO;
import netbanking.dao.CustomerDAO;
import netbanking.dao.LoginDAO;
import netbanking.dao.SecurityDAO;
import netbanking.pojo.dao.Balance;
import netbanking.pojo.dao.Login;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public LoginServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		
		
		
		if (request.getQueryString()!=null && request.getQueryString().contains("secretquestionfirsttime")
				&& request.getSession().getAttribute("login") != null) {
			Login login = (Login) request.getSession().getAttribute("login");

			String secretQuestion = request.getParameter("secretquestion");
			String secretAnswerNew = request.getParameter("secretanswer");
			String passw = request.getParameter("password");
			

			login.setSecretQuestion(secretQuestion);
			login.setSecretAnswer(secretAnswerNew);
			login.setPassword(passw);

			LoginDAO loginDAO = new LoginDAO();
			loginDAO.updateLogin(login);
			
			if(request.getParameter("txnpassword")!=null){
				Long txnw = Long.parseLong(request.getParameter("txnpassword"));
				SecurityDAO sd = new SecurityDAO();
				CustomerDAO customer = new CustomerDAO();
				long accountNbr = customer.getAccountNbrByCustomerId(login.getCustomerId());
				sd.insertSecurityNumber(accountNbr, txnw);
			}
			
			PrintWriter out = response.getWriter();
			out.println("<script type=\"text/javascript\">");
			out.println("alert('Details updated please login again with your new credentials');");
			out.println("</script>");
			// return to success page

			RequestDispatcher dis1 = request.getRequestDispatcher("/customer.jsp");
			dis1.forward(request, response);
		}

		else if (request.getQueryString()!=null && request.getQueryString().contains("secretquestion")
				&& request.getSession().getAttribute("login") != null) {
			Login login = (Login) request.getSession().getAttribute("login");

			String secretQuestion = request.getParameter("secretquestion");
			login.setSecretQuestion(secretQuestion);
			// create secret answer

			String secretAnswer = request.getParameter("secretanswer");
			login.setSecretAnswer(secretAnswer);
			LoginDAO loginDAO = new LoginDAO();
			boolean isSuccess = loginDAO.isLoginSuccess(login);
			if (isSuccess) {
				request.getSession().setAttribute("login", login);
				CustomerDAO customer = new CustomerDAO();
				long accountNbr = customer.getAccountNbrByCustomerId(login.getCustomerId());
				BalanceDAO balanceDAO = new BalanceDAO();
				double bal=0;
				try {
					bal = balanceDAO.getBalance(accountNbr);
				} catch (AccountNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				Balance balance = new Balance();
				balance.setAccountNumber(accountNbr);
				balance.setBalance(bal);

				request.setAttribute("balance", balance);
				request.getSession().setMaxInactiveInterval(5 * 60);
				request.getSession().setAttribute("accountnumber", String.valueOf(accountNbr));
				
				RequestDispatcher dis2 = request.getRequestDispatcher("/WEB-INF/homescreen.jsp");
				dis2.forward(request, response);

			} else {
				RequestDispatcher dis2 = request.getRequestDispatcher("/WEB-INF/invalidlogin.jsp");
				dis2.forward(request, response);

			}
		}

		else {

			long customerId = Long.parseLong(request.getParameter("customerid"));
			String password = request.getParameter("password");

			Login login = new Login();
			login.setCustomerId(customerId);
			login.setPassword(password);
			login.setRole("Customer");

			LoginDAO loginDAO = new LoginDAO();
			String secretQuestion = loginDAO.getSecretQuestion(login);
			if (secretQuestion.equalsIgnoreCase("InvalidLogin")) {
				RequestDispatcher dis = request.getRequestDispatcher("/WEB-INF/invalidlogin.jsp");
				dis.forward(request, response);
			} else if (secretQuestion.equalsIgnoreCase("No")) {
				// first time login
				CustomerDAO customerDAO = new CustomerDAO();
				String name = customerDAO.getAccountNameByCustomerId(customerId);
				request.getSession().setAttribute("login", login);
				request.getSession().setAttribute("name", name);
				RequestDispatcher dis = request.getRequestDispatcher("/WEB-INF/secretquestionfirsttime.jsp");
				dis.forward(request, response);

			} else {
				CustomerDAO customerDAO = new CustomerDAO();
				String name = customerDAO.getAccountNameByCustomerId(customerId);
				request.getSession().setAttribute("login", login);
				request.getSession().setAttribute("name", name);
				RequestDispatcher dis = request.getRequestDispatcher("/WEB-INF/secretquestion.jsp");
				dis.forward(request, response);

			}

		}

	}

}
