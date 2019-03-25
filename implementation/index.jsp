<%
	ManageSystemWS webserver = new ManageSystemWS();
	User userCheck = null;
	
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
	
	
%>

<!DOCTYPE html>
<html>
<head>
  <link rel="stylesheet" type="text/css" href="./css/main.css">
  <title>Login</title>
</head>
<body>
	<h1>Please enter your login details.</h1>
	
	<form id="login_form" action="./home.jsp" method="post">
		<fieldset id="login">
			<label id="username_label">Username:</label>
			<input type="text" id="username"></input><br/>
			
			<label id="password_label">Password:</label>
			<input type="password" id="password"></input><br/>
			
			<input type="hidden" name="action" value="validateLogin">
			<p><input type="submit" id="login_button" value="Login"></input></p>
		</fieldset>
	</form>
</body>
</html>