<%
	/*
	ManageSystemWS webserver = new ManageSystemWS();
	User newUser = new User();

	*/
	
	String action = (String) request.getParameter("action");
	
	String school = request.getParameter("school_name");
	String username = request.getParameter("username");
	String password = request.getParameter("password");
	String account = request.getParameter("access_level");


	session.setAttribute("acct_type", account);
	session.setAttribute("school_name", school);
	/*
	newUser.setUsername(username);
	newUser.setPassword(password);

	String errorMessage = "";

	if ("signUpUser".equals(action))
	{
		try {
			webserver.createUser(newUser);
		}
		catch (Exception e){
			errorMessage = "failed to create " + e.getMessage();
		}
	}
	*/
	
	//temp school list for tests
	String[] school_list = {"school 1", "school 2", "school 3"};

	

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
			<select name="school_name">
				<option value="test1">TEST 1</option>
				<%
					for (int i=0; i<school_list.length; i++)
					{
						
				%>
						<option value="<%= school_list[i] %>"><%= school_list[i] %></option>
				<%
					}
				%>
			</select>
		
			<label id="username_label">Username:</label>
			<input type="text" name="username"><br/>

			<label id="password_label">Password:</label>
			<input type="password" name="password"></input><br/>
			
			<input type="hidden" name="action" value="validateLogin"></input>
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