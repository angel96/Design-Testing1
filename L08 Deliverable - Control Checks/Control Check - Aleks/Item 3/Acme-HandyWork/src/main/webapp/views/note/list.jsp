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

<display:table name="notes" id="row"
	requestURI="${requestURI}" pagesize="5"
	class="displaytag">
	
	<display:column property="moment" titleKey="note.moment"/>
	<display:column property="customerComment" titleKey="note.customerComment"/>
	<display:column property="refereeComment" titleKey="note.refereeComment"/>
	<display:column property="handyWorkerComment" titleKey="note.handyWorkerComment"/>
	<security:authorize access="hasRole('REFEREE')">
		<display:column>
			<jstl:if test="${empty row.refereeComment}">
				<a href="note/referee/addComment.do?idNote=${row.id}"> <spring:message
						code="note.add" />
				</a>
			</jstl:if>
		</display:column>
	</security:authorize>
	<security:authorize access="hasRole('HANDY_WORKER')">
		<display:column>
			<jstl:if test="${empty row.handyWorkerComment}">
				<a href="note/handyworker/addComment.do?idNote=${row.id}"> <spring:message
						code="note.add" />
				</a>
			</jstl:if>
		</display:column>
	</security:authorize>
	<security:authorize access="hasRole('CUSTOMER')">
		<display:column>
			<jstl:if test="${empty row.customerComment}">
				<a href="note/customer/addComment.do?idNote=${row.id}"> <spring:message
						code="note.add" />
				</a>
			</jstl:if>
		</display:column>
	</security:authorize>
	
</display:table>