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

<p>
	<spring:message code="phase.title" />
</p>

<display:table name="sections" id="row" requestURI="${requestURI}"
	pagesize="5" class="displaytag">

	<display:column property="number" titleKey="section.number" />
	<display:column property="title" titleKey="section.title" />
	<display:column property="text" titleKey="section.text" />
	<display:column property="picture" titleKey="section.picture" />
	<security:authorize access="hasRole('HANDY_WORKER')">
		<display:column titleKey="section.edit">
			<a href="section/handyworker/edit.do?id=${row.id}"><spring:message
					code="section.edit" /></a>
		</display:column>
	</security:authorize>

	<jstl:forEach items="${errors}" var="error">
		<jstl:out value="${error}" />
	</jstl:forEach>
	<jstl:out value="${oops}" />
	<jstl:out value="${message}" />


</display:table>

<security:authorize access="hasRole('HANDY_WORKER')">

	<a
		href="section/create.do?tutorial=<%=request.getParameter("tutorial")%>"><spring:message
			code="section.create" /></a>

</security:authorize>