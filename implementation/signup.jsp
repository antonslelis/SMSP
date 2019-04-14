<%
	
%>

<html>
<head>
  <link rel="stylesheet" type="text/css" href="./css/login.css">
  <title>Signup</title>
</head>
<body>
	<h1>Please enter your login details.</h1>

	<form id="signup_form" action="./login.jsp" method="post">
		<fieldset id="signup">
			<label id="school_label">School Name:</label>
			<input type="text" name="school_name"><br/>
		
			<label id="username_label">Username:</label>
			<input type="text" name="username"><br/>

			<label id="password_label">Password:</label>
			<input type="password" name="password"></input><br/>
			
			<p><input type="submit" id="signup_button" value="Sign up!"></p>
		</fieldset>
		<a href="./login.jsp">Back to Login</a>
	</form>
	
</body>
</html>