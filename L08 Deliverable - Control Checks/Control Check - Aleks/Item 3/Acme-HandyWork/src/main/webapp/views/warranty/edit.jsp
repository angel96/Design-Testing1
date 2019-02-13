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

<form:form action="warranty/administrator/edit.do"
	modelAttribute="warranty">

	<form:hidden path="id" />
	<form:hidden path="version" />

	<form:label path="title">
		<spring:message code="warranty.title" />
	</form:label>
	<form:input path="title" readonly="${not warranty.draftMode}" />
	<form:errors cssClass="error" path="title"></form:errors>
	<br />
	<form:label path="terms">
		<spring:message code="warranty.terms" />
	</form:label>
	<form:textarea path="terms" readonly="${not warranty.draftMode}" />
	<form:errors cssClass="error" path="terms"></form:errors>
	<br />
	<form:label path="laws">
		<spring:message code="warranty.laws" />
	</form:label>
	<form:textarea path="laws" readonly="${not warranty.draftMode}" />
	<form:errors cssClass="error" path="laws"></form:errors>
	<br />

	<security:authorize access="hasRole('ADMIN')">
		<jstl:if test="${warranty.draftMode}">
			<form:label path="draftMode">
				<spring:message code="warranty.isDraft" />
			</form:label>

			<form:checkbox path="draftMode" />
		</jstl:if>
	</security:authorize>

	<br />
	<jstl:if test="${warranty.draftMode}">
		<input type="submit" name="save"
			value="<spring:message code="warranty.save" />" />
		<jstl:if test="${warranty.id != 0}">
			<input type="submit" name="delete"
				value="<spring:message code="warranty.delete" />" />
		</jstl:if>
	</jstl:if>


</form:form>

<input type="submit" name="cancel"
	value="<spring:message code ="warranty.cancel"/>"
	onclick="javascript: relativeRedir('warranty/administrator/list.do');" />
