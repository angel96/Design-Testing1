<%--
 * action-1.jsp
 *
 * Copyright (C) 2018 Universidad de Sevilla
 * 
 * The use of this project is hereby constrained to the conditions of the 
 * TDG Licence, a copy of which you may download from 
 * http://www.tdg-seville.info/License.html
 --%>
<link
	href="https://code.jquery.com/ui/1.10.4/themes/ui-lightness/jquery-ui.css"
	rel="stylesheet">
<script>
	$(function() {
		$("#datepicker-1").datepicker({
			appendText : "(yy-mm-dd)",
			dateFormat : "yy-mm-dd"
		});
	});
</script>
<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>

<p>
	<spring:message code="find.action.1" />
</p>

<form:form action=""
	modelAttribute="finderObject">

	<form:label path="query">
		<spring:message code="finder.key" />
	</form:label>
	<form:input path="query" />
	<br />

	<form:select path="" multiple="false" size="1">
		<form:option value="None" />
		<form:options items="${categories}" />
	</form:select>

	<form:select path="" multiple="false" size="1">
		<form:option value="None" />
		<form:options items="${warranties}" />
	</form:select>

	<form:label path="">
		<spring:message code="finder.min" />
	</form:label>
	<form:input path="" type="text" />

	<form:label path="">
		<spring:message code="finder.max" />
	</form:label>
	<form:input path="" type="text" />

	<form:label path="">
		<spring:message code="finder.date1" />
	</form:label>
	<form:input path="" type="text" id="datepicker-1" />

	<form:label path="">
		<spring:message code="finder.date2" />
	</form:label>
	<form:input path="" type="text" id="datepicker-1" />

	<input type="submit" name="find"
		value="<spring:message code="finder.find" />" />
</form:form>