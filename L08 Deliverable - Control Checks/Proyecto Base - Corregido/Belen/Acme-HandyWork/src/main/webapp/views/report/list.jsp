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
	<spring:message code="complaint.action.1" />
</p>

<display:table name="reports" id="row" requestURI="${requestURI}"
	pagesize="5" class="displaytag">

	<display:column property="moment" titleKey="report.moment" />
	<display:column property="description" titleKey="report.description" />
	<display:column property="attachments" titleKey="report.attachments" />
	<display:column property="finalMode" titleKey="report.finalMode" />
	<security:authorize access="hasRole('REFEREE')">
		<display:column titleKey="report.edit">
			<jstl:if test="${row.finalMode eq 'false'}">
				<a href="report/referee/update.do?idRep=${row.id}"> <spring:message
						code="report.edit" />
				</a>
			</jstl:if>
		</display:column>
	</security:authorize>
		<security:authorize access="hasRole('REFEREE')">
			<display:column titleKey="report.delete">
			<jstl:if test="${row.finalMode eq 'false'}">
				<a href="report/referee/delete.do?idRep=${row.id}"> <spring:message
						code="report.delete" />
				</a></jstl:if>
			</display:column>
		</security:authorize>
		<security:authorize access="hasRole('REFEREE')">
			<display:column>
			<jstl:if test="${row.finalMode eq 'true'}">
			<a href="note/referee/create.do?idRep=${row.id}"> <spring:message
						code="note.create" />
				</a>
				</jstl:if>
			</display:column>
			</security:authorize>
		<security:authorize access="hasRole('CUSTOMER')">
			<display:column>
			<a href="note/customer/create.do?idRep=${row.id}"> <spring:message
						code="note.create" />
				</a>
			</display:column>
			</security:authorize>
		<security:authorize access="hasRole('HANDY_WORKER')">
			<display:column>
			<a href="note/handyworker/create.do?idRep=${row.id}"> <spring:message
						code="note.create" />
				</a>
			</display:column>
			</security:authorize>


</display:table>
