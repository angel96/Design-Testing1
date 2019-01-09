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
	<spring:message code="fixUpTask.action.1" />
</p>

<display:table name="fixuptasks" id="row" requestURI="fixuptask/customer/list.do"
	pagesize="5" class="displaytag">

	<display:column>
		<a href="fixuptask/customer/edit.do?id=${row.id}"> <img
			src="images/viewmore.png" />
		</a>
	</display:column>

	<display:column property="ticker" titleKey="fixuptask.tickers" />
	<display:column property="moment" titleKey="fixuptask.moment" />
	<display:column property="description" titleKey="fixuptask.description" />
	<display:column property="maximumPrice"
		titleKey="fixuptask.maximunPrice" />
	<display:column property="category.name" titleKey="fixuptask.category" />
	<security:authorize access="hasRole('CUSTOMER')">
	<display:column>
			<a href="complaint/customer/create.do?idFix=${row.id}"><spring:message
					code="fixuptask.createcomplaint" /></a>
		</display:column> 
		</security:authorize>

</display:table>

