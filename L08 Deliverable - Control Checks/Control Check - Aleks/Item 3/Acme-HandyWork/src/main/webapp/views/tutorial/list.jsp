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

<display:table name="tutorials" id="row" requestURI="${requestURI}"
	pagesize="5" class="displaytag">

	<display:column property="title" titleKey="tutorial.title" />
	<display:column property="lastUpdate" titleKey="tutorial.lastUpdate" />
	<display:column property="summary" titleKey="tutorial.summary" />
	<display:column titleKey="tutorial.viewmore">
		<a href="tutorial/show.do?id=${row.id}"> <spring:message
				code="tutorial.viewmore" />
		</a>
	</display:column>
	<display:column titleKey="tutorial.section">
		<a href="section/list.do?tutorial=${row.id}"> <spring:message
				code="tutorial.section" />
		</a>
	</display:column>
	<security:authorize access="hasRole('HANDY_WORKER')">
		<display:column titleKey="tutorial.edit">
			<a href="tutorial/handyworker/edit.do?id=${row.id}"> <spring:message
					code="tutorial.edit" />
			</a>
		</display:column>
	</security:authorize>
	<security:authorize access="hasRole('HANDY_WORKER')">
		<display:column titleKey="tutorial.sponsorships">
			<a href="sponsorship/handyworker/list.do?tutorial=${row.id}"> <spring:message
					code="tutorial.sponsorships" />
			</a>
		</display:column>
	</security:authorize>
	<security:authorize access="hasRole('SPONSOR')">
		<display:column titleKey="tutorial.sponsorships">
			<a href="sponsorship/sponsor/create.do?tutorial=${row.id}"> <spring:message
					code="tutorial.sponsor" />
			</a>
		</display:column>
	</security:authorize>
</display:table>