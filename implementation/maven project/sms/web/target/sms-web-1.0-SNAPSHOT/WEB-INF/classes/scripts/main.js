function main() {
	setInterval(function() {
		// set up a new date object 
		var today = new Date();
		//extract the hour min and seconds and store them in a variable 
		var curr_hour = today.getHours();
		var curr_minute = today.getMinutes();
		var curr_second = today.getSeconds();

		//if statements to add a 0 to the front if it is less than 10
		if (curr_hour < 10) {
		  curr_hour = "0" + curr_hour;
		}
		if (curr_minute < 10) {
		  curr_minute = "0" + curr_minute;
		}
		if (curr_second < 10) {
		  curr_second = "0" + curr_second;
		}
		//extract the day, month and year and store them

		var curr_day = today.getDate();

		// get month and add 1 as it starts at 0
		var curr_month = today.getMonth() + 1;
		var curr_year = today.getFullYear();

		//if the date or month is less than 10 then add a 0
		if (curr_day < 10) {
		  curr_day = "0" + curr_day;
		}
		if (curr_month < 10) {
		  curr_month = "0" + curr_month;
		}
		
		//convert into 1 date and 1 time
		var curr_date = curr_day + "/" + curr_month + "/" + curr_year;

		var curr_time = curr_hour + ":" + curr_minute + ":" + curr_second;
		//display current time
		document.getElementById("time").innerHTML = "Time: " + curr_time;
		document.getElementById("date").innerHTML = "Date: " + curr_date;
	}, 1000);
}