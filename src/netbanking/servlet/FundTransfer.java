package netbanking.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.security.auth.login.AccountNotFoundException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import netbanking.dao.SecurityDAO;
import netbanking.dao.TransferDAO;
import netbanking.exception.InsufficientBalanceException;
import netbanking.exception.SecurityTransactionWrongException;
import netbanking.pojo.dao.Transaction;

/**
 * Servlet implementation class FundTransfer
 */
@WebServlet("/FundTransfer")
public class FundTransfer extends HttpServlet {

	private static List<Integer> myList = new ArrayList<Integer>();
	private static Map<Integer, String> myMap = new HashMap<Integer, String>();

	static {

		myMap.put(1, "First");
		myMap.put(2, "Second");
		myMap.put(3, "Third");
		myMap.put(4, "Fourth");
		myMap.put(5, "Fifth");
		myMap.put(6, "Sixth");
	}

	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public FundTransfer() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String question = generateRandomQuestion();
		request.setAttribute("question", question);
		RequestDispatcher dis = request.getRequestDispatcher("/WEB-INF/fundtransfer.jsp");
		dis.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			String srcAccountNumber = request.getParameter("srcaccountnumber");
			String destAccountNumber = request.getParameter("destaccountnumber");
			String destAccountName = request.getParameter("destaccountname");
			String description = request.getParameter("description");
			double amount = Double.parseDouble(request.getParameter(("amount")));
			String srcAccountName = (String) request.getSession().getAttribute("name");
			// user entered
			String securityNo = (String) request.getParameter("securityno");

			SecurityDAO sd = new SecurityDAO();
			Long securityNum = sd.isSecuritySuccess(Long.parseLong(srcAccountNumber));
			// from db.
			String securityKey = String.valueOf(securityNum);

			int k = 0;
			for (Integer i : myList) {
				if (!(securityNo.charAt(k) == securityKey.charAt(i - 1))) {
					throw new SecurityTransactionWrongException("wrong transaction password");
				}
				k++;
			}

			myList.clear();

			Transaction tr = new Transaction();
			tr.setAccountNumber(Long.parseLong(srcAccountNumber));
			tr.setName(srcAccountName);
			tr.setDestAccountNumber(Long.parseLong(destAccountNumber));
			tr.setDescription(description);
			tr.setDestAccountName(destAccountName);
			tr.setAmmount(amount);

			TransferDAO td = new TransferDAO();

			td.transfer(tr);
			request.setAttribute("isError", false);
			request.setAttribute("tmessage", "Transaction Succesfull");
			String question = generateRandomQuestion();
			request.setAttribute("question", question);
		} catch (InsufficientBalanceException e) {
			request.setAttribute("isError", true);
			request.setAttribute("tmessage", "InsufficientBalance");
			myList.clear();
			String question = generateRandomQuestion();
			request.setAttribute("question", question);
		} catch (AccountNotFoundException e) {
			request.setAttribute("isError", true);
			request.setAttribute("tmessage", "Invalid destination account");
			myList.clear();
			String question = generateRandomQuestion();
			request.setAttribute("question", question);
		} catch (SecurityTransactionWrongException e) {
			request.setAttribute("isError", true);
			request.setAttribute("tmessage", "Invalid Security transaction password");
			myList.clear();
			String question = generateRandomQuestion();
			request.setAttribute("question", question);
		} catch (Exception e) {
			request.setAttribute("isError", true);
			request.setAttribute("tmessage", e.getMessage());
			myList.clear();
			String question = generateRandomQuestion();
			request.setAttribute("question", question);
		}

		RequestDispatcher dis = request.getRequestDispatcher("/WEB-INF/fundtransfer.jsp");
		dis.forward(request, response);

	}

	private String generateRandomQuestion() {
		Random rd = new Random();

		while (myList.size() < 3) {
			int num = rd.nextInt(6) + 1;
			if (!myList.contains(num)) {
				myList.add(num);
			}
		}
		Collections.sort(myList);
		StringBuilder sb = new StringBuilder("Enter the ");
		sb.append(myMap.get(myList.get(0)));
		sb.append(" , ");
		sb.append(myMap.get(myList.get(1)));
		sb.append(" and ");
		sb.append(myMap.get(myList.get(2)));
		sb.append(" digits of your security password");
		return sb.toString();

	}

}
