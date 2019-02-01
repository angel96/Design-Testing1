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
<link
	href="https://code.jquery.com/ui/1.10.4/themes/ui-lightness/jquery-ui.css"
	rel="stylesheet">
<script>
	$(function() {
		$("#datepicker-1").datepicker({
			appendText : "(yy/mm/dd)",
			dateFormat : "yy/mm/dd"
		});
		$("#datepicker-2").datepicker({
			appendText : "(yy/mm/dd)",
			dateFormat : "yy/mm/dd"
		});
	});
</script>
<p><spring:message code="education.edit" /></p>

<form:form modelAttribute="educationRecord" action="educationrecord/handyworker/edit.do">

<form:hidden path="id" />
<form:hidden path="version" />

<form:label path="diplomaTitle">
	<spring:message code="education.diplomaTitle" />
</form:label>
<form:input path="diplomaTitle"/>
<form:errors cssClass="error" path="diplomaTitle"></form:errors>
<br>

<form:label path="startStudies">
	<spring:message code="education.startStudies" />
</form:label>
<form:input path="startStudies" type="text" id="datepicker-1"/>
<%--  <form:errors cssClass="error" path="startStudies" />--%>
<br>

<form:label path="endStudies">
	<spring:message code="education.endStudies" />
</form:label>
<form:input path="endStudies" type="text"  id="datepicker-2"/>
<form:errors cssClass="error" path="endStudies" />
<br>

<form:label path="institutionDiploma">
	<spring:message code="education.institutionDiploma" />
</form:label>
<form:input path="institutionDiploma"  />
<form:errors cssClass="error" path="institutionDiploma" />
<br />

<form:label path="attachment">
	<spring:message code="education.attachment" />
</form:label>
<form:input path="attachment"  placeholder="http://..."/>
<form:errors cssClass="error" path="attachment" />
<br />


<form:label path="comments">
<spring:message code="education.comments" />
</form:label>
<form:textarea path="comments"/><br>
<spring:message code="education.comments.place" />
<form:errors cssClass="error" path="comments" />
<br />

<security:authorize access="hasRole('HANDY_WORKER')">
<input type="submit" name="save"
			value="<spring:message code="education.save" />" />
</security:authorize>
</form:form>
		
<input type="submit" name="cancel" value="<spring:message code ="education.cancel"/>"
	onclick="javascript: relativeRedir('curriculum/handyworker/list.do');" />

