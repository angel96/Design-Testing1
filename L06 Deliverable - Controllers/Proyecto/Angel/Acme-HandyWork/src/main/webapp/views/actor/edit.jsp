<%--
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

<form:form action="administrator/edit.do" modelAttribute="administrator">
	<form:hidden path="id" />
	<form:hidden path="version" />
	<!--<form:hidden path="profiles" /> -->
	<form:hidden path="boxes" />
	<form:hidden path="account.id" />
	<form:hidden path="account.authorities" />

	<form:label path="name">
		<spring:message code="admin.name" />
	</form:label>
	<form:input path="name" />
	<form:errors cssClass="error" path="name" />
	<br />

	<form:label path="surname">
		<spring:message code="admin.surname" />
	</form:label>
	<form:input path="surname" />
	<form:errors cssClass="error" path="surname" />
	<br />

	<form:label path="middleName">
		<spring:message code="admin.middlename" />
	</form:label>
	<form:input path="middleName" />
	<form:errors cssClass="error" path="middleName" />
	<br />

	<form:label path="phone">
		<spring:message code="admin.phone" />
	</form:label>
	<form:input path="phone" />
	<form:errors cssClass="error" path="phone" />
	<br />

	<form:label path="email">
		<spring:message code="admin.email" />
	</form:label>
	<form:input path="email" />
	<form:errors cssClass="error" path="email" />
	<br />

	<form:label path="adress">
		<spring:message code="admin.adress" />
	</form:label>
	<form:input path="adress" />
	<form:errors cssClass="error" path="adress" />
	<br />

	<form:label path="photo">
		<spring:message code="admin.photo" />
	</form:label>
	<form:input path="photo" />
	<form:errors cssClass="error" path="photo" />
	<br />

	<form:label path="account.username">
		<spring:message code="admin.user" />
	</form:label>
	<form:input path="account.username" />
	<form:errors cssClass="error" path="account.username" />
	<br />

	<form:label path="account.password">
		<spring:message code="admin.password" />
	</form:label>
	<form:password path="account.password" />
	<form:errors cssClass="error" path="account.password" />
	<br />
	<br />

	<jstl:forEach items="${errors}" var="error">
		<jstl:out value="${error}" />
	</jstl:forEach>
	<jstl:out value="${oops}" />
	<jstl:out value="${message}" />
	<input type="submit" name="save"
		value="<spring:message code="admin.save"/>" />
</form:form>