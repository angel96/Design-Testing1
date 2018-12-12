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
modelAttribute="fixuptaskObject">

<form:hidden path="id" />
<form:hidden path="version" />

<form:label path="tickers">
	<spring:message code="fixuptask.tickers" />
</form:label>
<form:input path="tickers" readonly="true" />
<form:errors cssClass='error' path="tickers" />
<br />

<form:label path="moment">
	<spring:message code="fixuptask.moment" />
</form:label>
<form:input path="moment" readonly="true" />
<form:errors cssClass='error' path="moment" />
<br />

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

<form:label path="maximunPrice">
	<spring:message code="fixuptask.maximunPrice" />
</form:label>
<form:input path="maximunPrice"/>
<form:errors cssClass='error' path="maximunPrice" />
<br />

<form:label path="startDate">
	<spring:message code="fixuptask.startDate" />
</form:label>
<form:input path="startDate"/>
<form:errors cssClass='error' path="startDate" />
<br />

<form:label path="endDate">
	<spring:message code="fixuptask.endDate" />
</form:label>
<form:input path="endDate" />
<form:errors cssClass='error' path="endDate" />
<br />

<form:select path="category">
<option value="0" label="---" />
<form:options items="${categories}" itemValue="title" itemValue="category" />
</form:select>

<form:select path="warranty">
<option value="0" label="---" />
<form:options items="${warranties}" itemValue="title" itemValue="warranty" />
</form:select>

</form:form>

<security:authorize access="hasRole('CUSTOMER')">
<input type="submit" name="save"
			value="<spring:message code="fixuptask.save" />" />
<input type="submit" name="cancel"
	value="<spring:message code ="fixuptask.cancel"/>"
	onclick="javascript: relativeRedir('fixuptask/customer/list.do');" />
</security:authorize>