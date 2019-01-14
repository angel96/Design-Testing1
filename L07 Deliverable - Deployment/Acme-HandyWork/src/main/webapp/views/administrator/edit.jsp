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
<form:form action="administrator/edit.do" modelAttribute="administrator"
	onsubmit="return checkPhone(this);">
	<form:hidden path="id" />
	<form:hidden path="version" />
	<jstl:if test="${administrator.id != 0}">
		<form:hidden path="boxes" />
	</jstl:if>
	<form:hidden path="account.enabled" />
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
	<form:input path="phone" id="phone" />
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

	<input type="submit" name="save"
		value="<spring:message code="admin.save"/>" />
</form:form>
