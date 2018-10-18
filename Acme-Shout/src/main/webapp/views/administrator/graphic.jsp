<%--
 * action-2.jsp
 *
 * Copyright (C) 2018 Universidad de Sevilla
 * 
 * The use of this project is hereby constrained to the conditions of the 
 * TDG Licence, a copy of which you may download from 
 * http://www.tdg-seville.info/License.html
 --%>

<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<script type="text/javascript"
	src="https://www.gstatic.com/charts/loader.js">
</script>
<script type="text/javascript">
	google.charts.load('current', {packages : ['corechart']});
    function drawChart() {
       // Define the chart to be drawn.
       var data = google.visualization.arrayToDataTable([
          ['<spring:message code="administrator.indicator" />', '<spring:message code="administrator.value" />'],
          ['<spring:message code="administrator.count.all.shouts" />',  parseInt('${statistics.get("count.all.shouts")}')],
          ['<spring:message code="administrator.count.short.shouts" />',  parseInt('${statistics.get("count.short.shouts")}')],
          ['<spring:message code="administrator.count.long.shouts" />',  parseInt('${statistics.get("count.long.shouts")}')]
       ]);

       var options = {title: 'Acme - Shouts'}; 

       // Instantiate and draw the chart.
       var chart = new google.visualization.ColumnChart(document.getElementById('char_div'));
       chart.draw(data, options);
    }
    google.charts.setOnLoadCallback(drawChart);
</script>
<p>
	<spring:message code="administrator.graphic" />
</p>
<div id="char_div"></div>