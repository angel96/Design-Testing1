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

<p><spring:message code="fixUpTask.action.1" /></p>

<display:table name="fixUpTasks" id="row"
	requestURI="fixuptask/list.do" pagesize="5"
	class="displaytag">

	<display:column>
		<a href="fixuptaks/customer/edit.do?id=${row.id}">
		<img src="images/viewmore.png" />
		</a>
	</display:column>
	
	<display:column property="tickers" titleKey="fixuptask.tickers"/>
	<display:column property="moment" titleKey="fixuptask.moment"/>
	<display:column property="description" titleKey="fixuptask.description" />
	<display:column property="maximunPrice" titleKey="fixuptask.maximunPrice"/>
	<display:column property="category" titleKey="fixuptask.category"/>
	<display:column property="creator" titleKey="fixuptask.creator" />
	
	<security:authorize access="hasRole('CUSTOMER')">
	<jstl:if test="${row.creator}==${principal.username}">
	<display:column>
		<a href="fixuptaks/customer/edit.do?id=${row.id}">
		<img src="images/update.png" />
		</a>
	</display:column>
	</jstl:if>
	</security:authorize>
	
	<security:authorize access="hasRole('CUSTOMER')">
	<jstl:if test="${row.creator}==${principal.username}">
	<display:column>
		<input type="submit" name="delete"
		value="<img src="images/trash.png" />" />
	</display:column>
	</jstl:if>
	</security:authorize>
	
</display:table>

<!-- El create va en el desplegable -->