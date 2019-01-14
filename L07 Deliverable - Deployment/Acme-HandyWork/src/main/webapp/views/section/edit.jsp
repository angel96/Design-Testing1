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

<form:form action="${requestURI}" modelAttribute="section">
	<form:hidden path="id" />
	<form:hidden path="version" />

	<form:label path="title">
		<spring:message code="section.title" />
	</form:label>
	<form:input path="title" readonly="${view}" />
	<form:errors cssClass="error" path="title" />
	<br />
	<form:label path="text">
		<spring:message code="section.text" />
	</form:label>
	<form:input path="text" readonly="${view}" />
	<form:errors cssClass="error" path="text" />
	<br />
	<form:label path="picture">
		<spring:message code="section.picture" />
	</form:label>
	<form:input path="picture" readonly="${view}" />
	<form:errors cssClass="error" path="picture" />
	<br />
	<form:label path="number">
		<spring:message code="section.number" />
	</form:label>
	<form:input path="number" readonly="${view}" />
	<form:errors cssClass="error" path="number" />
	<br />
	<jstl:forEach items="${errors}" var="error">
		<jstl:out value="${error}" />
	</jstl:forEach>
	<jstl:out value="${oops}" />
	<jstl:out value="${message}" />
	<security:authorize access="hasRole('HANDY_WORKER')">
		<input type="submit" name="save"
			value="<spring:message code="section.save" />" />

		<jstl:if test="${section.id != 0}">

			<input type="submit" name="delete"
				value="<spring:message code="section.delete" />" />

		</jstl:if>

	</security:authorize>
</form:form>

<input type="button" name="cancel"
	value="<spring:message code="section.cancel"/>"
	onclick="javascript:relativeRedir('/welcome/index.do');" />

