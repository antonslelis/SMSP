<%@ page import="org.solent.group.project.model.Activity" %>
<%@ page import="org.solent.group.project.model.Pupil" %>
<%@ page import="org.solent.group.project.model.Board" %>
<%@ page import="java.util.List" %>
<%@ page import="org.solent.group.project.model.ActivityList" %><%
	String action = (String) request.getParameter("action");
	String account = (String) session.getAttribute("acct_type");

	if ("modifyActivity".equals(action)){
		int activity_id = Integer.parseInt(request.getParameter("id"));


		Pupil pupil_acc = (Pupil) session.getAttribute("pupil_acc");
		Activity activity = pupil_acc.getPersonalActivities().getActivitybyId(activity_id);





	}
	
	
%>

<html>
<head>
	<link rel="stylesheet" type="text/css" href="./css/main.css">
	<%
		if ("createActivity".equals(action)){
	%>
	<title>Add Activity</title>
	<%
		}
		else {		
	%>	
	<title>Modify Activity</title>
	<%
		}
	%>
	
</head>
<body>
	<h1>Please enter the activity details.</h1>
	<h2><a href="./events.jsp">Back</a></h2>
	<form id="createActivity_form" action="./activities.jsp" method="post">

		<label id="task_label">Task:</label>
		<input type="text" name="task"><br/>

		<label id="comment_label">Pupil Comment:</label>
		<textarea name="comment"></textarea><br/>


		<% if (account.equals("BOARD")){
		%>

		<label id="free_label">Free?:</label>
		<input type="checkbox" name="free" value="true"><br/>
		<% }
		%>
		<input type="hidden" name="action" value="createActivity"/>
		<p><input type="submit" id="createActivity_btn" value="Create activity"></p>
		
	</form>
</body>
</html>