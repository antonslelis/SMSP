<%@ page import="org.solent.group.project.model.Board" %>
<%@ page import="org.solent.group.project.model.Parent" %>
<%@ page import="java.util.List" %><%
	String account = (String) session.getAttribute("acct_type");
	Board teacher_acc = (Board) session.getAttribute("board_acc");

	List<Parent> parentList =
%>

<html>
<head>
  <link rel="stylesheet" type="text/css" href="./css/main.css">
  <title>Create user</title>
</head>
<body>
	<form id="createUser_form" action="./home.jsp" method="post">
		<label id="fname_label">First Name:</label>
		<input type="text" name="first_name"><br/>

		<label id="lname_label">Last Name:</label>
		<input type="text" name="last_name" value=""><br/>

		<label id="user_label">Username:</label>
		<input type="text" name="username_c"><br/>

		<label id="pass_label">Password:</label>
		<input type="password" name="password_c" value=""><br/>

		<select name="creation_level">
		<% if (account.equals("ADMIN")) {
		%>
			<option name="BOARD">BOARD</option>

		<%  }
			else if (account.equals("BOARD")) {
		%>
			<option name="TEACHER">TEACHER</option>
			<option name="PARENT">PARENT</option>
			<option name="PUPIL">PUPIL</option>
		<%
			}
		%>
		</select>

		<select>
			<option></option>

		</select>

		<input type="hidden" name="action" value="createUser">
		<p><input type="submit" id="createUser_btn" value="Create user"></p>
		
	</form>
</body>
</html>