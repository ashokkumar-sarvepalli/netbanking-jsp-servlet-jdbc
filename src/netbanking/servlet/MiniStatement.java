package netbanking.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import netbanking.dao.StatementDAO;
import netbanking.pojo.dao.StatementVO;

/**
 * Servlet implementation class MiniStatement
 */
@WebServlet("/MiniStatement")
public class MiniStatement extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MiniStatement() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String accountNumber = (String)request.getSession().getAttribute("accountnumber");
		StatementDAO sd = new StatementDAO();
		List<StatementVO> statementVOList = sd.getMiniStatement(Long.parseLong(accountNumber));
		request.setAttribute("statementList", statementVOList);
		RequestDispatcher dis = request.getRequestDispatcher("/WEB-INF/ministatement.jsp");
		dis.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
