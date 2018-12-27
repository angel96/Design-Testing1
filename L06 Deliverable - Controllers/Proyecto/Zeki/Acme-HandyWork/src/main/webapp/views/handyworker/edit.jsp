<%--
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

<form:form action="handyWorker/edit.do" modelAttribute="handyWorker">
<form:hidden path="id"/>
<form:hidden path="version"/>
<form:hidden path="account.id" />
<form:hidden path="account.authorities" />
<form:hidden path="finder"/>
<form:hidden path="score"/>


	<form:label path="name">
 		<spring:message code="handy.name" ></spring:message> 

	</form:label>
	<form:input path="name" />
	<form:errors cssClass="error" path="name" />
	<br /> 

<form:label path="surname">
<spring:message code="handy.surname"></spring:message> 
</form:label>
<form:input path="surname"/>
<form:errors cssClass="error" path="surname"></form:errors>
<br>

<form:label path="middleName">
<spring:message code="handy.middlename"></spring:message>
</form:label>
<form:input path="middleName"/>
<form:errors cssClass="error" path="middleName"></form:errors>
<br>

<form:label path="phone">
<spring:message code="handy.phone"></spring:message> 
</form:label>
<form:input path="phone"/>
<form:errors cssClass="error" path="phone"></form:errors>
<br>

<form:label path="email">
<spring:message code="handy.email"></spring:message>
</form:label>
<form:input path="email"/>
<form:errors cssClass="error" path="email"></form:errors>
<br>

<form:label path="adress">
<spring:message code="handy.adress"></spring:message>
</form:label>
<form:input path="adress"/>
<form:errors cssClass="error" path="adress"></form:errors>
<br>

<form:label path="photo">
<spring:message code="handy.photo"></spring:message>
</form:label>
<form:input path="photo" placeholder="http://....."/>
<form:errors cssClass="error" path="photo"></form:errors>
<br>

<form:label path="make">
<spring:message code="handy.make"></spring:message>
</form:label>
<form:input path="make"/>
<form:errors cssClass="error" path="make"></form:errors>
<br>  

<form:label path="account.username">
<spring:message code="handy.user"></spring:message> 
</form:label>
<form:input path="account.username"/>
<form:errors cssClass="error" path="account.username"></form:errors>
<br>

<form:label path="account.password">
<spring:message code="handy.password"></spring:message>
</form:label>
<form:password path="account.password"/>
<form:errors cssClass="error" path="account.password"></form:errors>
<br>

 
 <jstl:forEach items="${errors}" var="error">
		<jstl:out value="${error}" />
	</jstl:forEach>
	<jstl:out value="${oops}" />
	<jstl:out value="${message}" />
	<input type="submit" name="save"
		value="<spring:message code="handy.save"/>" />
</form:form>
 
<input type="button" name="cancel" value="<spring:message code="handy.cancel"/>"
onclick="javascript:relativeRedir('/welcome/index.jsp');"/> 
