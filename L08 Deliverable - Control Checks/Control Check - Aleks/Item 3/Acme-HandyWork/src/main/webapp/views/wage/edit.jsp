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

<form:form action="wage/customer/edit.do?fixId=${fixId }"
	modelAttribute="wage">

	<form:hidden path="id" />
	<form:hidden path="version" />
	<form:hidden path="ticker" />
	<form:hidden path="publicationMoment" />
	<form:label path="ticker">
		<spring:message code="wage.ticker" />
	</form:label>
	: <jstl:out value="${wage.ticker}" />
	<br>
	<form:label path="body">
		<spring:message code="wage.body" />
	</form:label>
	<form:input path="body"  readonly="${view}" />
	<form:errors cssClass="error" path="body"></form:errors>
	<br />

	<form:label path="url">
		<spring:message code="wage.url" />
	</form:label>
	<form:input path="url" readonly="${view}" placeholder="http://..." />
	<form:errors cssClass="error" path="url"></form:errors>
	<br />
	
	<jstl:if test="${wage.id != 0}">
		<form:label path="finalMode">
			<spring:message code="wage.finalMode" />
		</form:label>

		<form:checkbox path="finalMode" disabled="${view}"/>
	</jstl:if>
	<br/>

	<security:authorize access="hasRole('CUSTOMER')">
  		<jstl:if test="${not view}">
			<input type="submit" name="save"
				value="<spring:message code="wage.save" />" />
				</jstl:if>
		<jstl:if test="${wage.finalMode eq 'false'}">
		<jstl:if test="${wage.id != 0 }">
			<input type="submit" name="delete"
				value="<spring:message code="wage.delete" />" />
  		</jstl:if>
  		</jstl:if>
	</security:authorize>
	
</form:form>

	<input type="submit" name="cancel"
		value="<spring:message code ="wage.cancel"/>"
		onclick="javascript: relativeRedir('wage/customer/list.do');" />
