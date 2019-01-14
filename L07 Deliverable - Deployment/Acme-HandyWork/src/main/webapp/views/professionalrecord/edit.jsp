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
<p><spring:message code="professional.edit" /></p>

<form:form modelAttribute="professionalrecord" action="professionalrecord/handyworker/edit.do">

<form:hidden path="id" />
<form:hidden path="version" />

<form:label path="companyName">
	<spring:message code="professional.companyName" />
</form:label>
<form:input path="companyName"/>
<form:errors cssClass="error" path="companyName"></form:errors>
<br>

<form:label path="startWorking">
	<spring:message code="professional.startWorking" />
</form:label>
<form:input path="startWorking" placeholder="yyyy/mm/dd" id = "datepicker-1"/>
<form:errors cssClass="error" path="startWorking" />
<br>

<form:label path="endWorking">
	<spring:message code="professional.endWorking" />
</form:label>
<form:input path="endWorking" placeholder="yyyy/mm/dd" id = "datepicker-2"/>
<form:errors cssClass="error" path="endWorking" />
<br>

<form:label path="role">
	<spring:message code="professional.role" />
</form:label>
<form:input path="role"  />
<form:errors cssClass="error" path="role" />
<br />

<form:label path="attachment">
	<spring:message code="professional.attachment" />
</form:label>
<form:input path="attachment"  />
<form:errors cssClass="error" path="attachment" />
<br />

<form:label path="comments">
<spring:message code="professional.comments" />
</form:label>
<form:textarea path="comments"/><br>
<form:errors cssClass="error" path="comments" />
<spring:message code="professional.comments.place" />
<br />

<jstl:forEach items="${errors}" var="error">
		<jstl:out value="${error}" />
	</jstl:forEach>
	<jstl:out value="${oops}" />
	<jstl:out value="${message}" />

<security:authorize access="hasRole('HANDY_WORKER')">
<input type="submit" name="save"
			value="<spring:message code="professional.save" />" />
</security:authorize>
</form:form>
		
<input type="submit" name="cancel" value="<spring:message code ="professional.cancel"/>"
	onclick="javascript: relativeRedir('curriculum/handyworker/list.do');" />

