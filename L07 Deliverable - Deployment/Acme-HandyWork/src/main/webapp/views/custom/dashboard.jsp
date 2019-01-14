<%--
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
<h3><spring:message code="custom.dashboard" /></h3>
</p>

<table border="1">
	<tr>
		<th></th>
		<th><spring:message code="custom.avg" /></th>
		<th><spring:message code="custom.min" /></th>
		<th><spring:message code="custom.max" /></th>
		<th><spring:message code="custom.stddev" /></th>
	</tr>
	<tr>
		<td><spring:message code="custom.fixupperuser" /></td>
		<td>${statistics.get("FixUpTaskPerUser")[0]}</td>
		<td>${statistics.get("FixUpTaskPerUser")[1]}</td>
		<td>${statistics.get("FixUpTaskPerUser")[2]}</td>
		<td>${statistics.get("FixUpTaskPerUser")[3]}</td>
	</tr>
	<tr>
		<td><spring:message code="custom.applicationperfixup" /></td>
		<td>${statistics.get("ApplicationsPerFixUpTask")[0]}</td>
		<td>${statistics.get("ApplicationsPerFixUpTask")[1]}</td>
		<td>${statistics.get("ApplicationsPerFixUpTask")[2]}</td>
		<td>${statistics.get("ApplicationsPerFixUpTask")[3]}</td>
	</tr>

	<tr>
		<td><spring:message code="custom.maximumpricefixup" /></td>
		<td>${statistics.get("MaximumPriceFixUpTask")[0]}</td>
		<td>${statistics.get("MaximumPriceFixUpTask")[1]}</td>
		<td>${statistics.get("MaximumPriceFixUpTask")[2]}</td>
		<td>${statistics.get("MaximumPriceFixUpTask")[3]}</td>
	</tr>
	<tr>
		<td><spring:message code="custom.priceofferefedapplication" /></td>
		<td>${statistics.get("PriceOfferedInApplication")[0]}</td>
		<td>${statistics.get("PriceOfferedInApplication")[1]}</td>
		<td>${statistics.get("PriceOfferedInApplication")[2]}</td>
		<td>${statistics.get("PriceOfferedInApplication")[3]}</td>
	</tr>

	<tr>
		<td><spring:message code="custom.complaintsperfixup" /></td>
		<td>${statistics.get("ComplaintsPerFixUpTask")[0]}</td>
		<td>${statistics.get("ComplaintsPerFixUpTask")[1]}</td>
		<td>${statistics.get("ComplaintsPerFixUpTask")[2]}</td>
		<td>${statistics.get("ComplaintsPerFixUpTask")[3]}</td>
	</tr>


	<tr>
		<td><spring:message code="custom.notesperreport" /></td>
		<td>${statistics.get("NotesPerReport")[0]}</td>
		<td>${statistics.get("NotesPerReport")[1]}</td>
		<td>${statistics.get("NotesPerReport")[2]}</td>
		<td>${statistics.get("NotesPerReport")[3]}</td>
	</tr>
</table>

<p>
<h3>
	<spring:message code="custom.ratio" />
</h3>
</p>
<table border="1">
	<tr>
		<th><spring:message code="custom.fixuptaskcomplaint" /></th>
		<th><spring:message code="custom.ratiopendingapplication" /></th>
		<th><spring:message code="custom.ratioacceptedapplication" /></th>
		<th><spring:message code="custom.ratiorejectedapplication" /></th>
		<th><spring:message code="custom.ratioelapsedapplication" /></th>
	</tr>
	<tr>
		<td>${ratioApplications.get('RatioFixUpTaskComplaint')}</td>
		<td>${ratioApplications.get("RatioPendingApplications")}</td>
		<td>${ratioApplications.get("RatioAcceptedApplications")}</td>
		<td>${ratioApplications.get("RatioRejectedApplications")}</td>
		<td>${ratioApplications.get("RatioApplicationsItsStatusCannotbechanged")}</td>
	</tr>
</table>

<p>
<h3>
	<spring:message code="custom.tops" />
</h3>
</p>

<table border="1">
	<tr>
		<th><spring:message
				code="custom.topThreeCustomerOrderByComplaints" /></th>
	</tr>
	<jstl:forEach
		items="${actors.get('topThreeCustomerOrderByComplaints')}" var="actor">
		<tr>
			<th><jstl:out value="${actor.name}" /> <jstl:out
					value="${actor.surname}" /> </br> <jstl:out value="${actor.email}" /></br>
		</tr>
	</jstl:forEach>
</table>

<table border="1">
	<tr>
		<th><spring:message
				code="custom.topThreeHandyWorkerOrderByComplaints" /></th>
	</tr>
	<jstl:forEach
		items="${actors.get('topThreeHandyWorkerOrderByComplaints')}"
		var="actor">
		<tr>
			<th><jstl:out value="${actor.name}" /> <jstl:out
					value="${actor.surname}" /> </br> <jstl:out value="${actor.email}" /></br>
		</tr>
	</jstl:forEach>
</table>

<p>
<h3>
	<spring:message code="custom.aboveavg" />
</h3>
</p>
<table border="1">
	<tr>
		<th><spring:message
				code="custom.customersWith10PerCentMoreFixUpPublishedThanAvgOrderApps" /></th>
	</tr>

	<jstl:forEach
		items="${actors.get('customersWith10PerCentMoreFixUpPublishedThanAvgOrderApps')}"
		var="actor">
		<tr>
			<th><jstl:out value="${actor.name}" /> <jstl:out
					value="${actor.surname}" /> </br> <jstl:out value="${actor.email}" /></br>
		</tr>
	</jstl:forEach>
</table>
<table border="1">
	<tr>
		<th><spring:message
				code="custom.HandyWorkersWith10PerCentMoreAppsPublishedThanAvgOrderApps" /></th>
	</tr>
	<jstl:forEach
		items="${actors.get('HandyWorkersWith10PerCentMoreAppsPublishedThanAvgOrderApps')}"
		var="actor">
		<tr>
			<th><jstl:out value="${actor.name}" /> <jstl:out
					value="${actor.surname}" /> </br> <jstl:out value="${actor.email}" /></br>
		</tr>
	</jstl:forEach>
</table>