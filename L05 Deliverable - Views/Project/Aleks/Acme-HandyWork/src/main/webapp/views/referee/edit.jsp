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

<form:form modelAttribute="referee" action="welcome/index.jsp/">
<form:hidden path="id"/>
<form:hidden path="version"/>

<form:label path="Name">
<spring:message code="referee.name"></spring:message>
</form:label>
<form:input path="text"/>
<br>

<form:label path="Surname">
<spring:message code="referee.surname"></spring:message>
</form:label>
<form:input path="text"/>
<br>

<form:label path="Middle Name">
<spring:message code="referee.middlename"></spring:message>
</form:label>
<form:input path="text"/>
<br>

<form:label path="Phone">
<spring:message code="referee.phone"></spring:message>
</form:label>
<form:input path="text"/>
<br>

<form:label path="Email">
<spring:message code="referee.email"></spring:message>
</form:label>
<form:input path="text"/>
<br>

<form:label path="Adress">
<spring:message code="referee.adress"></spring:message>
</form:label>
<form:input path="text"/>
<br>

<form:label path="URL for a Photo">
<spring:message code="referee.photo"></spring:message>
</form:label>
<form:input path="text"/>
<br>

<form:label path="User">
<spring:message code="referee.user"></spring:message>
</form:label>
<form:input path="text"/>
<br>

<form:label path="Password">
<spring:message code="referee.password"></spring:message>
</form:label>
<form:password path="text"/>
<br>
</form:form>

<input type="submit" value="<spring:message code=referee.save/>">