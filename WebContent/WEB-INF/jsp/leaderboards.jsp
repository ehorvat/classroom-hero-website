<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<!--Load the AJAX API-->
<script type="text/javascript" src="https://www.google.com/jsapi"></script>



<script type="text/javascript">


  // Load the Visualization API library and the piechart library.
  google.load('visualization', '1.0', {'packages':['corechart']});
  google.setOnLoadCallback(drawChart);
     // ... draw the chart...
     
      function drawChart() {

      // Create the data table.
      var data = new google.visualization.DataTable();
      data.addColumn('string', 'Student');
      data.addColumn('number', 'Points');
       
      <c:forEach var='student' items='${topTenStudents}'  varStatus="status"> 
 
             var fname = ${student.fname};
             var points = ${student.totalCoins};
             alert(fname);
             alert(points);
          
             data.addRow([fname, points]);  
             
      </c:forEach> 

      // Set chart options
        var options = {
          title: 'Top 10 Students',
          vAxis: {title: 'Students',  titleTextStyle: {color: 'red'}}
        };

      // Instantiate and draw our chart, passing in some options.
      var chart = new google.visualization.BarChart(document.getElementById('chart_div'));
      chart.draw(data, options);
    }

</script>



<title >Classroom Hero Statistics</title>
</head>
<body>
<h1 style="margin 0; text-align:center;"> Classroom Hero Statistics</h1><br>
<h1 style="margin 0; text-align:center; size:80%;"> This page is currently under construction...</h1>

<!--Div that will hold the pie chart-->
    <div id="chart_div" style="width: 900px; height: 500px;"></div>


</body>
</html>