<%--
 * action-2.jsp
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

<form:form action="handyworker/tutorial/edit.do"
	modelAttribute="tutorialObject">
	
	<form:hidden path="id"/>
	<form:hidden path="version"/>

	<form:label path="title">
		<spring:message code="tutorial.title"/>
	</form:label>
	<form:input path="title"/>
	<br/>
	<form:label path="lastUpdate">
		<spring:message code="tutorial.lastUpdate" />
	</form:label>
	<form:input path="lastUpdate" value="${tutorialObject.lastUpdate}" readonly="${true}"/>
	<br/>
	<form:label path="summary">
		<spring:message code="tutorial.summary"/>
	</form:label>
	<form:textarea path="summary"/>
	<br/>
	<form:label path="picture">
		<spring:message code="tutorial.picture"/>
	</form:label>
	<input type="submit" name="picture"
	value="<spring:message code ="tutorial.addPicture"/>" />
	<br/>
	
	<security:authorize access="hasRole('HANDY_WORKER')">
	<form:label path="section">
		<spring:message code="tutorial.section"/>
	</form:label>
	<display:table name="section" id="row" requestURI="${requestURI}" pagesize="10" class="displaytag">
		<display:column property="section"/>
	</display:table>
	<input type="submit" name="section"
	value="<spring:message code ="tutorial.addSection"/>" />
	<br/>
	</security:authorize>
	<br/>

</form:form>

<security:authorize access="hasRole('HANDY_WORKER')">
<input type="submit" name="save"
	value="<spring:message code ="tutorial.save"/>"
	onclick="javascript: relativeRedir('handyworker/tutorial/edit.do');" />
</security:authorize>

<input type="submit" name="cancel"
	value="<spring:message code ="tutorial.cancel"/>"
	onclick="javascript: relativeRedir('handyworker/tutorial/list.do');" />