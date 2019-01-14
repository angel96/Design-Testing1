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

<script>
	$(function() {
		$("#datepicker-1").datepicker({
			appendText : "(yy/mm/dd)",
			dateFormat : "yy/mm/dd"
		});
		$("#datepicker-2").datepicker({
			appendText : "(yy/mm/dd)",
			dateFormat : "yy/mm/dd"
		});
	});
</script>

<form:form action="${requestURI}" modelAttribute="phase">
	<form:hidden path="id" />
	<form:hidden path="version" />

	<form:label path="number">
		<spring:message code="phase.number" />
	</form:label>
	<form:input path="number" readonly="${view}" />
	<form:errors cssClass="error" path="number" />

	<br>
	<form:label path="title">
		<spring:message code="phase.title" />
	</form:label>
	<form:input path="title" readonly="${view}" />
	<form:errors cssClass="error" path="title" />

	<form:label path="description">
		<spring:message code="phase.description" />
	</form:label>
	<form:input path="description" readonly="${view}" />
	<form:errors cssClass="error" path="description" />

	<form:label path="startMoment">
		<spring:message code="phase.startMoment" />
	</form:label>
	<form:input path="startMoment" readonly="${view}" id = "datepicker-1"/>

	<form:label path="endMoment">
		<spring:message code="phase.endMoment" />
	</form:label>
	<form:input path="endMoment" readonly="${view}" id = "datepicker-2"/>
	<br />
	<security:authorize access="hasRole('HANDY_WORKER')">
		<input type="submit" name="save"
			value="<spring:message code="phase.save" />" />
		<jstl:if test="${phase.id != 0}">
			<input type="submit" name="delete"
				value="<spring:message code="phase.delete" />" />
		</jstl:if>
	</security:authorize>
	<jstl:forEach items="${errors}" var="error">
		<jstl:out value="${error}" />
	</jstl:forEach>
	<jstl:out value="${oops}" />
	<jstl:out value="${message}" />
</form:form>

<input type="button" name="cancel"
	value="<spring:message code="phase.cancel"/>"
	onclick="javascript:relativeRedir('/welcome/index.do');" />

