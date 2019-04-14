<%
	String school = request.getParameter("school_name");
	String username = request.getParameter("username");
	String password = request.getParameter("password");
	//String account = session.getAttribute("acct_type");
	
	session.setAttribute("school_name", school);
	session.setAttribute("username", username);
	session.setAttribute("password", password);
	//session.setAttribute("acct_type", account);
	
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
					<li id="school"> <%= session.getAttribute("school_name") %> </li>
					<li><a href="./events.jsp">Events</a></li>
					<li><a href="./report.jsp">Report</a></li>
					<li><a href="./payments.jsp">Payments</a></li>
					<li id="acct_type">Account Type</li>
					<li><a href="./login.jsp">Logout</a></li>
				</ul>
				
			</div> <!-- /nav_bar -->	
			
			<p> Welcome back <%= session.getAttribute("username") %></p>
			
			
		</div> <!-- /content -->
	</div> <!-- /wrapper -->
	
</body>
</html>