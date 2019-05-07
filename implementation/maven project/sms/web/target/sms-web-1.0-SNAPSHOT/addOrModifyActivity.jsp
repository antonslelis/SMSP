<%
	String action = (String) request.getParameter("action");
	if ("modifyActivity".equals(action)){
		int activity_id = Integer.parseInt(request.getParameter("id"));
		
		/*
		Activity activity = getActivityById(activity_id);
		
		String name = activity.getTask();
		String comment = activity.getComment();
		int pupilId = activity.getPupilId();
		
		Pupil pupil = getPupilById(pupilId);
		String pupil_name = pupil.getName();
		*/
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
	<form id="createActivity_form" action="./events.jsp" method="post">

			<label id="event_label">Event Name:</label>
			<input type="text" name="name"><br/>

			<label id="comment_label">Comment:</label>
			<textarea name="comment"></textarea><br/>
			
			<label id="image_label">Upload Image:</label>
			<input type="file" name="image" accept="image/*"></input>
			
			<input type="hidden" name="action" value="createActivity"></input>
			<p><input type="submit" id="createActivity_btn" value="Create activity"></p>
		
	</form>
</body>
</html>