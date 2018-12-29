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

<p><spring:message code="fixUpTask.action.2" /></p>

<form:form action="fixuptask/customer/edit.do"
modelAttribute="fixuptask">

<form:hidden path="id" />
<form:hidden path="version" />
<form:hidden path="ticker"/>
<form:hidden path="moment"/>

<form:label path="description">
	<spring:message code="fixuptask.description" />
</form:label>
<form:input path="description" />
<form:errors cssClass='error' path="description" />
<br />

<form:label path="address">
	<spring:message code="fixuptask.address" />
</form:label>
<form:input path="address"  />
<form:errors cssClass='error' path="address" />
<br />

<form:label path="maximumPrice">
	<spring:message code="fixuptask.maximumPrice" />
</form:label>
<form:input path="maximumPrice"/>
<form:errors cssClass='error' path="maximumPrice" />
<br />

<form:label path="start">
	<spring:message code="fixuptask.startDate" />
</form:label>
<form:input path="start"/>
<form:errors cssClass='error' path="start" />
<br />

<form:label path="end">
	<spring:message code="fixuptask.endDate" />
</form:label>
<form:input path="end" />
<form:errors cssClass='error' path="end" />
<br />
<form:hidden path="category"/>
<form:hidden path="warranty"/>
<!--
<form:select path="category">
<form:options items="${categories}" itemLabel="category.name" itemValue="category.id" />
<form:option value="0" label="---" />
</form:select>
  
<form:select path="warranty">
<option value="0" label="---" />
<form:options items="${warranties}" itemValue="warranty" />
</form:select>
-->
<security:authorize access="hasRole('HANDY_WORKER')">
<form:label path="customer">
	<spring:message code="fixuptask.customer" />
</form:label>
<a href="profile/handyworker/edit.do?customerId=${row.id}">
<jstl:out value="${fixpask.customer.name}"></jstl:out> 
</a>
<form:errors cssClass='error' path="customer" />
</security:authorize>

<security:authorize access="hasRole('HANDY_WORKER')">
<form:label path="application">
</form:label>
<input type="submit" name="addApplication"
			value="<spring:message code="fixuptask.application" />" 
			onclick="javascript: relativeRedir('handyworker/application/edit.do');" />
<form:errors cssClass='error' path="application" />
</security:authorize>
</form:form>

<security:authorize access="hasRole('CUSTOMER')">
<input type="submit" name="save"
			value="<spring:message code="fixuptask.save" />" 
			onclick="javascript: relativeRedir('fixuptask/customer/edit.do');" />
			
<input type="submit" name="delete"
			value="<spring:message code="fixuptask.delete" />" />

<input type="submit" name="cancel"
	value="<spring:message code ="fixuptask.cancel"/>"
	onclick="javascript: relativeRedir('fixuptask/customer/list.do');" />
</security:authorize>