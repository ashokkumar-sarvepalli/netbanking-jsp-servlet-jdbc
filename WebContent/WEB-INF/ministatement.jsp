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
<p><font color="blue" size= "20"> <center> MINI STATEMENT </center> </font></p>

<% List<StatementVO> statementList = (List<StatementVO>) request.getAttribute("statementList"); %>

<table style="width:100%">
<tr>
<th> DATE </th>
<th> ACCOUNT NUMBER </th>
<th> ACCOUNT NAME </th>
<th> DESCRIPTION </th>
<th> DEBIT AMOUNT </th>
<th> CREDIT AMOUNT </th>
</tr>

<% for(StatementVO statement : statementList) {  %>
<tr>
<td> <%=statement.getDate() %> </td>
<td> <%=statement.getAccountNumber() %> </td>
<td> <%=statement.getAccountName() %> </td>
<td> <%=statement.getDescription() %> </td>
<td> <%=statement.getDebitAmount() %> </td>
<td> <%=statement.getCreditAmount() %>  </td>
</tr>
<%}%>
</table>

<center>
<P>
<form action=BalanceServlet method="get">
		  <button type="submit">AccountHomeScreen</button>
		</form>
</P>
</center>
</body>
</html>