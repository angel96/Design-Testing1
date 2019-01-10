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

<script type="text/javascript">
	$(document).ready(function() {
		$("#form").submit(function(event) {
			event.preventDefault();
			ajaxPost();
		});
		function ajaxPost() {
			var formData = {
				id : $("#id").val(),
				version : $("#version").val(),
				creationDate : $("#creationDate").val(),
				singleKey : $("#singleKey").val(),
				category : $("#category").val(),
				warranty : $("#warranty").val(),
				price1 : $("#price1").val(),
				price2 : $("#price2").val(),
				startDate : $("#datepicker-1").val(),
				endDate : $("#datepicker-2").val()
			};
			alert(JSON.stringify(formData));
			$.ajax({
				type : "POST",
				contentType : "application/json; charset=utf-8",
				url : "fixuptask/handyworker/searchAjax.do",
				data : JSON.stringify(formData),
				async : false,
				cache : false,
				dataType : 'json',
				success : function(result) {
					console.log(result);
				},
				error : function(e) {
					alert("Error!");
					console.log("Error: ", e);
				}
			});
		}
	});
</script>

<form:form id="form" modelAttribute="finder">
	<form:hidden path="id" id="id" />
	<form:hidden path="version" id="version" />
	<form:hidden path="creationDate" id="creationDate" />

	<form:label path="singleKey">
		<spring:message code="finder.key" />
	</form:label>
	<form:input path="singleKey" id="singleKey" />
	<br />

	<form:select id="category" path="category" multiple="false">
		<form:option value="0" label="-----------" />
		<form:options items="${categories}" itemValue="id" itemLabel="name" />
	</form:select>

	<form:select id="warranty" path="warranty" multiple="false">
		<form:option value="0" label="-----------" />
		<form:options items="${warranties}" itemValue="id" itemLabel="title" />
	</form:select>

	<form:label path="price1">
		<spring:message code="finder.min" />
	</form:label>
	<form:input id="price1" path="price1" placeholder="0.0" type="text" />

	<form:label path="price2">
		<spring:message code="finder.max" />
	</form:label>
	<form:input id="price2" path="price2" placeholder="10.0" type="text" />

	<form:label path="startDate">
		<spring:message code="finder.date1" />
	</form:label>
	<form:input path="startDate" type="text" id="datepicker-1" />

	<form:label path="endDate">
		<spring:message code="finder.date2" />
	</form:label>
	<form:input path="endDate" type="text" id="datepicker-2" />
	<jstl:forEach items="${errors}" var="er">
		<jstl:out value="${er}" />
	</jstl:forEach>
	<jstl:out value="${error}" />
	<jstl:out value="${oops}" />
	<input type="submit" name="search"
		value="<spring:message code="finder.find" />" />
</form:form>

<display:table name="fixuptasks" id="row"
	pagesize="5" class="displaytag">
	<security:authorize access="hasRole('CUSTOMER')">
		<display:column>
			<a href="${URI}edit.do?id=${row.id}"> <img
				src="images/viewmore.png" />
			</a>
		</display:column>
	</security:authorize>

	<display:column property="ticker" titleKey="fixuptask.tickers" />
	<display:column property="moment" titleKey="fixuptask.moment" />
	<display:column property="description" titleKey="fixuptask.description" />
	<display:column property="maximumPrice"
		titleKey="fixuptask.maximunPrice" />
	<display:column property="category.name" titleKey="fixuptask.category" />

	<security:authorize access="hasRole('CUSTOMER')">
		<display:column titleKey="fixuptask.applications">
			<a href="application/customer/listByFixUp.do?fixUpId=${row.id}"><spring:message
					code="fixuptask.applications" /> </a>
		</display:column>
	</security:authorize>

	<security:authorize access="hasRole('HANDY_WORKER')">

		<display:column titleKey="fixuptask.application">
			<jstl:forEach items="${row.application}" var="app">
				<jstl:if test="${app.status eq 'pending' or 'rejected'}">
					<a href="application/handyworker/create.do?fixUpId=${row.id}"><spring:message
							code="fixuptask.apply" /></a>
				</jstl:if>
			</jstl:forEach>
		</display:column>
	</security:authorize>

	<security:authorize access="hasRole('CUSTOMER')">
		<display:column titleKey="fixuptask.phases">
			<a href="phase/customer/list.do?fixuptask=${row.id}"><spring:message
					code="fixuptask.phases" /></a>
		</display:column>
		<display:column>
		
		<a href="complaint/customer/create.do?idFix=${row.id}"><spring:message
					code="fixuptask.complaints" /></a>
		</display:column>
	</security:authorize>

	<security:authorize access="hasRole('HANDY_WORKER')">
		<display:column titleKey="fixuptask.phases">
			<a href="phase/handyworker/list.do?fixuptask=${row.id}"><spring:message
					code="fixuptask.phases" /></a>
		</display:column>
	</security:authorize>

</display:table>