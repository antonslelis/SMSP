<%! private Admin admin_acc = new Admin();
	private Board board_acc = new Board();
	private Teacher teacher_acc = new Teacher();
	private Parent parent_acc = new Parent();
	private Pupil pupil_acc = new Pupil();
	private boolean allowed = false;
%>
<%@ page import="org.solent.group.project.model.*" %><%

	ManageSystemWS webserver = new ManageSystemWS();
	User userCheck = new User();

	String action = (String) request.getParameter("action");

	String username = request.getParameter("username");
	String password = request.getParameter("password");

	userCheck.setUsername(username);
	userCheck.setPassword(password);

	String errorMessage = "";




	if (!allowed) {
		try {
			User logCheck = webserver.logIn(userCheck);
			if (logCheck != null) {
				String type = logCheck.getType();
				if (type.equals("ADMIN")) {
					admin_acc = webserver.logAdmin(logCheck);
				} else if (type.equals("BOARD")) {
					board_acc = webserver.logBoard(logCheck);
				} else if (type.equals("TEACHER")) {
					teacher_acc = webserver.logTeacher(logCheck);
				} else if (type.equals("PARENT")) {
					parent_acc = webserver.logParent(logCheck);
				} else if (type.equals("PUPIL")) {
					pupil_acc = webserver.logPupil(logCheck);
				}
				session.setAttribute("acct_type", type);
				allowed = true;
			} else {
				response.sendRedirect("./accessError.html");
			}
		} catch (Exception e) {
			errorMessage = "failed to validate " + e.getMessage();
		}
	}

	if ("createUser".equals(action))
	{
		String type = (String) session.getAttribute("acct_type");

		String create_username = request.getParameter("username");
		String create_password = request.getParameter("password");
		String create_type = request.getParameter("creation_level");
		String fname = request.getParameter("first_name");
		String lname = request.getParameter("last_name");

		if (type.equals("ADMIN")) {
			Board created_user = new Board();
			created_user.setUsername(create_username);
			created_user.setFirst_name(fname);
			created_user.setLast_name(lname);
			created_user.setPassword(create_password);
			created_user.setType(create_type);

			webserver.createBoard(created_user, admin_acc);
		} else if (type.equals("BOARD")) {
			if (create_type.equals("TEACHER"))
			{
				Teacher created_user = new Teacher();
				created_user.setUsername(create_username);
				created_user.setFirst_name(fname);
				created_user.setLast_name(lname);
				created_user.setPassword(create_password);
				created_user.setType(create_type);
				webserver.createTeacher(created_user, board_acc);
			}
			else if (create_type.equals("PARENT"))
			{
				Parent created_user = new Parent();
				created_user.setUsername(create_username);
				created_user.setFirst_name(fname);
				created_user.setLast_name(lname);
				created_user.setPassword(create_password);
				created_user.setType(create_type);
				webserver.createParent(created_user, board_acc);
			}
		} else if (type.equals("TEACHER")) {
			Pupil created_user = new Pupil();
			created_user.setUsername(create_username);
			created_user.setFirst_name(fname);
			created_user.setLast_name(lname);
			created_user.setPassword(create_password);
			created_user.setType(create_type);
			webserver.createPupil(created_user, teacher_acc, parent_acc);
		}

	}

	String account = (String) session.getAttribute("acct_type");
	
%>
<html>
<head>
  <link rel="stylesheet" type="text/css" href="./css/main.css">
  <script src="./scripts/main.js"></script>
  <title>Homepage</title>
</head>
<body onLoad="main()">
	<div id="wrapper">
		<div id="header">
			<h1>System Homepage</h1>
			<p id="date"></p>
			<p id="time"></p>
		</div> <!-- /header -->
		
		<div id="content">
			<div id="nav_bar">
				<!-- create list for nav bar -->
				<ul>
					<li><a href="./events.jsp">Events</a></li>
					<li><a href="./report.jsp">Report</a></li>
					<% if(account.equals("ADMIN") || account.equals("BOARD") || account.equals("TEACHER")) {
					%>
					<li><a href="./createUser.jsp">Create User</a></li>
					<%
					}
					%>
					<li id="acct_type"> <%= session.getAttribute("acct_type") %> </li>
					<li><a href="./login.jsp?action=logout">Logout</a></li>
				</ul>
				
			</div> <!-- /nav_bar -->	
			
			<p> Welcome back <%= session.getAttribute("username") %></p>
			
		</div> <!-- /content -->
	</div> <!-- /wrapper -->
	
</body>
</html>