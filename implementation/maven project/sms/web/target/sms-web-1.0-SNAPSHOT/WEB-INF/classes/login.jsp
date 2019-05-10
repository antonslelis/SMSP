<%	
	/*
	ManageSystemWS webserver = new ManageSystemWS();
	User newUser = new User();

	*/
	
	
	
	String school = request.getParameter("school_name");
	String username = request.getParameter("username");
	String password = request.getParameter("password");
	String account = request.getParameter("access_level");


	session.setAttribute("acct_type", account);
	session.setAttribute("school_name", school);
	
	String action = (String) request.getParameter("action");
	
	//clears session when logout is pressed
	if ("logout".equals(action))
	{
		session.invalidate();
	}


	

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
			<label id="username_label">Username:</label>
			<input type="text" name="username"><br/>

			<label id="password_label">Password:</label>
			<input type="password" name="password"></input><br/>
			
			<input type="hidden" name="action" value="validateLogin"></input>
			<p><input type="submit" id="login_button" value="Login"></p>
		</fieldset>
	</form>

</body>
</html>