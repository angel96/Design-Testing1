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

<display:table name="applications" id="row"	requestURI="${requestURI}" pagesize="5"	class="displaytag">

	<display:column property="moment" titleKey="application.moment"/>
	<display:column property="status" titleKey="application.status"/>
	<display:column property="offeredPrice" titleKey="application.offeredPrice">$</display:column>
	<display:column titleKey="application.fixUpTask" >
	 <jstl:out value = "${row.fixUpTask.description}"/>
	 </display:column>
	<security:authorize access="hasRole('HANDY_WORKER')">	
	<display:column>
		<a href="application/handyworker/edit.do?id=${row.id}">
			<img src="images/update.png">
		</a>
	</display:column>
	</security:authorize>
	<security:authorize access="hasRole('CUSTOMER')">	
	<display:column titleKey="application.updateStatus">
		<a href="application/customer/edit.do?id=${row.id}">
			<img src="images/update.png">
		</a>
	</display:column>
	</security:authorize>
</display:table>