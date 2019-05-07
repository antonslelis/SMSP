<%
	String account = (String) session.getAttribute("acct_type");
	
%>

<html>
<head>
  <link rel="stylesheet" type="text/css" href="./css/main.css">
  <title>Invoice</title>
</head>
<body>
	<form id="createUser_form" action="./home.jsp" method="post">
			<label id="user_label">Username:</label>
			<input type="text" name="name"><br/>

			<label id="pass_label">Password:</label>
			<input type="password" name="password" value=""></input><br/>
			
			<select>
			<% if (account.equals("Admin")) {
			%>
				<option name="Board">Board</option>
			
			<%  }
				else if (account.equals("Board")) {
			%>
				<option name="Teacher">Teacher</option>
				<option name="Parent">Parent</option>
			<%  }
				else if (account.equals("Teacher")) {
			%>
				<option name="Pupil">Pupil</option>
			<%
				}
			%>
			</select>
			
			<input type="hidden" name="action" value="createUser"></input>
			<p><input type="submit" id="createUser_btn" value="Create user"></p>
		
	</form>
</body>
</html>