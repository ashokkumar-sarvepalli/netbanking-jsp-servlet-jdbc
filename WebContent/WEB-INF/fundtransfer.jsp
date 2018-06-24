<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script type="text/css">
table {
    border-collapse: collapse;
}

td {
    padding-top: .25em;
    padding-bottom: .25em;
}
</script>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body bgcolor = "yellow">
<p><font color="blue" size= "20"> <center> Welcome To ABCInternetBanking </center> </font></p>
<p><font color="blue" size= "20"> <center> INTRA BANK FUND TRANSFER </center> </font></p>
<center>
	<div style="width: 400px; margin: 200px auto 0 auto;">
		<form action="FundTransfer" method="post">

	<P>
	<form action="FundTransfer" method="post">
	<table>
		
		<tr>
		<td> <label><b>Source-Account-Number</b></label> </td>
		<td> <input type="text"
					name="srcaccountnumber" value=<%=session.getAttribute("accountnumber") %> readonly> </td>
		</tr>
		
		<tr>
		<td><label><b>Destination-Account-Number</b></label> </td>
		<td><input type="text"
					placeholder="Enter Destination account number" name="destaccountnumber" required> </td>
		</tr>	
			
				 
		<tr>
		<td><label><b>Destination-Account-Name</b></label></td>
		<td> <input type="text"
					placeholder="Enter Destination account Name" name="destaccountname" required></td>
		</tr>
					
				
		<tr>
		<td> <label><b>Description</b></label> </td>
		<td> <input type="text"
					placeholder="Enter Description" name="description" required> </td>
		</tr>		
				
		<tr>	
		<td><label><b>Amount</b></label> </td>
		<td><input type="number" min="1"
					placeholder="Enter the ammount" name="amount" required> </td>
		</tr>		
		
		<tr>	
		<td><label><b><%=request.getAttribute("question")%></b></label> </td>
		<td><input type="password" min="1"
					placeholder="Enter the security password" name="securityno" required> </td>
		</tr>		
		</table>
		
		
		
		
			<center>
			<P>
				<button type="submit">Transfer</button>
		    </P>
			</center>
	
			<% if(request.getAttribute("isError")!=null) { %>
			<center>
			<P>
			<%Boolean value = (Boolean) request.getAttribute("isError"); %>
			<% if(value==Boolean.TRUE) { %>
			    <font color = "red">
				<%=request.getAttribute("tmessage") %>
				</font>
			   <%} else { %>
				<font color = "green">
				<%=request.getAttribute("tmessage") %>
				</font>
			  <%} %>
		    </P>
		    </center>
		
		   <%} %>
		</form>
		
		
		<form action=BalanceServlet method="get">
		  <button type="submit">AccountHomeScreen</button>
		</form>
		</P>
		
	</div>
	
	</center>

	
</body>
</html>