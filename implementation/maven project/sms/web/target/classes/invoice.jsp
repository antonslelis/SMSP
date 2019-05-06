<%
	int activity_id = Integer.parseInt(request.getParameter("id"));
%>

<html>
<head>
  <link rel="stylesheet" type="text/css" href="./css/main.css">
  <title>Invoice</title>
</head>
<body>
	<h1>Please enter your invoice details.</h1>

	<form id="invoice_form" action="./events.jsp" method="post">
			<label id="event_label">Event Name:</label>
			<input type="text" name="name"><br/>

			<label id="price_label">Price:</label>
			<input type="number" name="price" value="£"></input><br/>
			
			<input type="hidden" name="action" value="createInvoice"></input>
			<p><input type="submit" id="createInvoice_btn" value="Create invoice"></p>
		
	</form>
</body>
</html>