<%--
 * action-1.jsp
 *
 * Copyright (C) 2018 Universidad de Sevilla
 * 
 * The use of this project is hereby constrained to the conditions of the 
 * TDG Licence, a copy of which you may download from 
 * http://www.tdg-seville.info/License.html
 --%>
<link
	href="https://code.jquery.com/ui/1.10.4/themes/ui-lightness/jquery-ui.css"
	rel="stylesheet">
<script>
	$(function() {
		$("#datepicker-1").datepicker({
			appendText : "(yy/mm/dd)",
			dateFormat : "yy/mm/dd"
		});
		$("#datepicker-2").datepicker({
			appendText : "(yy/mm/dd)",
			dateFormat : "yy/mm/dd"
		});
	});
</script>
<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>

<p>
	<spring:message code="finder.action.1" />
</p>

<form:form action="fixuptask/handyworker/search.do"
	modelAttribute="finder">
	<form:hidden path="id" />
	<form:hidden path="version" />
	<form:hidden path="creationDate" />

	<form:label path="singleKey">
		<spring:message code="finder.key" />
	</form:label>
	<form:input path="singleKey" />
	<form:errors cssClass='error' path="singleKey" />
	<br />
	<form:label path="category">
		<spring:message code="finder.category" />
	</form:label>
	<form:select path="category">
		<form:option value="0" label="---" />
		<jstl:if test="${lang eq 'en'}">
			<form:options items="${categories}" itemValue="id" itemLabel="name" />
		</jstl:if>
		<jstl:if test="${lang eq 'es'}">
			<form:options items="${categories}" itemValue="id"
				itemLabel="otherlanguages[0]" />
		</jstl:if>
	</form:select>
	<br>
	<form:label path="warranty">
		<spring:message code="finder.warranty" />
	</form:label>
	<form:select path="warranty" multiple="false">
		<form:option value="0" label="-----------" />
		<form:options items="${warranties}" itemValue="id" itemLabel="title" />
	</form:select>
	<br>
	<form:label path="price1">
		<spring:message code="finder.min" />
	</form:label>
	<form:input path="price1" placeholder="0.0" type="text" />
	(<spring:message code="finder.set" />)
	<form:errors cssClass='error' path="price1" />
	<br>
	<form:label path="price2">
		<spring:message code="finder.max" />
	</form:label>
	<form:input path="price2" placeholder="10.0" type="text" />
	(<spring:message code="finder.set" />)
	<form:errors cssClass='error' path="price2" />
	<br>
	<form:label path="startDate">
		<spring:message code="finder.date1" />
	</form:label>
	<form:input path="startDate" type="text" id="datepicker-1" />
	<br>
	<form:label path="endDate">
		<spring:message code="finder.date2" />
	</form:label>
	<form:input path="endDate" type="text" id="datepicker-2" />
	<br>
	
	<input type="submit" name="search"
		value="<spring:message code="finder.find" />" />
	<a href="fixuptask/handyworker/searchList.do?id=${finder.id}"><spring:message code = "finder.previous" /></a>
</form:form>
<a href="fixuptask/handyworker/searchList.do?id="></a>
<input type="button" name="cancel"
	value="<spring:message code="cust.cancel"/>"
	onclick="javascript:relativeRedir('/welcome/index.do');" />