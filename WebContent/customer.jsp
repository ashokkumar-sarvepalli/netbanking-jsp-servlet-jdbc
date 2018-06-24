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
<p><font color="blue" size= "20"> <center> Customer Login </center> </font></p>
	<div style="width: 400px; margin: 200px auto 0 auto;">
		<form action="LoginServlet" method="post">

			<P>
				<center> <label><b>Username</b></label> <input type="text"
					placeholder="Enter Username" name="customerid" required>
					
				</center>
			</P>

			<P>
			   <center>
				<label><b>Password</b></label> <input type="password"
					placeholder="Enter Password" name="password" required>
					
				</center>
			</P>

			<P>
			
			 	<center>
				<button type="submit">Login</button>
				
				</center>
			</P>


		</form>
	</div>
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