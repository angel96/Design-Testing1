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

<script type="text/javascript">
	$(document).ready(function() {
		$("#form").submit(function(event) {
			var make = $("#make").val();
			if (make == "" || make == " " || make == null) {
				var name = $("#name").val();
				var surname = $("#surname").val();
				var make = name.concat(" ", surname);
				$("#make").val(make);
			}
		});
	});
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

<form:form id="form" action="handyworker/edit.do"
	modelAttribute="handyWorker" onsubmit="return checkPhone(this);">

	<form:hidden path="id" />
	<form:hidden path="version" />

	<jstl:if test="${handyWorker.id != 0 }">
		<form:hidden path="notes" />
		<form:hidden path="curriculum" />
		<form:hidden path="application" />
		<form:hidden path="tutorials" />
		<form:hidden path="finder" />
		<form:hidden path="boxes" />
		<form:label path="score">
			<spring:message code="handy.score"></spring:message>
		</form:label>
		: <jstl:out value="${handyWorker.score}" />
		</br>
	</jstl:if>
	<form:hidden path="account.enabled" />
	<form:hidden path="account.id" />
	<form:hidden path="account.authorities" />
	<%-- <form:hidden path="score" /> --%>


	<form:label path="name">
		<spring:message code="handy.name"></spring:message>

	</form:label>
	<form:input path="name" readonly="${view}" id="name" />
	<form:errors cssClass="error" path="name" />
	<br />

	<form:label path="surname">
		<spring:message code="handy.surname"></spring:message>
	</form:label>
	<form:input path="surname" readonly="${view}" id="surname"/>
	<form:errors cssClass="error" path="surname" ></form:errors>
	<br>

	<form:label path="middleName">
		<spring:message code="handy.middlename"></spring:message>
	</form:label>
	<form:input path="middleName" readonly="${view}" />
	<form:errors cssClass="error" path="middleName"></form:errors>
	<br>

	<form:label path="phone">
		<spring:message code="handy.phone"></spring:message>
	</form:label>
	<form:input path="phone" placeholder="XXXXXXXXX" readonly="${view}"
		id="phone" />
	<form:errors cssClass="error" path="phone"></form:errors>
	<br>

	<form:label path="email">
		<spring:message code="handy.email"></spring:message>
	</form:label>
	<form:input path="email" placeholder="nombre@email.com"
		readonly="${view}" />
	<form:errors cssClass="error" path="email"></form:errors>
	<br>

	<form:label path="adress">
		<spring:message code="handy.adress"></spring:message>
	</form:label>
	<form:input path="adress" readonly="${view}" />
	<form:errors cssClass="error" path="adress"></form:errors>
	<br>

	<form:label path="photo">
		<spring:message code="handy.photo"></spring:message>
	</form:label>
	<form:input path="photo" placeholder="http://....." readonly="${view}" />
	<form:errors cssClass="error" path="photo"></form:errors>
	<br>

	<jstl:choose>
		<jstl:when test="${handyWorker.id == 0}">
			<form:hidden path="make" id="make" />
		</jstl:when>
		<jstl:when test="${handyWorker.id != 0}">
			<form:label path="make">
				<spring:message code="handy.make"></spring:message>
			</form:label>
			<form:input path="make" readonly="${view}" />
			<form:errors cssClass="error" path="make"></form:errors>
		</jstl:when>
	</jstl:choose>

	<jstl:if test="${view eq false}">
		<form:label path="account.username">
			<spring:message code="handy.user"></spring:message>
		</form:label>
		<form:input path="account.username" />
		<form:errors cssClass="error" path="account.username"></form:errors>
		<br>

		<form:label path="account.password">
			<spring:message code="handy.password"></spring:message>
		</form:label>
		<form:password path="account.password" />
		<form:errors cssClass="error" path="account.password"></form:errors>
		<br>
	</jstl:if>

	<jstl:if test="${view eq false}">
		<input type="submit" name="save"
			value="<spring:message code="handy.save"/>" />
	</jstl:if>

	<jstl:if test="${view eq true}">
		<h1>
			<spring:message code="handy.tutorials" />
		</h1>
		<display:table name="handyWorker.tutorials" id="row" pagesize="5"
			class="displaytag">
			<display:column property="title" titleKey="tutorial.title" />
			<display:column property="lastUpdate" titleKey="tutorial.lastUpdate" />
			<display:column property="summary" titleKey="tutorial.summary" />
			<display:column titleKey="tutorial.viewmore">
				<a href="tutorial/show.do?id=${row.id}"> <spring:message
						code="tutorial.viewmore" />
				</a>
			</display:column>
		</display:table>
	</jstl:if>
</form:form>

<input type="button" name="cancel"
	value="<spring:message code="handy.cancel"/>"
	onclick="javascript:relativeRedir('/welcome/index.do');" />
