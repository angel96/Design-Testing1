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

<form:form action="${requestURI}"	modelAttribute="application">
	
	<jstl:set var="statusacc" value="accepted"></jstl:set>
	<form:hidden path="id" />
	<form:hidden path="version" />
	<form:hidden path="moment"/>
	<form:hidden path="momentElapsed"/>
  	<form:hidden path="creditCard"/>
  	<form:hidden path="fixUpTask"/> 
  		
  	<spring:message code="application.fixUpTask" />
	<jstl:out value=" :${application.fixUpTask.description}" />
	<br>
	
	<security:authorize access="hasRole('HANDY_WORKER')">
	<form:hidden path="status"/>
	</security:authorize>
	
	<security:authorize access="hasRole('CUSTOMER')">
	<form:label path="status">
		<spring:message code="application.status" />
	</form:label>
	<form:select path="status">
		<form:option value="0" label="---" />
		<form:options items="${status}" />
	</form:select>
	</security:authorize>

	<form:label path="comments">
		<spring:message code="application.comments" />
	</form:label>
	<form:textarea path="comments"/>
	<br>
	
<!--  	<jstl:if test="${application.status} == ${statusacc}">
			<form:label path="creditCard">
				<spring:message code="application.addCreditCard" />
			</form:label>
			<input type="submit" name="creditCard" 
			value="<spring:message code ="application.addCreditCard"/>"
			onclick="javascript: relativeRedir('handyworker/creditCard/edit.do');" />
		</jstl:if>
		-->
	
	<br/>
	<security:authorize access="hasRole('HANDY_WORKER')">
	<form:label path="offeredPrice">
		<spring:message code="application.offeredPrice" />
	</form:label>
	<form:input path="offeredPrice"/>
	<br>
	
	</security:authorize>
	<security:authorize access="hasRole('CUSTOMER')">
	<form:label path="offeredPrice">
		<spring:message code="application.offeredPrice" />
	</form:label>
	<form:input path="offeredPrice" readonly="${true}"/>
	<br>
	</security:authorize>

<input type="submit" name="save"
	value="<spring:message code ="application.save"/>"/>
</form:form>

<input type="submit" name="cancel"
	value="<spring:message code ="application.cancel"/>"
	onclick="javascript: relativeRedir('application/handyworker/list.do');" />