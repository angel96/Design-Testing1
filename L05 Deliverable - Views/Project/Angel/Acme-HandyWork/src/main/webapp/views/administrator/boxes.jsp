<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl"	uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>

<form:form modelAtributte="boxes" action="messages/send.do">
<form:select path="boxes"></form:select>
<form:options item="${boxes}" itemLabel="Box" itemValue="id"/>
<form:option label="----" value="0"></form:option>
<display:table name="boxes" id="row" requestURI="${requestURI}" 
				pagesize=5 class="displaytag"></display:table>
<display:column property="<spring:message code=admin.box.recipient/>" titleKey="row.receiver"></display:column>
<display:column property="<spring:message code=admin.box.moment/>" titleKey="row.moment"></display:column>
<display:column property="<spring:message code=admin.box.subject/>" titleKey="row.subject"></display:column>
<display:column property="<spring:message code=admin.box.priority/>" titleKey="row.priority"></display:column>
<display:column property="<spring:message code=admin.box.view/>" titleKey="messages/view?id=${row.id}"></display:column>

<a href="messages/create.do"> Create New Message</a>

</form:form>