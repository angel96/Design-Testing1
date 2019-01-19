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
<script>
	$(function() {
		$("#datepicker-1").datepicker({
			appendText : "(yy/mm/dd)",
			dateFormat : "yy/MM/dd"
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

<script>
	function change(o) {
		if (o.value == 'accepted') {
			document.getElementById("creditcard").style.display = 'block';
			$("#holdername").val("");
			$("#brandname").val("");
			$("#number").val("");
			$("#cvv").val("");
		} else {
			document.getElementById("creditcard").style.display = 'none';
		}
	}
</script>

<form:form action="${requestURI}" modelAttribute="application">

	<form:hidden path="id" />
	<form:hidden path="version" />
	<form:hidden path="moment" />
	<form:hidden path="momentElapsed" />
	<form:hidden path="creditCard" />
	<form:hidden path="fixUpTask" />

	<spring:message code="application.fixUpTask" />
	<jstl:out value=" : ${application.fixUpTask.description}" /> - <jstl:out
		value=" ${application.fixUpTask.ticker}" />
	<br>

	<security:authorize access="hasRole('HANDY_WORKER')">
		<jstl:if test="${application.id != 0}">
			<form:label path="status">
				<spring:message code="application.status" />
			</form:label>
			<jstl:out value="${application.status}" />
		</jstl:if>
		<form:hidden path="status" />
	</security:authorize>
	<br>
	<security:authorize access="hasRole('CUSTOMER')">
		<form:label path="status">
			<spring:message code="application.status" />
		</form:label> : 
		<jstl:if test="${application.status != 'pending'}">
			<jstl:out value="${application.status}" />
			<form:hidden path="status" />
		</jstl:if>
		
		<jstl:if test="${application.status == 'pending'}">
			<form:select path="status" onchange="change(this);">
				<form:option value="0" label="---" />
				<form:options items="${status}" />
			</form:select>
		</jstl:if>
	</security:authorize>
	<br>
	<form:label path="comments">
		<spring:message code="application.comments" />
	</form:label>
	<form:textarea path="comments" />
	<form:errors cssClass="error" path="comments" />
	<br>
	<div id="creditcard" style="display: none">

		<h4>
			<spring:message code="creditCard.alert" />
		</h4>
		<form:label path="creditCard.holderName">
			<spring:message code="application.creditcard.holdername" />
		</form:label>
		<form:input path="creditCard.holderName" id="holdername" />
		<form:errors cssClass="error" path="creditCard.holderName" />
		<br>
		<form:label path="creditCard.brandName">
			<spring:message code="application.creditcard.brandname" />
		</form:label>
		<form:input path="creditCard.brandName" id="brandname" />
		<form:errors cssClass="error" path="creditCard.brandName" />
		<br>
		<form:label path="creditCard.type">
			<spring:message code="application.creditcard.type" />
		</form:label>
		<form:select path="creditCard.type">

			<form:option value="0" label="----------------------" />
			<jstl:forEach items="${types}" var="var">
				<form:option value="${var}" label="${var}" />
			</jstl:forEach>
		</form:select>
		<form:errors cssClass="error" path="creditCard.type" />
		<form:label path="creditCard.number">
			<spring:message code="application.creditcard.number" />
		</form:label>
		<form:input path="creditCard.number" id="number" />
		<form:errors cssClass="error" path="creditCard.number" />
		<br>
		<form:label path="creditCard.expiration">
			<spring:message code="application.creditcard.expiration" />
		</form:label>
		<form:input path="creditCard.expiration" id="datepicker-1" />
		<form:errors cssClass="error" path="creditCard.expiration" />
		<form:label path="creditCard.codeCVV">
			<spring:message code="application.creditcard.codeCVV" />
		</form:label>
		<form:input path="creditCard.codeCVV" id="cvv" />
		<form:errors cssClass="error" path="creditCard.codeCVV" />
	</div>
	<br />
	<security:authorize access="hasRole('HANDY_WORKER')">
		<form:label path="offeredPrice">
			<spring:message code="application.offeredPrice" />
		</form:label>
		<form:input path="offeredPrice"
			readonly="${application.status == 'rejected' or 'accepted'}" />
		<spring:message code="application.vat" />
		<jstl:out value="${vat}" />
		<form:errors cssClass="error" path="offeredPrice"></form:errors>
		<br>

	</security:authorize>
	<security:authorize access="hasRole('CUSTOMER')">
		<form:label path="offeredPrice">
			<spring:message code="application.offeredPrice" />
		</form:label>
		<form:input path="offeredPrice" readonly="${true}" />
		<spring:message code="application.vat" />
		<jstl:out value="${vat}" />
		<br>
	</security:authorize>

	<input type="submit" name="save"
		value="<spring:message code ="application.save"/>" />
</form:form>
<input type="submit" name="cancel"
	value="<spring:message code ="application.cancel"/>"
	onclick="javascript: relativeRedir('${URI}');" />
