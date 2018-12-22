<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl"	uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>

<form:form modelAtributte="boxes" action="boxes/list.do">

<form:select path="boxes">
<form:options item="${boxes}" itemLabel="Box" itemValue="id"/>
<form:option label="----" value="0"></form:option>
</form:select>

<display:table name="boxes" id="row" requestURI="${requestURI}" 
				pagesize=5 class="displaytag"></display:table>
<display:column property="<spring:message code=box.recipient/>" titleKey="row.receiver"></display:column>
<display:column property="<spring:message code=box.moment/>" titleKey="row.moment"></display:column>
<display:column property="<spring:message code=box.subject/>" titleKey="row.subject"></display:column>
<display:column property="<spring:message code=box.priority/>" titleKey="row.priority"></display:column>
<display:column property="<spring:message code=box.view/>" titleKey="messages/administrator/edit.do?id=${row.id}"></display:column>

<a href="messages/edit.do"> Create New Message</a>

</form:form>