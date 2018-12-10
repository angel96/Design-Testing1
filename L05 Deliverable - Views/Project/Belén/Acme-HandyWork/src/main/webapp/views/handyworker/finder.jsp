<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl"	uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>


<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title><spring:message code="handyworker.finder.titlepage"></spring:message></title>
</head>
<body>
<form:form action="finder/handyworker/search.do" modelAttribute="finder">
		<h1>
			<spring:message code="handyworker.finder.title"></spring:message>
		</h1>
		<form:label path="singleKey">
			<spring:message code="finder.singleKey"></spring:message>
		</form:label>
		<form:input path="singlekey" placeholder = "Single Key"/>
		<br>
		
		<form:label path="category">
			<spring:message code="finder.category"></spring:message>
		</form:label>
		<form:select id="categories" path="category">
			<form:option value="0" label="----" />		
			<form:options items="${categories}" itemValue="id" itemLabel="name" />
		</form:select>
		<br>
		
		<form:label path="warranty">
			<spring:message code="finder.warranty"></spring:message>
		</form:label>
		<form:select id="warranties" path="warranty">
			<form:option value="0" label="----" />		
			<form:options items="${warranties}" itemValue="id" itemLabel="title" />
		</form:select>
		<br>
		
		<p><spring:message code="handyworker.finder.prices"></spring:message></p>
		<form:label path="rangePrices">
			<spring:message code="finder.rangePrices"></spring:message>
		</form:label>
		<form:input path="minPrice" placeholder="0"/>
		<spring:message code="handyworker.finder.to"></spring:message>
		<form:input path="maxPrice" placeholder="100"/>
		<form:errors cssClass="error" path="rangePrices" placeholder="<spring:message code="handyworker.finder.errorprice"></spring:message>"/>
		<br>
		
		<p><spring:message code="handyworker.finder.dates"></spring:message></p>
		<form:label path="rangeDates">
			<spring:message code="finder.rangeDates"></spring:message>
		</form:label>
		<form:input path="minDate" placeholder="dd/mm/yyyy hh:mm" />
		<spring:message code="handyworker.finder.to"></spring:message>
		<form:input path="maxDate" placeholder="dd/mm/yyyy hh:mm" />
		<form:errors cssClass="error" path="rangePrices" placeholder="<spring:message code="handyworker.finder.errordate"></spring:message>"/>
		<br>
		
		<display:table pagesize="5" class="displaytag" 
	name="fixuptasks" requestURI="${requestURI}" id="row">
			<spring:message code="handyworker.finder.ticker" var="tickerFixUp" />
			<display:column property="ticker" titleKey="fixUpTask.ticker" sortable="true" />
			
			<spring:message code="handyworker.finder.description" var="descriptionFixUp" />
			<display:column property="description" titleKey="fixUpTask.description" sortable="true" />
		</display:table>
</form:form>
</body>
</html>