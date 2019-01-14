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

<display:table name="phases" id="row" requestURI="${requestURI}"
	pagesize="5" class="displaytag">

	<display:column property="number" titleKey="phase.number" />
	<display:column property="title" titleKey="phase.title" />
	<display:column property="description" titleKey="phase.description" />
	<display:column property="startMoment" titleKey="phase.startMoment" />
	<display:column property="endMoment" titleKey="phase.endMoment" />
	<display:column titleKey="phase.viewmore">
		<security:authorize access="hasRole('HANDY_WORKER')">
			<a href="phase/handyworker/edit.do?id=${row.id}"><spring:message
					code="phase.viewmore" /></a>
		</security:authorize>
		<security:authorize access="hasRole('CUSTOMER')">
			<a href="phase/customer/edit.do?id=${row.id}"><spring:message
					code="phase.viewmore" /></a>
		</security:authorize>
	</display:column>
	<jstl:forEach items="${errors}" var="error">
		<jstl:out value="${error}" />
	</jstl:forEach>
	<jstl:out value="${oops}" />
	<jstl:out value="${message}" />
</display:table>
</br>
<security:authorize access="hasRole('HANDY_WORKER')">
	<jstl:if test="${ownfixup}">
		<a
			href="phase/handyworker/create.do?fixuptask=<%=request.getParameter("fixuptask")%>"><spring:message
				code="phase.create" /></a>
	</jstl:if>
</security:authorize>
