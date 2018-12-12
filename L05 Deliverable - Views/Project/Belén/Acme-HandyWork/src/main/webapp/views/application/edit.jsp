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

<form:form action="handyworker/application/edit.do"
	modelAttribute="applicationObject">
	
	<form:hidden path="id" />
	<form:hidden path="version" />

	<form:label path="fixUpTask">
		<spring:message code="application.fixUpTask" />
	</form:label>
	<form:input path="fixUpTask" readonly="${fixUpTaskObject.ticker}" />
	<form:label path="moment">
		<spring:message code="application.moment" />
	</form:label>
	<form:input path="moment" readonly="${applicationObject.moment}" />
	<br/>
	<br/>
	<form:label path="status">
		<spring:message code="application.status" />
	</form:label>
	<form:select path="status" size="1" disabled>
		<form:option value=""/>
		<form:options items="${status}"/>
	</form:select>
	<br/>
	<security:authorize access="hasRole('HANDY_WORKER')">
	<form:label path="offeredPrice">
		<spring:message code="application.offeredPrice" />
	</form:label>
	<form:input path="offeredPrice"/>
	</security:authorize>
	<br/>

</form:form>

<security:authorize access="hasRole('HANDY_WORKER')">
<input type="submit" name="save"
	value="<spring:message code ="application.save"/>"
	onclick="javascript: relativeRedir('handyworker/application/edit.do');" />
</security:authorize>

<input type="submit" name="cancel"
	value="<spring:message code ="application.cancel"/>"
	onclick="javascript: relativeRedir('handyworker/application/list.do');" />
