<%
	//temp activity list for tests
	String[] activity_list = {"activity 1", "activity 2", "activity 3", "activity 4", "activity 5", "activity 6", "activity 7"
			, "activity 8", "activity 9", "activity 10", "activity 11", "activity 12", "activity 13"};
			
	String account = (String) session.getAttribute("acct_type");
	
	String action = (String) request.getParameter("action");
	if ("createActivity".equals(action))
	{
		Image uploadedFile = (Image) request.getParameter("image");
		String event_name = (String) request.getParameter("name");
		String comment = (String) request.getParameter("comment");
		
		//Activity activity = new Activity();
		//activity.createActivity(event_name, comment, uploadedFile);
	}
	
%>

<html>
<head>
  <link rel="stylesheet" type="text/css" href="./css/main.css">
  <script src="./scripts/main.js"></script>
  <title>Events</title>
</head>
<body onLoad="main()">
	<div id="wrapper">
		<div id="header">
			<h1>Events page</h1>
			<p id="date"></p>
			<p id="time"></p>
		</div> <!-- /header -->
		
		<div id="content_activities">
			<div id="nav_bar">
				<!-- create list for nav bar -->
				<ul>
					<li id="school"> <%= session.getAttribute("school_name") %> </li>
					<li><a href="./events.jsp">Events</a></li>
					<li><a href="./report.jsp">Report</a></li>
					<li><a href="./payments.jsp">Payments</a></li>
					<li id="acct_type"> <%= session.getAttribute("acct_type") %> </li>
					<li><a href="./login.jsp?action=logout">Logout</a></li>
				</ul>
				
			</div> <!-- /nav_bar -->	
			<%
				if(account.equals("Teacher") || account.equals("Board") || account.equals("Admin")){
			%>
					<h2><a href="./addOrModifyActivity.jsp?action=createActivity">Create Activity</a></h2>
			<%
				}
				
				for (int i=0; i < activity_list.length; i++)
				{
			%>
					<h3><%=activity_list[i]%></h3>
					<%
						if(account.equals("Board") || account.equals("Admin")){
					%>
							<a href="./addOrModifyActivity.jsp?action=modifyActivity&id=<%=i+1%>">Edit Activity</a><br/>
							<a href="./invoice.jsp?id=<%=i+1%>">Invoice</a>
					<%
						}
					%>
					<br/>
			<%
				}
			%>
			
			
		</div> <!-- /content_activities -->
	</div> <!-- /wrapper -->
	
</body>
</html>