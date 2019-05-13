<%! private String type;
%>
<%@ page import="org.solent.group.project.model.*" %><%

	//new webserver
	ManageSystemWS webserver = new ManageSystemWS();
	User userCheck = new User();

	//get all login info from post request from login page
	String action = (String) request.getParameter("action");
	String username = request.getParameter("username");
	String password = request.getParameter("password");
	//assign the value of the session username
	if (session.getAttribute("username") == null) {
		session.setAttribute("username", username);
	}
	//assign values to login test user if the details haven't been entered before

	userCheck.setUsername(username);
	userCheck.setPassword(password);

	String errorMessage = "";

	if (session.getAttribute("allowed") == null) {
		session.setAttribute("allowed", false);
	}


	if (session.getAttribute("allowed").equals(false)) {
		try {
			//try logging in test user
			User logCheck = webserver.logIn(userCheck);
			if (logCheck != null) {
				//log user in based on account type
				type = logCheck.getType();
				if (type.equals("ADMIN")) {
					session.setAttribute("admin_acc", webserver.logAdmin(logCheck));
				} else if (type.equals("BOARD")) {
					session.setAttribute("board_acc", webserver.logBoard(logCheck));
				} else if (type.equals("TEACHER")) {
					session.setAttribute("teacher_acc", webserver.logTeacher(logCheck));
				} else if (type.equals("PARENT")) {
					session.setAttribute("parent_acc", webserver.logParent(logCheck));
				} else if (type.equals("PUPIL")) {
					session.setAttribute("pupil_acc", webserver.logPupil(logCheck));
				}
				session.setAttribute("acct_type", type);
				session.setAttribute("allowed", true);
			} else {
				//redirect to login
				response.sendRedirect("./accessError.html");
			}
		} catch (Exception e) {
			errorMessage = "failed to validate " + e.getMessage();
		}
	}

	if ("createUser".equals(action))
	{
		type = (String) session.getAttribute("acct_type");

		//take in all create account parameters
		String create_username = request.getParameter("username_c");
		String create_password = request.getParameter("password_c");
		String create_type = request.getParameter("creation_level");
		String fname = request.getParameter("first_name");
		String lname = request.getParameter("last_name");

		if (type.equals("ADMIN")) {
			Admin admin_acc = (Admin) session.getAttribute("admin_acc");
			Board created_user = new Board();
			created_user.setUsername(create_username);
			created_user.setFirst_name(fname);
			created_user.setLast_name(lname);
			created_user.setPassword(create_password);
			created_user.setType(create_type);

			webserver.createBoard(created_user, admin_acc);
		} else if (type.equals("BOARD")) {
			Board board_acc = (Board) session.getAttribute("board_acc");
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
			Teacher teacher_acc = (Teacher) session.getAttribute("teacher_acc");
			Parent parent_acc = (Parent) session.getAttribute("parent_acc");

			Pupil created_user = new Pupil();
			created_user.setUsername(create_username);
			created_user.setFirst_name(fname);
			created_user.setLast_name(lname);
			created_user.setPassword(create_password);
			created_user.setType(create_type);
			webserver.createPupil(created_user, teacher_acc, parent_acc);
		}

	}

	session.setAttribute("acct_type", type);

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
					<%  if (type != null){
							if(type.equals("ADMIN") || type.equals("BOARD") || type.equals("TEACHER")) {
					%>
								<li><a href="./createUser.jsp">Create User</a></li>
					<%
							}
						}
					%>
					<li id="acct_type"> <%= session.getAttribute("acct_type") %> </li>
					<li><a href="./login.jsp?action=logout">Logout</a></li>
				</ul>
				
			</div> <!-- /nav_bar -->	
			
			<p> Welcome back <%= session.getAttribute("username") %></p>
			<% Board newboard = (Board) session.getAttribute("board_acc"); %>
			<%= newboard.getTeacherList().getListSize()%>
		</div> <!-- /content -->
	</div> <!-- /wrapper -->
	
</body>
</html>