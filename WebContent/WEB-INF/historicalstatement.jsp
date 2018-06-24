<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
      <%@ page import="netbanking.pojo.dao.StatementVO"%>
      <%@ page import="java.util.List"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<style>
table {
    font-family: arial, sans-serif;
    border-collapse: collapse;
    width: 100%;
}

td, th {
    border: 1px solid #dddddd;
    text-align: left;
    padding: 8px;
}

tr:nth-child(even) {
    background-color: #dddddd;
}
</style>
</head>
<body bgcolor = "yellow">
<p><font color="blue" size= "20"> <center> Welcome To ABCInternetBanking </center> </font></p>
<p><font color="blue" size= "20"> <center> HISTORICAL STATEMENT </center> </font></p>

	<div style="width: 400px; margin: 200px auto 0 auto;">
		<form action="HistoricalStatement" method="post">

		<table>
		
		<tr>
		<td><label><b>From Date :</b></label> </td>
		<td><input type="text"
					placeholder="MM/dd/yyyy" name="fromdate" required>
		</td>
		</tr>
			
		<tr>
		<td><label><b>To Date :</b></label> </td>
		<td><input type="text"
					placeholder="MM/dd/yyyy" name="todate" required>
		</td>
		</tr>	 
		</table>
	
		<p>
		<center>
	    <button type="submit">Search</button>
		</center>
		</p>
		</form>
	</div>



<center>
<P>
<form action=BalanceServlet method="get">
		  <button type="submit">AccountHomeScreen</button>
		</form>
</P>
</center>
</body>
</html>