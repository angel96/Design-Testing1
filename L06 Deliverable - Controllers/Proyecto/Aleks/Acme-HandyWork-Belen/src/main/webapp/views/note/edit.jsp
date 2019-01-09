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


<form:form action="note/customer/edit.do"
modelAttribute="note">

	<form:hidden path="id" />
	<form:hidden path="version" />
	<form:hidden path="moment"/>

	<form:label path="comment">
		<spring:message code="note.comment" />
	</form:label>
	<form:textarea path="comment"/>
	<br>
	<form:label path="otherComments">
		<spring:message code="note.otherComments" />
	</form:label>
	<form:textarea path="comment"/>
	<br>

<input type="submit" name="save"
			value="<spring:message code="note.save" />" />
</form:form>	
<input type="submit" name="cancel"
	value="<spring:message code ="note.cancel"/>"
	onclick="javascript: relativeRedir('note/customer/list.do');" />
