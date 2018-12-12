<%--
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

<form:form modelAttribute="message" action="message/send.do/">
<form:hidden path="id"/>
<form:hidden path="version"/>

<form:label path="Subject">
<spring:message code="handyworker.box.subject"></spring:message>
</form:label>
<form:input path="text"/>
<br>

<form:label path="Recipient">
<spring:message code="handyworker.box.recipient"></spring:message>
</form:label>
<form:input path="text"/>
<br>

<form:select path="message"></form:select>
<form:options item="${message}" itemLabel="Tags" itemValue="id"/>
<form:option label="<spring:message code="handyworker.low"></spring:message>" value="0"></form:option>
<form:option label="<spring:message code="handyworker.neutral"></spring:message>" value="1"></form:option>
<form:option label="<spring:message code="handyworker.high"></spring:message>" value="2"></form:option>
<br>

<form:label path="Content">
<spring:message code="handyworker.content"></spring:message>
</form:label>
<form:textarea path="text"/>
<br>

<form:label path="Tags">
<spring:message code="handyworker.tags"></spring:message>
</form:label>
<form:input path="text"/>
<br>
</form:form>