<%--
 * action-1.jsp
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
<script type="text/javascript">
	$(function() {
		$("#datepicker-1").datepicker({
			appendText : "(yy/mm/dd)",
			dateFormat : "yy/mm/dd"
		});

		$(function() {
			$("#datepicker-2").datepicker({
				appendText : "(yy/mm/dd)",
				dateFormat : "yy/mm/dd"
			});
		});
	});
</script>
<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>

<p>
	<jstl:set var="lang" value="${lang}" />
</p>

<form:form action="fixuptask/customer/edit.do"
	modelAttribute="fixUpTask">

	<form:hidden path="id" />
	<form:hidden path="version" />
	<form:hidden path="ticker" />
	<form:hidden path="moment" />
	<form:label path="ticker">
		<spring:message code="fixuptask.tickers" />
	</form:label>
	: <jstl:out value="${fixUpTask.ticker}" />
	<br>
	<form:label path="description">
		<spring:message code="fixuptask.description" />
	</form:label>
	<form:input path="description" readonly="${view}" />
	<form:errors cssClass="error" path="description"></form:errors>
	<br />

	<form:label path="address">
		<spring:message code="fixuptask.address" />
	</form:label>
	<form:input path="address" readonly="${view}" />
	<form:errors cssClass="error" path="address"></form:errors>
	<br />

	<form:label path="maximumPrice">
		<spring:message code="fixuptask.maximunPrice" />
	</form:label>
	<form:input path="maximumPrice" readonly="${view}" />
	<form:errors cssClass="error" path="maximumPrice"></form:errors>
	<br />

	<form:label path="start">
		<spring:message code="fixuptask.startDate" />
	</form:label>
	<form:input path="start" type="text" id="datepicker-1"
		readonly="${view}" />
	<br />

	<form:label path="end">
		<spring:message code="fixuptask.endDate" />
	</form:label>
	<form:input path="end" readonly="${view}" id="datepicker-2" />
	<br />


	<form:select path="category" disabled="${view}">
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

	<form:select path="warranty" disabled="${view}">
		<option value="0" label="---" />
		<form:options items="${warranties}" itemValue="id" itemLabel="title" />
	</form:select>

	<security:authorize access="hasRole('HANDY_WORKER')">
		<jstl:if test="${res eq true}">
			<form:label path="application">
				<spring:message code="fixuptask.application" />
			</form:label>
			<a href="application/handyworker/create.do?fixUpId=${fixUpTask.id}"><spring:message
					code="fixuptask.apply" /></a>
		</jstl:if>
	</security:authorize>

	<security:authorize access="hasRole('CUSTOMER')">
		<jstl:if test="${not view}">
			<input type="submit" name="save"
				value="<spring:message code="fixuptask.save" />" />

			<input type="submit" name="delete"
				value="<spring:message code="fixuptask.delete" />" />
		</jstl:if>
	</security:authorize>
	<security:authorize access="hasRole('HANDY_WORKER')">
		<a href="customer/handyworker/show.do?fixup=${fixUpTask.id}"><spring:message
				code="fixuptask.customer" /></a>
	</security:authorize>

</form:form>

<security:authorize access="hasRole('CUSTOMER')">
	<input type="submit" name="cancel"
		value="<spring:message code ="fixuptask.cancel"/>"
		onclick="javascript: relativeRedir('fixuptask/customer/list.do');" />
</security:authorize>

<security:authorize access="hasRole('HANDY_WORKER')">

	<input type="submit" name="cancel"
		value="<spring:message code ="fixuptask.cancel"/>"
		onclick="javascript: relativeRedir('fixuptask/handyworker/list.do');" />
</security:authorize>
