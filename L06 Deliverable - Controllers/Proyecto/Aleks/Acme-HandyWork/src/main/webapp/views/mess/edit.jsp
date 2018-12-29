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

<form:form modelAttribute="mesage" action="box/mess/send.do">

	<form:hidden path="id" />
	<form:hidden path="version" />
	<form:hidden path="sender" />
	<form:hidden path="box" />

	<form:label path="subject">
		<spring:message code="message.subject" />
	</form:label>
	<form:input path="subject" />
	<br />
	<br />

	<form:label path="moment">
		<spring:message code="message.date" />
	</form:label>
	<form:input path="moment" readonly="true" />
	<br />
	<br />
	<form:label path="tags">
		<spring:message code="message.tags" />
	</form:label>
	<form:input path="tags" />
	<br />
	<br />
	<form:label path="priority">
		<spring:message code="message.priority" />
	</form:label>
	<form:select path="priority">
		<form:option value="NONE" label="--- Select ---" />
		<form:options items="${priorities}" />
	</form:select>
	<br />

	<br>
	<form:label path="receiver">
		<spring:message code="message.recipient" />
	</form:label>
	<br>
	<br>
	<div
		style="width: 500px; height: 100px; overflow-y: scroll; border-style: solid; border-color: initial;">
		<jstl:forEach items="${actors}" var="actor">
			<input type="checkbox" name="receiver" value="${actor.id}" />
			<jstl:out value="${actor.name} ${actor.surname} ( ${actor.email} )" />
			<br>
		</jstl:forEach>
	</div>
	<br>
	<form:label path="body">
		<spring:message code="message.content" />
	</form:label>
	<br>
	<form:textarea path="body" />
	<br>
	<br>
	<jstl:forEach items="${errors}" var="error">
		<jstl:out value="${error}" />
	</jstl:forEach>
	<jstl:out value="${message}"></jstl:out>
	<jstl:out value="${oops}" />
	<input type="submit" name="send"
		value="<spring:message code="message.save" />" />
	<br>
</form:form>