<%@ page import="org.solent.group.project.model.Board" %>
<%@ page import="org.solent.group.project.model.Parent" %>
<%@ page import="java.util.List" %>
<%@ page import="org.solent.group.project.model.Teacher" %>
<%! private List<Parent> parentList;
	private List<Teacher> teacherList; %>
<%
	String account = (String) session.getAttribute("acct_type");

	if (!account.equals("ADMIN")) {
		Board board_acc = (Board) session.getAttribute("board_acc");
		//get both lists of school members
		parentList = board_acc.getParentList().getParentList();
		teacherList = board_acc.getTeacherList().getTeacherList();
	}
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

		<label id="role_label">Role:</label>
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

		<br/>
		<br/>
		<h3>Only change if PUPIL is selected above*</h3>
		<label id="parent_link_label">*Parent for pupil:</label>
		<% if (account.equals("BOARD")){
		%>
		<select name="parent_link">
			<%
			for (Parent parent : parentList){
			%>
				<option name="<%= parent.getUsername()%>">
					<%= parent.getUsername()%></option>
			<%
				}
			%>
		</select>
		<%
			}
		%>

		<label id="teacher_link_label">*Teacher for pupil:</label>
		<% if (account.equals("BOARD")){
		%>
		<select name="teacher_link">
			<%
				for (Teacher teacher : teacherList){
			%>
			<option name="<%= teacher.getUsername()%>">
				<%= teacher.getUsername()%></option>
			<%
				}
			%>
		</select>
		<%
			}
		%>
		<input type="hidden" name="action" value="createUser">
		<p><input type="submit" id="createUser_btn" value="Create user"></p>
		
	</form>
</body>
</html>