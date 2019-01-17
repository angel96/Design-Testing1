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
<form:form modelAttribute="sponsor" action="sponsor/edit.do" onsubmit="return checkPhone(this);">
	<form:hidden path="id" />
	<form:hidden path="version" />
	<jstl:if test="${sponsor.id != 0}">
		<form:hidden path="sponsorship" />
		<form:hidden path="boxes" />
	</jstl:if>
	<form:hidden path="account.enabled" />
	<form:hidden path="account.id" />
	<form:hidden path="account.authorities" />


	<form:label path="name">
		<spring:message code="spons.name"></spring:message>
	</form:label>
	<form:input path="name" />
	<form:errors cssClass="error" path="name"></form:errors>
	<br>

	<form:label path="surname">
		<spring:message code="spons.surname"></spring:message>
	</form:label>
	<form:input path="surname" />
	<form:errors cssClass="error" path="surname"></form:errors>
	<br>

	<form:label path="middleName">
		<spring:message code="spons.middleName"></spring:message>
	</form:label>
	<form:input path="middleName" />
	<form:errors cssClass="error" path="middleName" />
	<br>

	<form:label path="phone">
		<spring:message code="spons.phone"></spring:message>
	</form:label>
	<form:input path="phone" id = "phone"/>
	<form:errors cssClass="error" path="phone" />
	<br>

	<form:label path="email">
		<spring:message code="spons.email"></spring:message>
	</form:label>
	<form:input path="email" />
	<form:errors cssClass="error" path="email"></form:errors>
	<br>

	<form:label path="adress">
		<spring:message code="spons.adress"></spring:message>
	</form:label>
	<form:input path="adress" />
	<form:errors cssClass="error" path="adress" />
	<br>

	<form:label path="photo">
		<spring:message code="spons.photo"></spring:message>
	</form:label>
	<form:input path="photo" />
	<form:errors cssClass="error" path="photo" />
	<br>

	<form:label path="account.username">
		<spring:message code="spons.user"></spring:message>
	</form:label>
	<form:input path="account.username" />
	<form:errors cssClass="error" path="account.username"></form:errors>
	<br>

	<form:label path="account.password">
		<spring:message code="spons.password"></spring:message>
	</form:label>
	<form:password path="account.password" />
	<form:errors cssClass="error" path="account.password"></form:errors>
	<br>

	<jstl:forEach items="${errors}" var="error">
		<jstl:out value="${error}" />
	</jstl:forEach>
	<jstl:out value="${oops}" />
	<jstl:out value="${message}" />

	<input type="submit" name="save"
		value="<spring:message code="spons.save"/>" />

</form:form>

<input type="button" name="cancel"
	value="<spring:message code="spons.cancel"/>"
	onclick="javascript:relativeRedir('/welcome/index.do');" />
