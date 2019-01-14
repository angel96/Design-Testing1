<%--
 * action-1.jsp
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

<p>
	<spring:message code="application.action.1" />
</p>

<display:table name="applications" id="row" requestURI="${requestURI}"
	pagesize="5" class="displaytag">

	<display:column property="moment" titleKey="application.moment" />
	<display:column property="status" titleKey="application.status" />
	<display:column property="fixUpTask.ticker"
		titleKey="application.fixUpTask" />
	<display:column property="offeredPrice"
		titleKey="application.offeredPrice">$</display:column>
	<security:authorize access="hasRole('HANDY_WORKER')">
		<display:column>
			<a href="application/handyworker/edit.do?id=${row.id}"> <img
				src="images/update.png">
			</a>
		</display:column>
	</security:authorize>
	<security:authorize access="hasRole('CUSTOMER')">
		<display:column titleKey="application.updateStatus">
			<a href="application/customer/edit.do?id=${row.id}"> <img
				src="images/update.png">
			</a>
		</display:column>
	</security:authorize>
</display:table>

<script>
	var table = document.getElementById("row");
	var tbody = table.getElementsByTagName("tbody")[0];
	var row = tbody.getElementsByTagName("tr");

	for (i = 0; i < row.length; i++) {
		var value = row[i].getElementsByTagName("td")[1].firstChild.nodeValue;
		if (value == 'accepted') {
			row[i].style.backgroundColor = "#00FF80";
		} else if (value == 'rejected') {
			row[i].style.backgroundColor = "#FF8000";
		} else if (value == 'pending') {
		} else {
			row[i].style.backgroundColor = "#BDBDBD";
		}
	}
</script>