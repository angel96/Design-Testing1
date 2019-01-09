<%--
 * action-1.jsp
 *
 * Copyright (C) 2018 Universidad de Sevilla
 * 
 * The use of this project is hereby constrained to the conditions of the 
 * TDG Licence, a copy of which you may download from 
 * http://www.tdg-seville.info/License.html
 --%>

<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl"	uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>

<p><spring:message code="endorsement.edit" /></p>

<form:form modelAttribute="endorsement" action="${requestURI}?idActor=${idActor}">

<form:hidden path="id" />
<form:hidden path="version" />

<form:label path="moment">
	<spring:message code="endorsement.moment" />
</form:label>
<form:input path="moment" readonly="true"/>
<form:errors cssClass="error" path="moment"></form:errors>
<br>

<form:label path="userSended.name">
	<spring:message code="endorsement.userSended" />
</form:label>
<form:input path="userSended.name"  readonly="true"/>
<form:errors cssClass="error" path="userSended.name" />
<br />

<form:label path="userReceived.name">
	<spring:message code="endorsement.userReceived" />
</form:label>
<form:input path="userReceived.name"  readonly="true"/>
<form:errors cssClass="error" path="userReceived.name" />
<br />

<form:label path="comments">
<spring:message code="endorsement.comments" />
</form:label>
<form:textarea path="comments"/><br>
<spring:message code="endorsement.comments.place" />
<form:errors cssClass='error' path="comments" />
<br />


<input type="submit" name="save"
			value="<spring:message code="endorsement.save" />" />

</form:form>
<input type="submit" name="cancel" value="<spring:message code ="endorsement.cancel"/>"
	onclick="javascript: relativeRedir('endorsement/${role}/list.do');" />


