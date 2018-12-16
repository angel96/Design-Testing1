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

<p><spring:message code="complaint.action.2" /></p>

<form:form action="complaint/customer/edit.do"
modelAttribute="complaintObject">

<form:hidden path="id" />
<form:hidden path="version" />

<form:label path="tickers">
	<spring:message code="complaint.tickers" />
</form:label>
<form:input path="tickers" readonly="true" />
<form:errors cssClass='error' path="tickers" />
<br />

<form:label path="moment">
	<spring:message code="complaint.moment" />
</form:label>
<form:input path="moment" readonly="true" />
<form:errors cssClass='error' path="moment" />
<br />

<form:label path="description">
	<spring:message code="complaint.description" />
</form:label>
<form:input path="description"  />
<form:errors cssClass='error' path="description" />
<br />

<form:label path="attachment">
	<spring:message code="complaint.attachment" />
</form:label>

<form:errors cssClass='error' path="attachment" />
<br />
<display:table name="attachment" id="row" requestURI="${requestURI}" pagesize="10" class="displaytag">
		<display:column property="attachment"/>
	</display:table>
<input type="submit" name="add attachment"
	value="<spring:message code ="complaint.addattachment"/>"
	onclick="javascript: relativeRedir('attachment/customer/list.do');" />
	
</form:form>

<security:authorize access="hasRole('CUSTOMER')">
<input type="submit" name="save"
			value="<spring:message code="complaint.save" />" 
			onclick="javascript: confirm('<spring:message code="complaint.sure" />')"/>
</security:authorize>

<input type="submit" name="cancel"
	value="<spring:message code ="complaint.cancel"/>"
	onclick="javascript: relativeRedir('customer/complaint/list.do');" />
