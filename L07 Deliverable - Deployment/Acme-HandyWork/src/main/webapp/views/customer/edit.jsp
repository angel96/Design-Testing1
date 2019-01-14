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
<script>
	function checkPhone(form) {
		var phone = document.getElementById("phone").value;
		var e = new RegExp(
				"/^(\\+([1-9]|[1-9][0-9]|[1-9][0-9][0-9])\\s\\(([1-9]|[1-9][0-9]|[1-9][0-9][0-9])\\)\\s\\d{4,9})|(\\+([1-9]|[1-9][0-9]|[1-9][0-9][0-9])\\s\\d{4,9})|(\\d{4,9})$/");
		if (e.test(phone) == false) {
			if (confirm("<spring:message code = 'phone.confirm1' />")) {
				res = confirm("<spring:message code = 'phone.confirm2' />");
			} else {
				return false;
			}
		}

	}
</script>
<form:form modelAttribute="customer" action="customer/edit.do" onsubmit="return checkPhone(this);">
	<form:hidden path="id" />
	<form:hidden path="version" />
	<jstl:if test="${customer.id != 0}">
		<form:hidden path="boxes" />
		<form:hidden path="fixUpTask" />
		<form:hidden path="complaint" />
		<form:hidden path="notes" />
		<form:label path="score">
			<spring:message code="cust.score"></spring:message>
		</form:label>
		: <jstl:out value = "${customer.score}" />
		<br>
	</jstl:if>
	<form:hidden path="account.id" />
	<form:hidden path="account.enabled" />
	<form:hidden path="account.authorities" />

	<form:label path="name">
		<spring:message code="cust.name"></spring:message>
	</form:label>
	<form:input path="name" readonly="${view}" />
	<form:errors cssClass="error" path="name"></form:errors>
	<br>

	<form:label path="surname">
		<spring:message code="cust.surname"></spring:message>
	</form:label>
	<form:input path="surname" readonly="${view}" />
	<form:errors cssClass="error" path="surname"></form:errors>
	<br>

	<form:label path="middleName">
		<spring:message code="cust.middleName"></spring:message>
	</form:label>
	<form:input path="middleName" readonly="${view}" />
	<form:errors cssClass="error" path="middleName"></form:errors>
	<br>

	<form:label path="phone">
		<spring:message code="cust.phone"></spring:message>
	</form:label>
	<form:input path="phone" readonly="${view}" id = "phone"/>
	<form:errors cssClass="error" path="phone"></form:errors>
	<br>

	<form:label path="email">
		<spring:message code="cust.email"></spring:message>
	</form:label>
	<form:input path="email" readonly="${view}" />
	<form:errors cssClass="error" path="email"></form:errors>
	<br>

	<form:label path="adress">
		<spring:message code="cust.adress"></spring:message>
	</form:label>
	<form:input path="adress" readonly="${view}" />
	<form:errors cssClass="error" path="adress"></form:errors>
	<br>

	<form:label path="photo">
		<spring:message code="cust.photo"></spring:message>
	</form:label>
	<form:input path="photo" readonly="${view}" />
	<form:errors cssClass="error" path="photo"></form:errors>
	<br>
	<jstl:if test="${not view}">
		<form:label path="account.username">
			<spring:message code="cust.user"></spring:message>
		</form:label>
		<form:input path="account.username" />
		<form:errors cssClass="error" path="account.username"></form:errors>
		<br>

		<form:label path="account.password">
			<spring:message code="cust.password"></spring:message>
		</form:label>
		<form:password path="account.password" />
		<form:errors cssClass="error" path="account.password"></form:errors>
		<br>
		<input type="submit" name="save"
			value="<spring:message code="cust.save"/>" />
	</jstl:if>
</form:form>
<input type="button" name="cancel"
	value="<spring:message code="cust.cancel"/>"
	onclick="javascript:relativeRedir('/welcome/index.do');" />

