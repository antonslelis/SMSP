<%@ page import="org.solent.group.project.model.Board" %>
<%@ page import="org.solent.group.project.model.Teacher" %>
<%@ page import="java.util.List" %>
<%!
	private List<Teacher> teacherList;
%>
<%

	String account = (String) session.getAttribute("acct_type");


	Board board_acc = (Board) session.getAttribute("board_acc");
	if (board_acc != null) {
		teacherList = board_acc.getTeacherList().getTeacherList();
	}

	
%>

<html>
<head>
  <link rel="stylesheet" type="text/css" href="./css/main.css">
  <script src="./scripts/main.js"></script>
  <title>Teachers</title>
</head>
<body onLoad="main()">
	<div id="wrapper">
		<div id="header">
			<h1>Teachers page</h1>
			<p id="date"></p>
			<p id="time"></p>
		</div> <!-- /header -->
		
		<div id="content_members">
			<div id="nav_bar">
				<!-- create list for nav bar -->
				<ul>
					<li><a href="./home.jsp">Home</a></li>
					<% if(account.equals("BOARD")) {
					%>
					<li><a href="./teachers.jsp">Teachers</a></li>
					<li><a href="./createUser.jsp">Create User</a></li>
					<%
					}
					%>
					<li id="acct_type"> <%= session.getAttribute("acct_type") %> </li>
					<li><a href="./login.jsp?action=logout">Logout</a></li>
				</ul>
				
			</div> <!-- /nav_bar -->

			<%
			if (account.equals("BOARD")) {

				for (Teacher teacher : teacherList) {
					session.setAttribute("teacher_acc", teacher);
			%>

				<h3 id="<%=teacher.getUsername()%>"><%=teacher.getFirst_name() + " " + teacher.getLast_name()%></h3>
				<p><a href="pupils.jsp?id=<%= teacher.getUsername()%>">Pupil list</a>
			<%
				}
			}
			%>

		</div> <!-- /content_activities -->
	</div> <!-- /wrapper -->
	
</body>
</html>