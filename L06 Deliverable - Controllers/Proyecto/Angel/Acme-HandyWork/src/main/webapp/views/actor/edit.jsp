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
<jstl:set var = "actor" value = "${actor}" />
<form:form action="${requestURI}" modelAttribute="actor">
	<security:authorize access="hasRole('CUSTOMER')">
		<form:hidden path="fixUpTask" />
		<form:hidden path="notes" />
		<form:hidden path="complaint" />
		<form:hidden path="score" />
	</security:authorize>
	<security:authorize access="hasRole('REFEREE')">
		<form:hidden path="notes" />
		<form:hidden path="complaints" />
	</security:authorize>
	<security:authorize access="hasRole('HANDY_WORKER')">
		<form:hidden path="application" />
		<jstl:if test="${handyWorker.id != 0}">
			<form:hidden path="finder" />
		</jstl:if>
		<form:hidden path="curriculum" />
		<form:hidden path="tutorials" />
		<form:hidden path="notes" />
		<form:hidden path="score" />
	</security:authorize>
	<security:authorize access="hasRole('SPONSOR')">
		<form:hidden path="sponsorship" />
	</security:authorize>
	<form:hidden path="id" />
	<form:hidden path="version" />
	<form:hidden path="profiles" />
	<form:hidden path="boxes" />
	<form:hidden path="account.id" />
	<form:hidden path="account.authorities" />

	<form:label path="name">
		<spring:message code="actor.name" />
	</form:label>
	<form:input path="name" />
	<form:errors cssClass="error" path="name" />
	<br />

	<form:label path="surname">
		<spring:message code="actor.surname" />
	</form:label>
	<form:input path="surname" />
	<form:errors cssClass="error" path="surname" />
	<br />

	<form:label path="middleName">
		<spring:message code="actor.middlename" />
	</form:label>
	<form:input path="middleName" />
	<form:errors cssClass="error" path="middleName" />
	<br />

	<form:label path="phone">
		<spring:message code="actor.phone" />
	</form:label>
	<form:input path="phone" />
	<form:errors cssClass="error" path="phone" />
	<br />

	<form:label path="email">
		<spring:message code="actor.email" />
	</form:label>
	<form:input path="email" />
	<form:errors cssClass="error" path="email" />
	<br />

	<form:label path="adress">
		<spring:message code="actor.adress" />
	</form:label>
	<form:input path="adress" />
	<form:errors cssClass="error" path="adress" />
	<br />

	<form:label path="photo">
		<spring:message code="actor.photo" />
	</form:label>
	<form:input path="photo" />
	<form:errors cssClass="error" path="photo" />
	<br />
	<security:authorize access="hasRole('HANDY_WORKER')">
		<jstl:choose>
			<jstl:when test="${handyWorker.id == 0}">
				<form:hidden path="make"
					value="${handyWorker.name} ${handyWorker.surname}" />
			</jstl:when>
			<jstl:when test="${handyWorker.id != 0}">
				<form:label path="make">
					<spring:message code="handy.make"></spring:message>
				</form:label>
				<form:input path="make" />
				<form:errors cssClass="error" path="make"></form:errors>
			</jstl:when>
		</jstl:choose>
	</security:authorize>
	<form:label path="account.username">
		<spring:message code="actor.user" />
	</form:label>
	<form:input path="account.username" />
	<form:errors cssClass="error" path="account.username" />
	<br />

	<form:label path="account.password">
		<spring:message code="actor.password" />
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
		value="<spring:message code="actor.save"/>" />
</form:form>
