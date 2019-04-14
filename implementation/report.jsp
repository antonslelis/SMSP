<%

%>
<html>
<head>
  <link rel="stylesheet" type="text/css" href="./css/main.css">
  <script src="./scripts/main.js"></script>
  <title>Report</title>
</head>
<body onLoad="main()">
	<div id="wrapper">
		<div id="header">
			<h1>Report page</h1>
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
			
			
			
			
		</div> <!-- /content -->
	</div> <!-- /wrapper -->
	
</body>
</html>