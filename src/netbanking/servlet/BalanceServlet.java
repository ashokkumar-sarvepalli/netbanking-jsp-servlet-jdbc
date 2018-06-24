package netbanking.servlet;

import java.io.IOException;

import javax.security.auth.login.AccountNotFoundException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import netbanking.dao.BalanceDAO;
import netbanking.pojo.dao.Balance;

/**
 * Servlet implementation class BalanceServlet
 */
@WebServlet("/BalanceServlet")
public class BalanceServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BalanceServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try {
			String accountNumber = (String) request.getSession().getAttribute("accountnumber");
			BalanceDAO bl = new BalanceDAO();
			double balance = bl.getBalance(Long.parseLong(accountNumber));
			Balance bal = new Balance();
			bal.setAccountNumber(Long.parseLong(accountNumber));
			bal.setBalance(balance);
			
			request.setAttribute("balance", bal);
			
			RequestDispatcher dis2 = request.getRequestDispatcher("/WEB-INF/homescreen.jsp");
			dis2.forward(request, response);
		} catch (AccountNotFoundException | NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
