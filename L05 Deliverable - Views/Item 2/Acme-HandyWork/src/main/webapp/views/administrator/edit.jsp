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

<form:form modelAttribute="administratorObject" action="profile/administrator/edit.do">
<form:hidden path="id"/>
<form:hidden path="version"/>

<form:label path="name">
<spring:message code="admin.name"></spring:message>
</form:label>
<form:input path="name"/>
<form:errors cssClass="error" path="name"></form:errors>
<br>

<form:label path="surname">
<spring:message code="admin.surname"></spring:message>
</form:label>
<form:input path="surname"/>
<form:errors cssClass="error" path="surname"></form:errors>
<br>

<form:label path="middleName">
<spring:message code="admin.middlename"></spring:message>
</form:label>
<form:input path="middlename"/>
<br>

<form:label path="phone">
<spring:message code="admin.phone"></spring:message>
</form:label>
<form:input path="phone"/>
<br>

<form:label path="email">
<spring:message code="admin.email"></spring:message>
</form:label>
<form:input path="text"/>
<form:errors cssClass="error" path="email"></form:errors>
<br>

<form:label path="adress">
<spring:message code="admin.adress"></spring:message>
</form:label>
<form:input path="adress"/>
<br>

<form:label path="photoUrl">
<spring:message code="admin.photo"></spring:message>
</form:label>
<form:input path="photoUrl"/>
<br>

<form:label path="user">
<spring:message code="admin.user"></spring:message>
</form:label>
<form:input path="user"/>
<form:errors cssClass="error" path="user"></form:errors>
<br>

<form:label path="password">
<spring:message code="admin.password"></spring:message>
</form:label>
<form:password path="password"/>
<form:errors cssClass="error" path="password"></form:errors>
<br>
</form:form>

<input type="submit" name="save" value="<spring:message code=admin.save/>"
onclick="javascript:relativeRedir('profile/administrator/edit.do');"/>
<input type="button" name="cancel" value="<spring:message code=admin.cancel/>"
onclick="javascript:relativeRedir('welcome/index.jsp');"/>
