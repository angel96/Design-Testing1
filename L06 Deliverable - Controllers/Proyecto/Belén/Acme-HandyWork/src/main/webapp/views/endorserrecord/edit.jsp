<%--
 * action-1.jsp
 *
 * Copyright (C) 2018 Universidad de Sevilla
 * 
 * The use of this project is hereby constrained to the conditions of the 
 * TDG Licence, a copy of which you may download from 
 * http://www.tdg-seville.info/License.html
 --%>

<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl"	uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>

<p><spring:message code="endorser.edit" /></p>

<form:form modelAttribute="endorserrecord" action="endorserrecord/edit.do">

<form:hidden path="id" />
<form:hidden path="version" />

<form:label path="fullNameEndorser">
	<spring:message code="endorser.fullNameEndorser" />
</form:label>
<form:input path="fullNameEndorser"/>
<form:errors cssClass="error" path="fullNameEndorser"></form:errors>
<br>

<form:label path="email">
	<spring:message code="endorser.email" />
</form:label>
<form:input path="email"/>
<form:errors cssClass="error" path="email"></form:errors>
<br>

<form:label path="phone">
	<spring:message code="endorser.phone" />
</form:label>
<form:input path="phone"/>
<form:errors cssClass="error" path="phone"></form:errors>
<br>

<form:label path="linkedin">
	<spring:message code="endorser.linkedin" />
</form:label>
<form:input path="linkedin"/>
<form:errors cssClass="error" path="linkedin"></form:errors>
<br>
</form:form>
<security:authorize access="hasRole('HANDY_WORKER')">
<input type="submit" name="save"
			value="<spring:message code="endorser.save" />" />
<input type="submit" name="cancel" value="<spring:message code ="endorser.cancel"/>"
	onclick="javascript: relativeRedir('curriculum/handyworker/list.do');" />
</security:authorize>

