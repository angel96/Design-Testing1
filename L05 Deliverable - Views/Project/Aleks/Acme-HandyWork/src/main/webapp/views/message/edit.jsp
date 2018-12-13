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

<form:form modelAttribute="message" action="message/edit.do/">
<form:hidden path="id"/>
<form:hidden path="version"/>

<form:label path="subject">
<spring:message code="message.subject"></spring:message>
</form:label>
<form:input path="subject"/>
<br>

<form:label path="recipient">
<spring:message code="message.recipient"></spring:message>
</form:label>
<form:input path="recipient"/>
<br>

<form:select path="message"></form:select>
<form:options item="${message}" itemLabel="tags" itemValue="id"/>
<form:option label="----" value="0"></form:option>
<br>

<form:label path="content">
<spring:message code="message.content"></spring:message>
</form:label>
<form:textarea path="content"/>
<br>

<form:label path="tags">
<spring:message code="message.tags"></spring:message>
</form:label>
<form:input path="tags"/>
<br>
</form:form>
<input type="submit" name="save" value="<spring:message code=message.save/>"
onclick="javascript:relativeRedir('message/edit.do/');"/>
<input type="button" name="cancel" value="<spring:message code=message.cancel/>"
onclick="javascript:relativeRedir('welcome/index.jsp');"/>