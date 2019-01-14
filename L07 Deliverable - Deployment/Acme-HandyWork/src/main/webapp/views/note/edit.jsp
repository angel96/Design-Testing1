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

<script type="text/javascript">
	function checkConfirm(form) {
		if (confirm("<spring:message code = 'note.confirm1' />")) {
			res = confirm("<spring:message code = 'note.confirm2' />");
		} else {
			return false;
		}
	}
</script>

<form:form action="${requestURI}?idRep=${idRep}" modelAttribute="note"
	onsubmit="return checkConfirm(this);">

	<form:hidden path="id" />
	<form:hidden path="version" />
	<security:authorize access="hasRole('CUSTOMER')">
		<form:hidden path="refereeComment" />
		<form:hidden path="handyWorkerComment" />
	</security:authorize>
	<security:authorize access="hasRole('HANDY_WORKER')">
		<form:hidden path="refereeComment" />
		<form:hidden path="customerComment" />
	</security:authorize>
	<security:authorize access="hasRole('REFEREE')">
		<form:hidden path="handyWorkerComment" />
		<form:hidden path="customerComment" />
	</security:authorize>

	<form:label path="moment">
		<spring:message code="note.moment" />
	</form:label>
	<form:input path="moment" readonly="true" />
	<br>
	<security:authorize access="hasRole('CUSTOMER')">
		<form:label path="customerComment">
			<spring:message code="note.customerComment" />
		</form:label>
		<form:textarea path="customerComment" />
		<form:errors cssClass="error" path="customerComment" />
		<br>
	</security:authorize>
	<security:authorize access="hasRole('REFEREE')">
		<form:label path="refereeComment">
			<spring:message code="note.refereeComment" />
		</form:label>
		<form:textarea path="refereeComment" />
		<form:errors cssClass="error" path="refereeComment" />
		<br>
	</security:authorize>
	<security:authorize access="hasRole('HANDY_WORKER')">
		<form:label path="handyWorkerComment">
			<spring:message code="note.handyWorkerComment" />
		</form:label>
		<form:textarea path="handyWorkerComment" />
		<form:errors cssClass="error" path="hndyWorkerComment" />
		<br>
	</security:authorize>

	<jstl:forEach items="${errors}" var="error">
		<jstl:out value="${error}" />
	</jstl:forEach>
	<jstl:out value="${oops}" />
	<jstl:out value="${message}" />

	<input type="submit" name="save"
		value="<spring:message code="note.save" />" />
</form:form>
<security:authorize access="hasRole('CUSTOMER')">
	<input type="submit" name="cancel"
		value="<spring:message code ="note.cancel"/>"
		onclick="javascript: relativeRedir('report/customer/list.do');" />
</security:authorize>
<security:authorize access="hasRole('HANDY_WORKER')">
	<input type="submit" name="cancel"
		value="<spring:message code ="note.cancel"/>"
		onclick="javascript: relativeRedir('report/handyworker/list.do');" />
</security:authorize>
<security:authorize access="hasRole('REFEREE')">
	<input type="submit" name="cancel"
		value="<spring:message code ="note.cancel"/>"
		onclick="javascript: relativeRedir('report/referee/list.do');" />
</security:authorize>