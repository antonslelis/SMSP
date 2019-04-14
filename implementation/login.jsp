<%
	/*
	ManageSystemWS webserver = new ManageSystemWS();
	User userCheck = new User();

	String action = (String) request.getParameter("action");
	String username = (String) request.getParameter("username");
	String password = (String) request.getParameter("password");


	userCheck.setUsername(username);
	userCheck.setPassword(password);

	String errorMessage = "";

	if ("validateLogin".equals(action))
	{
		try {
			webserver.logIn(userCheck);
		}
		catch (Exception e){
			errorMessage = "failed to validate " + e.getMessage();
		}
	}
	*/

%>

<html>
<head>
  <link rel="stylesheet" type="text/css" href="./css/login.css">
  <title>Login</title>
</head>
<body>
	<h1>Please enter your login details.</h1>

	<form id="login_form" action="./home.jsp" method="post">
		<fieldset id="login">
			<label id="school_label">School Name:</label>
			<input type="text" name="school_name"><br/>
		
			<label id="username_label">Username:</label>
			<input type="text" name="username"><br/>

			<label id="password_label">Password:</label>
			<input type="password" name="password"></input><br/>
			
			<p><input type="submit" id="login_button" value="Login"></p>
		</fieldset>
	</form>
	
	<form id="signup_form" action="./signup.jsp">
		<fieldset id="signup">
			<p>New to SMS?</p>
			<p><input type="submit" id="signup_button" value="Sign up Now!"></p>
		</fieldset>
	</form>
</body>
</html>