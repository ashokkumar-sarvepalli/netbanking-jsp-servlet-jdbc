package netbanking.servlet;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
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
 * Servlet implementation class HistoricalStatement
 */
@WebServlet("/HistoricalStatement")
public class HistoricalStatement extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public HistoricalStatement() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher dis2 = request.getRequestDispatcher("/WEB-INF/historicalstatement.jsp");
		dis2.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String fromDate = (String)request.getParameter("fromdate");
		String toDate = (String)request.getParameter("todate");
		Long accountNumber = Long.parseLong((String)request.getSession().getAttribute("accountnumber"));
		fromDate = fromDate + " 00:00:01";
		toDate = toDate + " 23:59:59";
		String filename = "statement"+"_accountnumber_"+accountNumber+".csv";
		StatementDAO sd = new StatementDAO();
		List<StatementVO> statementVOList = sd.getHistoricalStatement(accountNumber, fromDate, toDate);
		
		String header = "DATE,ACCOUNT NUMBER,ACCOUNT NAME,DESCRIPTION,DEBIT AMOUNT,CREDIT AMOUNT";
		
		
		String endl = System.getProperty("line.separator");
		
		FileOutputStream fos = new FileOutputStream(new File(filename));
		fos.write(header.getBytes());
		fos.write(endl.getBytes());
		
		for(StatementVO statement : statementVOList){
			StringBuilder sb = new StringBuilder();
			sb.append(statement.getDate());
			sb.append(",");
			sb.append(statement.getAccountNumber());
			sb.append(",");
			sb.append(statement.getAccountName());
			sb.append(",");
			sb.append(statement.getDescription());
			sb.append(",");
			sb.append(statement.getDebitAmount());
			sb.append(",");
			sb.append(statement.getCreditAmount());
			fos.write(sb.toString().getBytes());
			fos.write(endl.getBytes());
		}
		
		
		fos.close();
		
		
		
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		
		response.setContentType("APPLICATION/OCTET-STREAM");
		response.setHeader("Content-Disposition", "attachment; filename=\""
				+ filename + "\"");
 
		// use inline if you want to view the content in browser, helpful for
		// pdf file
		// response.setHeader("Content-Disposition","inline; filename=\"" +
		// filename + "\"");
		FileInputStream fileInputStream = new FileInputStream(filename);
 
		int i;
		while ((i = fileInputStream.read()) != -1) {
			out.write(i);
		}
		fileInputStream.close();
		out.close();
				
				
	}

}
