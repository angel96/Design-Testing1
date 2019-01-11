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

<security:authorize access="hasRole('ADMIN')">
	<form:form action="category/administrator/edit.do?parent=${parent}"
		modelAttribute="category">

		<form:hidden path="id" />
		<form:hidden path="version" />

		<form:label path="name">
			<spring:message code="category.name" />
		</form:label>
		<form:input path="name" />
		<br />

		<input type="submit" name="save"
			value="<spring:message code="category.save" />" />
			
		<input type="submit" name="delete"
			value="<spring:message code="category.delete" />" />
	</form:form>

	<input type="submit" name="cancel"
		value="<spring:message code ="warranty.cancel"/>"
		onclick="javascript: relativeRedir('category/administrator/list.do');" />
</security:authorize>