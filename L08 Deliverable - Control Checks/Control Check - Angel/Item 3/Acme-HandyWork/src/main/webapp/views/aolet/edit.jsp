<%--
 * action-2.jsp
 *
 * Copyright (C) 2018 Universidad de Sevilla
 * 
 * The use of this project is hereby constrained to the conditions of the 
 * TDG Licence, a copy of which you may download from 
 * http://www.tdg-seville.info/License.html
 --%>
<link
	href="https://code.jquery.com/ui/1.10.4/themes/ui-lightness/jquery-ui.css"
	rel="stylesheet">

<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>

<form:form action="${requestURI}" modelAttribute="aolet">

	<form:hidden path="id" />
	<form:hidden path="version" />

	<form:label path="ticker">
		<spring:message code="aolet.ticker" />
	</form:label>
	<form:input path="ticker" readonly="true" />
	<form:errors cssClass="error" path="ticker" />
	<br>
	<form:label path="title">
		<spring:message code="aolet.title" />
	</form:label>
	<form:input path="title" readonly="${aolet.finalMode}"/>
	<form:errors cssClass="error" path="title" />
	<br>
	<form:label path="body">
		<spring:message code="aolet.body" />
	</form:label>
	<form:textarea path="body" readonly="${aolet.finalMode}"/>
	<form:errors cssClass="error" path="body" />
	<br>
	<form:label path="moment">
		<spring:message code="aolet.moment" />
	</form:label>
	<form:input path="moment" readonly="true" />
	<form:errors cssClass="error" path="moment" />
	<br>
	<form:label path="optionalPicture">
		<spring:message code="aolet.picture" />
	</form:label>
	<form:input path="optionalPicture" readonly="${aolet.finalMode}"/>
	<form:errors cssClass="error" path="optionalPicture" />
	<br>
	<security:authorize access="hasRole('CUSTOMER')">
		<jstl:if test="${aolet.finalMode eq 'false'}">
			<form:label path="finalMode">
				<spring:message code="aolet.finalMode" />
			</form:label>
			<form:checkbox path="finalMode" />
			<br>
			<jstl:if test="${aolet.id == 0}">
				<input type="submit" name="save"
					value="<spring:message code="aolet.save" />" />
			</jstl:if>
			<jstl:if test="${aolet.id != 0 and mine == 'true'}">
				<input type="submit" name="save"
					value="<spring:message code="aolet.save" />" />
				<input type="submit" name="delete"
					value="<spring:message code="aolet.delete" />" />
			</jstl:if>
		</jstl:if>
	</security:authorize>

	<jstl:out value="${oops}" />
	<jstl:out value="${message}" />
	<jstl:forEach items="${errors}" var="error">
		<jstl:out value="${error}" />
	</jstl:forEach>

</form:form>

<input type="submit" name="cancel"
	value="<spring:message code ="aolet.cancel"/>"
	onclick="javascript: relativeRedir('${URI}');" />
