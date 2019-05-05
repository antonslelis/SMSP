<%
	//temp school list for tests
	String[] school_list = {"school 1", "school 2", "school 3"};
%>

<html>
<head>
  <link rel="stylesheet" type="text/css" href="./css/login.css">
  <title>Signup</title>
</head>
<body>
	<h1>Please enter your sign up details.</h1>

	<form id="signup_form" action="./login.jsp" method="post">
		<fieldset id="signup">
			<label id="school_label">School Name:</label>
			<select name="school_name">
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
			
			<label id="access_label">Access Level:</label>
			<select name="access_level">
				<option value="Admin">Admin</option>
				<option value="Board">Board</option>
				<option value="Teacher">Teacher</option>
				<option value="Parent">Parent</option>
			</select>
			
			<input type="hidden" name="action" value="signUpUser"></input>
			<p><input type="submit" id="signup_button" value="Sign up!"></p>
		</fieldset>
		
	</form>
	<form id="return_form" action="./login.jsp">
		<fieldset id="return">
			<p><input type="submit" id="return_button" value="Back to Login"></p>
		</fieldset>
	</form>
</body>
</html>