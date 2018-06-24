<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>

<body bgcolor = "yellow">
<p><font color="blue" size= "20"> <center> Welcome To ABCInternetBanking </center> </font></p>
<p><font color="blue" size= "20"> <center> CREATE CUSTOMER </center> </font></p>
<center>
	<div style="width: 400px; margin: 200px auto 0 auto;">
		<form action="CustomerServlet" method="post">

		<table>
			<P>
				 <label><b>Name</b></label> <input type="text"
					placeholder="Enter Name" name="name" required>
					
				
			</P>

			<P>
				<label><b>SSN</b></label> <input type="text"
					placeholder="Enter SSN" name="ssn" required>
					
			</P>
			
			
			
			<P>
				<label><b>EMAIL</b></label> <input type="email"
					placeholder="Enter EMAIL" name="email" required>
					
			</P>
			
			<P>
				<label><b>Phone</b></label> <input type="text"
					placeholder="Enter Phone" name="phone" required>
					
			</P>
			
			
			<P>
				<label><b>OpeningBalance</b></label> <input type="text"
					placeholder="Enter Opening Balance" name="openingbalance" required>
					
			</P>
			
			

			<P>
			
				<button type="submit">Login</button>
				
			</P>

		
		</form>
	</div>
	
	</center>

	<p>
		<font color="green" size="4">
			<center>To Go to home page press this below</center>
		 <a href=homepage.jsp>
			<center>
				press here
			</center>
		</a>
		</font>
	</p>
</body>
</html>