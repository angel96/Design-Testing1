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
	<spring:message code="report.intro" />
</p>

<form:form action="report/referee/edit.do?idComp=${idComp}"
	modelAttribute="report">

	<form:hidden path="id" />
	<form:hidden path="version" />
	<form:hidden path="moment" />
	<form:hidden path="complaint"/>
	<jstl:if test="${report.id == 0}">
	<form:hidden path="finalMode"/>
	</jstl:if>

	<form:label path="moment">
		<spring:message code="report.moment" />
	</form:label>
	<form:input path="moment" readonly="true"/>
	<form:errors cssClass="error" path="moment" />
	<br />
	
	<form:label path="description">
		<spring:message code="report.description" />
	</form:label>
	<form:input path="description" />
	<form:errors cssClass="error" path="description" />
	<br />

	<form:label path="attachments">
		<spring:message code="report.attachments" />
	</form:label>
	<form:input path="attachments" />
	<form:errors cssClass="error" path="attachments" />
	<br />
	
<jstl:if test="${report.id != 0}">
	<form:label path="finalMode">
		<spring:message code="report.finalMode" />
	</form:label>

	<form:checkbox path="finalMode" />
</jstl:if>
<br />
<!-- 
	<form:label path="notes"> //boton que redirija a una vista con las notas de x report?
		<spring:message code="report.notes" />
	</form:label>
	<form:input path="notes" />
	<form:errors cssClass="error" path="notes" />
	<br />
	-->

	<input type="submit" name="save"
			value="<spring:message code="report.save" />" />
</form:form>

<input type="submit" name="cancel"
	value="<spring:message code ="note.cancel"/>"
	onclick="javascript: relativeRedir('note/customer/list.do');" />