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

<p><spring:message code="complaint.action.1" /></p>

<display:table name="complaints" id="row"
	requestURI="${requestURI}" pagesize="5"
	class="displaytag">

	<display:column>
		<a href="complaint/customer,handyworker,referee/view.do?id=${row.id}&view=true"> <img
			src="images/viewmore.png" />
		</a>
	</display:column>

<display:column property="ticker" titleKey="complaint.tickers"/>
<display:column property="moment" titleKey="complaint.moment"/>
<display:column property="description" titleKey="complaint.description" />
<display:column property="attachment" titleKey="complaint.attachment"/>
		
</display:table>
