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

<form:form action="${requestURI}" modelAttribute="tutorial">

	<form:hidden path="id" />
	<form:hidden path="version" />
	<form:hidden path="sponsorship" />
	<form:hidden path="section" />

	<form:label path="title">
		<spring:message code="tutorial.title" />
	</form:label>
	<form:input path="title" readonly="${view}" />
	<br />
	<form:label path="lastUpdate">
		<spring:message code="tutorial.lastUpdate" />
	</form:label>
	<form:input path="lastUpdate" readonly="true" />
	<br />
	<form:label path="summary">
		<spring:message code="tutorial.summary" />
	</form:label>
	<form:textarea path="summary" readonly="${view}" />
	<br />
	<form:label path="picture">
		<spring:message code="tutorial.picture" />
	</form:label>
	<form:textarea path="picture" readonly="${view}" />
	<br />
	<jstl:forEach items="${errors}" var="error">
		<jstl:out value="${error}" />
	</jstl:forEach>
	<jstl:out value="${oops}" />
	<jstl:out value="${message}" />
	<security:authorize access="hasRole('HANDY_WORKER')">
		<input type="submit" name="save"
			value="<spring:message code ="tutorial.save"/>" />
		<jstl:if test = "${tutorial.id != 0}">
		<input type="submit" name="delete"
			value="<spring:message code ="tutorial.delete"/>" />
			</jstl:if>
	</security:authorize>
</form:form>
<input type="submit" name="cancel"
	value="<spring:message code ="tutorial.cancel"/>"
	onclick="javascript: relativeRedir('/welcome/index.do');" />