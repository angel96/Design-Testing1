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

<jstl:if test="${not own}">
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
</jstl:if>
<jstl:if test="${own}">
<display:table name="endorsements" id="row" requestURI="${requestURI}"
	pagesize="5" class="displaytag">

	<display:column property="moment" titleKey="endorsement.moment" />
	<display:column property="userReceived.name" titleKey="endorsement.userReceived" />
	<display:column property="comments" titleKey="endorsement.comments.view" />
	<display:column>
	<security:authorize access="hasRole('HANDY_WORKER')">
	<a href="endorsement/handyworker/update.do?idEndorsement=${row.id }">
		<spring:message code="endorsement.update" /></a>
	</security:authorize>
	<security:authorize access="hasRole('CUSTOMER')">
	<a href="endorsement/customer/update.do?idEndorsement=${row.id }">
		<spring:message code="endorsement.update" /></a>
	</security:authorize>
	</display:column>
	<display:column>
	<security:authorize access="hasRole('HANDY_WORKER')">
	<a href="endorsement/handyworker/delete.do?idEndorsement=${row.id }">
		<spring:message code="endorsement.delete" />
		</a></security:authorize>
	<security:authorize access="hasRole('CUSTOMER')">
	<a href="endorsement/customer/delete.do?idEndorsement=${row.id }">
		<spring:message code="endorsement.delete" />
		</a></security:authorize>
	</display:column>	
</display:table>
</jstl:if>