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

<p><spring:message code="miscellaneous.edit" /></p>

<form:form modelAttribute="miscellaneousrecord" action="miscellaneousrecord/handyworker/edit.do">

<form:hidden path="id" />
<form:hidden path="version" />

<form:label path="title">
	<spring:message code="miscellaneous.title" />
</form:label>
<form:input path="title"/>
<form:errors cssClass="error" path="title"></form:errors>
<br>

<form:label path="attachment">
	<spring:message code="miscellaneous.attachment" />
</form:label>
<form:input path="attachment"  />
<form:errors cssClass="error" path="attachment" />
<br />

<form:label path="comments">
<spring:message code="miscellaneous.comments" />
</form:label>º
<form:textarea path="comments"/><br>
<form:errors cssClass="error" path="comments" />
<spring:message code="miscellaneous.comments.place" />
<br />

<security:authorize access="hasRole('HANDY_WORKER')">
<input type="submit" name="save"
			value="<spring:message code="miscellaneous.save" />" />
</security:authorize>
</form:form>
<input type="submit" name="cancel" value="<spring:message code ="miscellaneous.cancel"/>"
	onclick="javascript: relativeRedir('curriculum/handyworker/list.do');" />


