package netbanking.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import netbanking.dao.CustomerDAO;
import netbanking.pojo.dao.Customer;
import netbanking.pojo.ui.AccountOpeningVO;

/**
 * Servlet implementation class CustomerServlet
 */
@WebServlet("/CustomerServlet")
public class CustomerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public CustomerServlet() {
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
		String name = request.getParameter("name");
		String ssn = request.getParameter("ssn");
		String email = request.getParameter("email");
		String phone = request.getParameter("phone");
		double openingBalance = Double.parseDouble(request.getParameter("openingbalance"));

		Customer customer = new Customer();
		customer.setName(name);
		customer.setSsn(ssn);
		customer.setEmail(email);
		customer.setPhone(phone);
		customer.setOpeningBalance(openingBalance);

		CustomerDAO customerDAO = new CustomerDAO();
		AccountOpeningVO accountOpeningVO = customerDAO.insertCustomer(customer);

		if (accountOpeningVO == null) {
			RequestDispatcher dis = request.getRequestDispatcher("/WEB-INF/errorpage.jsp");
			dis.forward(request, response);
		} else {
			request.setAttribute("accountopening", accountOpeningVO);
			RequestDispatcher dis = request.getRequestDispatcher("/WEB-INF/customercreatedetails.jsp");
			dis.forward(request, response);
		}
	}

}
