<%@ page import="java.util.List" %>
<%@ page import="org.solent.group.project.model.*" %>
<%!
	private List<Activity> activityList;
	private List<Pupil> pupilList; %>
<%

	String account = (String) session.getAttribute("acct_type");
	
	String action = (String) request.getParameter("action");
    Pupil pupil_acc = (Pupil) session.getAttribute("pupil_acc");

    ActivityList activityList = pupil_acc.getPersonalActivities();
	if ("createActivity".equals(action))
	{
		String task = (String) request.getParameter("name");
		String comment = (String) request.getParameter("comment");
		String author = (String) session.getAttribute("username");
        String free = request.getParameter("free");

		try {
		    Activity activity = new Activity();
		    if (free.equals("true")) {
                activity.setFree(true);
            }
		    else{
		        activity.setFree(false);
            }
		    activity.setAuthor(author);
		    activity.setPupilComment(comment);
		    activity.setTask(task);

		    pupil_acc.createActivity(activity);

        }
		catch (Exception e) {
            String errorMessage = "failed to validate " + e.getMessage();
    }

	}


%>

<html>
<head>
  <link rel="stylesheet" type="text/css" href="./css/main.css">
  <script src="./scripts/main.js"></script>
  <title>Activities</title>
</head>
<body onLoad="main()">
	<div id="wrapper">
		<div id="header">
			<h1>Activities page</h1>
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

			<h3><a href="addOrModifyActivity.jsp?action=createActivity">Create activity</a></h3>
			<%
				for (int i = 0; i < activityList.getListSize(); i++){
					Activity activity = activityList.getActivitybyId(i);
			%>
				<h3 id="<%=activity%>"><%= activity.getTask() %></h3>
			    <h3><a href="addOrModifyActivity.jsp?action=modifyActivity&id=<%= i %>">Modify activity</a></h3>

			<%
                if (!activity.isFree()){
            %>
                    <a href="invoice.jsp?id=<%=activity.getActId()%>">Invoice</a>
            <%
                    }
                }
			%>

		</div> <!-- /content_activities -->
	</div> <!-- /wrapper -->
	
</body>
</html>