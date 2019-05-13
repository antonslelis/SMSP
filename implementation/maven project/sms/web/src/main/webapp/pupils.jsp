<%@ page import="org.solent.group.project.model.Board" %>
<%@ page import="org.solent.group.project.model.Teacher" %>
<%@ page import="org.solent.group.project.model.Pupil" %>
<%@ page import="java.util.List" %>
<%!
	private Teacher teacher_acc;
	private List<Teacher> teacherList; %>
<%

	String account = (String) session.getAttribute("acct_type");
	String id = request.getParameter("id");

	if (account.equals("BOARD"))
	{
		Board board_acc = (Board) session.getAttribute("board_acc");
		teacherList = board_acc.getTeacherList().getTeacherList();

		for (Teacher teacher : teacherList) {
			if (teacher.getUsername().equals(id)) {
				teacher_acc = (Teacher) session.getAttribute("teacher_acc");
			}
		}
	}
	else {
		teacher_acc = (Teacher) session.getAttribute("teacher_acc");
	}
	List<Pupil> pupilList1 = teacher_acc.getPupilList().getPupilList();
	
%>

<html>
<head>
  <link rel="stylesheet" type="text/css" href="./css/main.css">
  <script src="./scripts/main.js"></script>
  <title>Pupils</title>
</head>
<body onLoad="main()">
	<div id="wrapper">
		<div id="header">
			<h1>Pupils page</h1>
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
					<li><a href="./createUser.jsp">Create User</a></li>
					<%
					}
					%>
					<li id="acct_type"> <%= session.getAttribute("acct_type") %> </li>
					<li><a href="./login.jsp?action=logout">Logout</a></li>
				</ul>
				
			</div> <!-- /nav_bar -->

			<%
			if (account.equals("TEACHER") || account.equals("BOARD"))
			{
				for (Pupil pupil : pupilList1){
					session.setAttribute("pupil_acc", pupil);
			%>
			<h3 id="<%=pupil.getUsername()%>"><%=pupil.getFirst_name() + " " + pupil.getLast_name()%></h3>
			<p><a href="activities.jsp">Activity list</a>

			<%
				}
			}
			%>


		</div> <!-- /content_activities -->
	</div> <!-- /wrapper -->
	
</body>
</html>