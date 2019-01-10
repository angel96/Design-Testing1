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

<form:form name="form" action="${requestURI}"
	modelAttribute="sponsorship">
	<form:hidden path="id" />
	<form:hidden path="version" />
	<form:hidden path="sponsor" />
	<form:hidden path="tutorial" />

	<form:label path="urlBanner">
		<spring:message code="sponsorship.urlBanner" />
	</form:label>
	<form:input path="urlBanner" readonly="${view}" />
	<br />

	<form:label path="linkTPage">
		<spring:message code="sponsorship.linkTPage" />
	</form:label>
	<form:input path="linkTPage" readonly="${view}" />

	<br />

	<security:authorize access="hasRole('SPONSOR')">
		<div>
			<form:label path="creditCard.holderName">
				<spring:message code="application.creditcard.holdername" />
			</form:label>
			<form:input path="creditCard.holderName" />
			<br>
			<form:label path="creditCard.brandName">
				<spring:message code="application.creditcard.brandname" />
			</form:label>
			<form:input path="creditCard.brandName" />
			<br>

			<form:label path="creditCard.number">
				<spring:message code="application.creditcard.number" />
			</form:label>
			<form:input path="creditCard.number" />
			<br>
			<form:label path="creditCard.expiration">
				<spring:message code="application.creditcard.expiration" />
			</form:label>
			<form:input path="creditCard.expiration" />
		</div>
	</security:authorize>

	<br />
	<jstl:forEach items="${errors}" var="error">
		<jstl:out value="${error}" />
	</jstl:forEach>
	<jstl:out value="${oops}" />
	<jstl:out value="${message}" />
	<security:authorize access="hasRole('SPONSOR')">
		<input type="submit" name="save"
			value="<spring:message code ="sponsorship.save"/>" />
		<jstl:if test="${sponsorship.id != 0}">
			<input type="submit" name="delete"
				value="<spring:message code ="sponsorship.delete"/>" />
		</jstl:if>
	</security:authorize>
</form:form>

<input type="button" name="cancel"
	value="<spring:message code="sponsorship.cancel"/>"
	onclick="javascript:relativeRedir('/welcome/index.do');" />

