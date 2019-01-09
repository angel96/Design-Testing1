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


<display:table name="actors" id="row" requestURI="${requestURI}"
	pagesize="5" class="displaytag">

	<display:column property="name" titleKey="endorsement.name" />
	<display:column property="surname" titleKey="endorsement.surname" />
	<display:column property="photo" titleKey="endorsement.photo" />
	<display:column property="email" titleKey="endorsement.email" />
	<display:column property="phone" titleKey="endorsement.phone" />
	<security:authorize access="hasRole('CUSTOMER')">
	<display:column>
			<a href="endorsement/customer/create.do?idActor=${row.id}"><spring:message
					code="endorsement.create" /></a>
	</display:column> 
	</security:authorize>
	<security:authorize access="hasRole('HANDY_WORKER')">
	<display:column>
			<a href="endorsement/handyworker/create.do?idActor=${row.id}"><spring:message
					code="endorsement.create" /></a>
	</display:column> 
	</security:authorize>

</display:table>

