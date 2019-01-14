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
	function checkAll(o) {
		var all = document.querySelectorAll('input[type="checkbox"]');

		for ( var i = 0; i < all.length; i++) {

			if (all[i] != o) {
				all[i].checked = o.checked;
			}

		}
	}
</script>

<form:form modelAttribute="mesage" action="${requestURI}">

	<form:hidden path="id" />
	<form:hidden path="version" />
	<form:label path="sender">
		<spring:message code="message.sender" />
	</form:label>
	: ${mesage.sender.name} ${mesage.sender.surname} (${mesage.sender.email})
	<form:hidden path="sender" />
	<form:hidden path="box" />
	<br>
	<br>
	<form:label path="subject">
		<spring:message code="message.subject" />
	</form:label>
	<form:input path="subject" readonly="${view}" />
	<form:errors cssClass="error" path="subject"></form:errors>
	<br />
	<br />

	<form:label path="moment">
		<spring:message code="message.date" />
	</form:label>
	<form:input path="moment" readonly="true" />
	<form:errors cssClass="error" path="moment"></form:errors>
	<br />
	<br />
	<form:label path="tags">
		<spring:message code="message.tags" />
	</form:label>
	<form:input path="tags" readonly="${view}" />
	<form:errors cssClass="error" path="tags"></form:errors>
	<br />
	<br />
	<form:label path="priority">
		<spring:message code="message.priority" />
	</form:label>

	<jstl:choose>
		<jstl:when test="${view}">
			<form:hidden path="priority" />
			<jstl:out value=" --> ${mesage.priority}" />

		</jstl:when>
		<jstl:when test="${not view}">

			<form:select path="priority">
				<form:option value="NONE" label="--- Select ---" />
				<form:options items="${priorities}" />
			</form:select>

		</jstl:when>
	</jstl:choose>

	<br />

	<br>
	<form:label path="receiver">
		<spring:message code="message.recipient" />
	</form:label>
	<br>
	<br>
	<security:authorize access="hasRole('ADMIN')">
		<jstl:if test="${not view}">
			<input type="checkbox" name="broadcast" onclick="checkAll(this);" />
			<spring:message code="message.broadcast" />
		</jstl:if>
	</security:authorize>
	<div
		style="width: 500px; height: 100px; overflow-y: scroll; border-style: solid; border-color: initial;">
		<jstl:if test="${view}">
			<form:hidden path="receiver" />
			<jstl:forEach items="${mesage.receiver}" var="actor">
				<input type="checkbox" name="receiver" value="${actor.id}"
					disabled="${view}" />
				<jstl:out value="${actor.name} ${actor.surname} ( ${actor.email} )" />
				<br>
			</jstl:forEach>
		</jstl:if>

		<jstl:if test="${not view}">
			<jstl:forEach items="${actors}" var="actor">
				<input type="checkbox" name="receiver" value="${actor.id}"
					readonly="${view}" />
				<jstl:out value="${actor.name} ${actor.surname} ( ${actor.email} )" />
				<br>
			</jstl:forEach>
		</jstl:if>
	</div>
	<br>
	<form:label path="body">
		<spring:message code="message.content" />
	</form:label>
	<br>
	<form:textarea path="body" readonly="${view}" />
	<form:errors cssClass="error" path="body"></form:errors>
	<br>

	<jstl:if test="${not view}">
		<input type="submit" name="send"
			value="<spring:message code="message.save" />" />
	</jstl:if>
	<jstl:if test="${view}">
		<input type="submit" name="move"
			value="<spring:message code="message.trash" />" />
	</jstl:if>
	<br>
	<jstl:forEach items="${errors}" var="error">
		<jstl:out value="${error}" />
	</jstl:forEach>
	<jstl:out value="${message}"></jstl:out>
	<jstl:out value="${oops}" />

	<jstl:if test="${view}">
		<br>
		<spring:message code="message.boxes" />
		<div
			style="width: 500px; height: 100px; overflow-y: scroll; border-style: solid; border-color: initial;">
			<br>
			<jstl:forEach items="${boxesOptional}" var="var">
				<spring:message code="message.boxes" />: 
				<a href="box/mess/dbox.do?boxId=${var.id}&mess=${mesage.id}">
					${var.name} </a>
				<br>
			</jstl:forEach>
			<br>
		</div>

	</jstl:if>
</form:form>