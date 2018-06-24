package netbanking.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import netbanking.db.connection.DBConnection;
import netbanking.pojo.dao.StatementVO;
import netbanking.queries.SelectQueries;
import netbanking.sort.StatementSorterByDate;

public class StatementDAO {
	
	public List<StatementVO> getMiniStatement(long accountNumber) {
		List<StatementVO>  statementList = getfullStatements(accountNumber);
		
		if(statementList.size()>13)
		return statementList.subList(0, 12);
		
		return statementList;

	}
	
	public List<StatementVO> getHistoricalStatement(long accountNumber,String fromDate, String toDate) {
		List<StatementVO>  statementList = getfullStatements(accountNumber);
		Date srcDate = convertStringToDate(fromDate);
		Date destDate = convertStringToDate(toDate);
		List<StatementVO> statementListRange = new ArrayList<StatementVO>();
		
		for (StatementVO statement : statementList) {

			if (statement.getDateInDateFormat().compareTo(srcDate) == 0) {
				statementListRange.add(statement);
			}

			else if (statement.getDateInDateFormat().compareTo(destDate) == 0) {
				statementListRange.add(statement);
			}

			else if (statement.getDateInDateFormat().compareTo(srcDate) > 0
					&& statement.getDateInDateFormat().compareTo(destDate) < 0) {
				statementListRange.add(statement);
			}
		}
		
		return statementListRange;
		
	}
	
	private Date convertStringToDate(String fromDate) {
		DateFormat df = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss"); 
		Date date;
		try {
		    date = df.parse(fromDate);
		    return date;
		    
		} catch (ParseException e) {
		   return null;
		}		
	}

	public List<StatementVO> getfullStatements(long accountNumber){
		try {
			List<StatementVO>  statementList = new ArrayList<StatementVO>();
			Connection con = DBConnection.getConnection();
			Statement st = con.createStatement();

			String query1 = SelectQueries.SELECT_CUSTOMER_BY_ACCOUNTNUMBER_WITHDRAWAL;
			query1 = query1.replace(":accountnumber", String.valueOf(accountNumber));
			ResultSet rs1 = st.executeQuery(query1);
			while (rs1.next()) {
				StatementVO s = new StatementVO();
				s.setDate(rs1.getString("date"));
				s.setDescription(rs1.getString("description"));
				s.setDebitAmount(String.valueOf(rs1.getDouble("amount")));
				s.setCreditAmount("--");
				s.setAccountName(rs1.getString("destaccountname"));
				s.setAccountNumber(rs1.getString("destaccountnumber"));
				statementList.add(s);
			}
			
			String query2 = SelectQueries.SELECT_CUSTOMER_BY_ACCOUNTNUMBER_DEPOSITS;
			query2 = query2.replace(":accountnumber", String.valueOf(accountNumber));
			ResultSet rs2 = st.executeQuery(query2);
			while (rs2.next()) {
				StatementVO s = new StatementVO();
				s.setDate(rs2.getString("date"));
				s.setDescription(rs2.getString("description"));
				s.setCreditAmount(String.valueOf(rs2.getDouble("amount")));
				s.setDebitAmount("--");
				s.setAccountName(rs2.getString("srcaccountname"));
				s.setAccountNumber(rs2.getString("srcaccountnumber"));
				statementList.add(s);
			}
			
			Collections.sort(statementList, new StatementSorterByDate());
			return statementList;

		} catch (Exception ex) {
			return null;
		}
	}

}
