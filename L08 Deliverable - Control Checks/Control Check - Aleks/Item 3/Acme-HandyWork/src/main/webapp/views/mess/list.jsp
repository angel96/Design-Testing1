<%--
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
<h2>${boxName}</h2>
</p>
<display:table name="messages" id="row" requestURI="${requestURI}"
	pagesize="5" class="displaytag">

	<display:column property="subject" titleKey="message.subject" />
	<display:column property="body" titleKey="message.content" />
	<display:column titleKey="message.tags">
		<jstl:forEach items="${row.tags}" var="tag">
			<jstl:out value="${tag}"></jstl:out>
		</jstl:forEach>
	</display:column>
	<display:column property="priority" titleKey="message.priority" />
	<display:column property="moment" titleKey="message.date" />

	<display:column>
		<a href="box/mess/create.do?id=${row.id}"><spring:message
				code="message.viewmore" /></a>
	</display:column>
	<jstl:if test="${boxName == 'Spam Box' or boxName == 'Trash Box'}">
		<display:column>

			<a href="box/mess/inbox.do?id=${row.id}"><spring:message
					code="message.inbox" /></a>

		</display:column>
	</jstl:if>
	<jstl:if test="${boxName == 'Trash Box'}">
		<display:column>

			<a href="box/mess/delete.do?id=${row.id}"><spring:message
					code="message.delete" /></a>

		</display:column>
	</jstl:if>

</display:table>