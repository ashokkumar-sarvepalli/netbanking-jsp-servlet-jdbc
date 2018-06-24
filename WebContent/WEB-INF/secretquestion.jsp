<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body bgcolor="yellow">
	<p>
		<font color="blue" size="20">
			<center>Welcome To ABCInternetBanking</center>
		</font>
	</p>
	<p>
		<font color="blue" size="20">
			<center>Welcome <%=request.getSession().getAttribute("name") %>. Choose your secret question and answer to proceed</center>
		</font>
	</p>

	<center>
		<div style="width: 400px; margin: 200px auto 0 auto;">
		<form action="LoginServlet?secretquestions=true" method="post">
		<font color="brown" size="8">
			<p>
				 <label><b>Secret Question</b></label>
					

					<select name="secretquestion" placeholder="What is your secret question" >
						<option value="Who is your childhood hero?">Who is your childhood hero?</option>
						<option value="What is your mother maiden name?">What is your mother maiden name?</option>
						<option value="Your best childhood friend">Your best childhood friend</option>
						<option value="Your Lover name">Your Lover name</option>
					</select> 
				
			</P>

			<P>
			<label><b>Secret Answer</b></label> <input type="text"
					placeholder="Enter your Answer" name="secretanswer" required>
					
			</P>
			
			<P>
			
				<button type="submit">Login</button>
				
			</P>
			

			</font>
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

