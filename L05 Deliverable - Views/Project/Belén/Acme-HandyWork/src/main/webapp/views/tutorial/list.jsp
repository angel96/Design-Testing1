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
	<spring:message code="tutorial.action.1" />
</p>

<display:table name="tutorials" id="row"
	requestURI="${requestURI}" pagesize="3"
	class="displaytag">

	<display:column property="title" titleKey="tutorial.title"/>
	<display:column property="lastUpdate" titleKey="tutorial.lastUpdate"/>
	<display:column property="summary" titleKey="application.summary"/>
	<display:column>
		<a href="tutorial/handyworker/edit.do?tutorialId=${row.id}">
			<img src="images/viewmore.png">
		</a>
	</display:column>
	<security:authorize access="hasRole('HANDY_WORKER')">
	<display:column>
		<a href="tutorial/handyworker/edit.do?tutorialId=${row.id}">
			<img src="images/update.png">
		</a>
		<a href="tutorial/handyworker/list.do?tutorialId=${row.id}">
			<img src="images/trash.png">
		</a>
	</display:column>
	</security:authorize>
</display:table>