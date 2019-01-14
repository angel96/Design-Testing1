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

<form:form action="customisation/administrator/edit.do"
	modelAttribute="customisationsystem">
	<form:hidden path="id" />
	<form:hidden path="version" />

	<form:label path="systemName">
		<spring:message code="custom.system" />
	</form:label>
	<form:input path="systemName" />
	<form:errors cssClass="error" path="systemName"></form:errors>
	<br>
	<form:label path="banner">
		<spring:message code="custom.banner" />
	</form:label>
	<form:input path="banner" />
	<form:errors cssClass="error" path="banner"></form:errors>
	<br>
	<form:label path="message">
		<spring:message code="custom.message" />
	</form:label>
	<form:textarea path="message" />
	<form:errors cssClass="error" path="message"></form:errors>
	<br>
	<form:label path="hoursFinder">
		<spring:message code="custom.hoursFinder" />
	</form:label>
	<form:input path="hoursFinder" />
	<form:errors cssClass="error" path="hoursFinder"></form:errors>
	<br>
	<form:label path="resultFinder">
		<spring:message code="custom.resultFinder" />
	</form:label>
	<form:input path="resultFinder" />
	<form:errors cssClass="error" path="resultFinder"></form:errors>
	<br>
	<form:label path="goodWords">
		<spring:message code="custom.goodWords" />
	</form:label>
	<form:textarea path="goodWords" />
	<form:errors cssClass="error" path="goodWords"></form:errors>
	<br>
	<form:label path="badWords">
		<spring:message code="custom.badWords" />
	</form:label>
	<form:textarea path="badWords" />
	<form:errors cssClass="error" path="badWords"></form:errors>
	<br>
	<form:label path="spamWords">
		<spring:message code="custom.spamWords" />
	</form:label>
	<form:textarea path="spamWords" />
	<form:errors cssClass="error" path="spamWords"></form:errors>
	<br>
	<input type="submit" name="save"
		value="<spring:message code="custom.save"/>" />
</form:form>
<input type="button" name="cancel"
  value="<spring:message code="cust.cancel"/>"
  onclick="javascript:relativeRedir('/welcome/index.do');" />