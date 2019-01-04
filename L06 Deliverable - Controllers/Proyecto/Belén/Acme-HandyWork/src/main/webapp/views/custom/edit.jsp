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

<form:form action="customisation/administrator/edit.do"
	modelAttribute="customisationsystem">
	<form:hidden path="id" />
	<form:hidden path="version" />

	<form:label path="systemName">
		<spring:message code="custom.system" />
	</form:label>
	<form:input path="systemName" />
	</br>
	<form:label path="banner">
		<spring:message code="custom.banner" />
	</form:label>
	<form:input path="banner" />
	</br>
	<form:label path="message">
		<spring:message code="custom.message" />
	</form:label>
	<form:textarea path="message" />
	</br>
	<form:label path="hoursFinder">
		<spring:message code="custom.hoursFinder" />
	</form:label>
	<form:input path="hoursFinder" />
	</br>
	<form:label path="resultFinder">
		<spring:message code="custom.resultFinder" />
	</form:label>
	<form:input path="resultFinder" />
	</br>
	<form:label path="goodWords">
		<spring:message code="custom.goodWords" />
	</form:label>

	<form:textarea path="goodWords" />
	</br>
	<form:label path="badWords">
		<spring:message code="custom.badWords" />
	</form:label>
	<form:textarea path="badWords" />
	</br>
	<form:label path="spamWords">
		<spring:message code="custom.spamWords" />
	</form:label>
	<form:textarea path="spamWords" />
	</br>
	<input type="submit" name="save"
		value="<spring:message code="custom.save"/>" />
</form:form>
