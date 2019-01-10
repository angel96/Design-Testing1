<%--
 * action-1.jsp
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

<p>
	<spring:message code="fixUpTask.action.2" />
	<jstl:set var="lang" value="${lang}" />
</p>

<form:form action="fixuptask/customer/edit.do"
	modelAttribute="fixuptask">

	<form:hidden path="id" />
	<form:hidden path="version" />
	<form:hidden path="ticker" />
	<form:hidden path="moment" />

	<form:label path="description">
		<spring:message code="fixuptask.description" />
	</form:label>
	<form:input path="description" />
	<form:errors cssClass="error" path="description" />
	<br />

	<form:label path="address">
		<spring:message code="fixuptask.address" />
	</form:label>
	<form:input path="address" />
	<form:errors cssClass="error" path="address" />
	<br />

	<form:label path="maximumPrice">
		<spring:message code="fixuptask.maximunPrice" />
	</form:label>
	<form:input path="maximumPrice" />
	<form:errors cssClass="error" path="maximumPrice" />
	<br />

	<form:label path="start">
		<spring:message code="fixuptask.startDate" />
	</form:label>
	<form:input path="start" />
	<form:errors cssClass="error" path="start" />
	<br />

	<form:label path="end">
		<spring:message code="fixuptask.endDate" />
	</form:label>
	<form:input path="end" />
	<form:errors cssClass='error' path="end" />
	<br />


	<form:select path="category">
		<form:option value="0" label="---" />
		<jstl:if test="${lang eq 'en'}">
			<form:options items="${categories}" itemValue="id" itemLabel="name" />
		</jstl:if>
		<jstl:if test="${lang eq 'es'}">
			<form:options items="${categories}" itemValue="id"
				itemLabel="otherlanguages[0]" />
		</jstl:if>
	</form:select>
	<br />

	<form:select path="warranty">
		<option value="0" label="---" />
		<form:options items="${warranties}" itemValue="id" itemLabel="title" />
	</form:select>

	<security:authorize access="hasRole('HANDY_WORKER')">
		<form:label path="application">
			<spring:message code="fixuptask.application" />
		</form:label>
		<input type="submit"
			value="<spring:message code="fixuptask.application" />"
			onclick="javascript: relativeRedir('application/handyworker/create.do');" />
		<form:errors cssClass='error' path="application" />
	</security:authorize>


	<security:authorize access="hasRole('CUSTOMER')">
		<input type="submit" name="save"
			value="<spring:message code="fixuptask.save" />" />

		<input type="submit" name="delete"
			value="<spring:message code="fixuptask.delete" />" />
	</security:authorize>

	<h2>
		<spring:message code="phase.title" />
	</h2>

	<jstl:if test="${fixuptask.id != 0}">
		<display:table name="phases" id="row" requestURI="${requestURI}"
			pagesize="5" class="displaytag">

			<display:column property="number" titleKey="phase.number" />
			<display:column property="title" titleKey="phase.title" />
			<display:column property="description" titleKey="phase.description" />
			<display:column property="startMoment" titleKey="phase.startMoment" />
			<display:column property="endMoment" titleKey="phase.endMoment" />

		</display:table>
	</jstl:if>
</form:form>
<input type="submit" name="cancel"
	value="<spring:message code ="fixuptask.cancel"/>"
	onclick="javascript: relativeRedir('fixuptask/customer/list.do');" />
