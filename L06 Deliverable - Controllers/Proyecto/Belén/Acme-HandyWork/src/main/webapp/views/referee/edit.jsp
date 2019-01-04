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

<form:form modelAttribute="referee" action="referee/edit.do">
<form:hidden path="id"/>
<form:hidden path="version"/>
<form:hidden path="account.id" />
<form:hidden path="account.authorities" />

<form:label path="name">
<spring:message code="refe.name"></spring:message>
</form:label>
<form:input path="name"/>
<form:errors cssClass="error" path="name"></form:errors>
<br>

<form:label path="surname">
<spring:message code="refe.surname"></spring:message>
</form:label>
<form:input path="surname"/>
<form:errors cssClass="error" path="surname"></form:errors>
<br>

<form:label path="middleName">
<spring:message code="refe.middleName"></spring:message>
</form:label>
<form:input path="middleName"/>
<br>

<form:label path="phone">
<spring:message code="refe.phone"></spring:message>
</form:label>
<form:input path="phone"/>
<br>

<form:label path="email">
<spring:message code="refe.email"></spring:message>
</form:label>
<form:input path="email"/>
<form:errors cssClass="error" path="email"></form:errors>
<br>

<form:label path="adress">
<spring:message code="refe.adress"></spring:message>
</form:label>
<form:input path="adress"/>
<br>

<form:label path="photo">
<spring:message code="refe.photo"></spring:message>
</form:label>
<form:input path="photo"/>
<br>

<form:label path="account.username">
<spring:message code="refe.user"></spring:message> 
</form:label>
<form:input path="account.username"/>
<form:errors cssClass="error" path="account.username"></form:errors>
<br>

<form:label path="account.password">
<spring:message code="refe.password"></spring:message>
</form:label>
<form:password path="account.password"/>
<form:errors cssClass="error" path="account.password"></form:errors>
<br>

<input type="submit" name="save"
		value="<spring:message code="refe.save"/>" />
<input type="button" name="cancel" value="<spring:message code="refe.cancel"/>"
onclick="javascript:relativeRedir('/welcome/index.jsp');"/> 
</form:form>

