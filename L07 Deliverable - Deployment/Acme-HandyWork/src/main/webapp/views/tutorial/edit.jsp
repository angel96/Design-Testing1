<%--
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

<style>
#slider {
	position: relative;
	overflow: hidden;
	border-radius: 4px;
}

#slider ul {
	position: relative;
	margin: 0;
	padding: 0;
	height: 200px;
	list-style: none;
}

#slider ul li {
	position: relative;
	display: block;
	float: left;
	margin: 0;
	padding: 0;
	width: 200px;
	height: 150px;
	text-align: center;
	line-height: 300px;
}
</style>

<script>
	jQuery(document).ready(function($) {

		setInterval(function() {
			moveRight();
		}, 3000);

		var slideCount = $('#slider ul li').length;
		var slideWidth = $('#slider ul li').width();
		var slideHeight = $('#slider ul li').height();
		var sliderUlWidth = slideCount * slideWidth;

		$('#slider').css({
			width : slideWidth,
			height : slideHeight
		});

		$('#slider ul').css({
			width : sliderUlWidth,
			marginLeft : -slideWidth
		});

		$('#slider ul li:last-child').prependTo('#slider ul');

		function moveLeft() {
			$('#slider ul').animate({
				left : +slideWidth
			}, 200, function() {
				$('#slider ul li:last-child').prependTo('#slider ul');
				$('#slider ul').css('left', '');
			});
		}
		;

		function moveRight() {
			$('#slider ul').animate({
				left : -slideWidth
			}, 200, function() {
				$('#slider ul li:first-child').appendTo('#slider ul');
				$('#slider ul').css('left', '');
			});
		}
		;

	});
</script>

<form:form action="${requestURI}" modelAttribute="tutorial">

	<form:hidden path="id" />
	<form:hidden path="version" />
	<form:hidden path="sponsorship" />
	<form:hidden path="section" />
	<div>
		<form:label path="title">
			<spring:message code="tutorial.title" />
		</form:label>
		<form:input path="title" readonly="${view}" />
		<form:errors cssClass="error" path="title"></form:errors>
		<br />
		<form:label path="lastUpdate">
			<spring:message code="tutorial.lastUpdate" />
		</form:label>
		<form:input path="lastUpdate" readonly="true" />
		<br />
		<form:label path="summary">
			<spring:message code="tutorial.summary" />
		</form:label>
		<form:textarea path="summary" readonly="${view}" />
		<form:errors cssClass="error" path="summary"></form:errors>
		<br />
		<form:label path="picture">
			<spring:message code="tutorial.picture" />
		</form:label>
		<form:textarea path="picture" readonly="${view}" />
		<form:errors cssClass="error" path="picture"></form:errors>
		<br /> <a href="handyworker/show.do?tutorial=${tutorial.id}"><spring:message
				code="tutorial.handy" /></a>
		<jstl:forEach items="${errors}" var="error">
			<jstl:out value="${error}" />
		</jstl:forEach>
		<jstl:out value="${oops}" />
		<jstl:out value="${message}" />
		<security:authorize access="hasRole('HANDY_WORKER')">
			<input type="submit" name="save"
				value="<spring:message code ="tutorial.save"/>" />
			<jstl:if test="${tutorial.id != 0}">
				<input type="submit" name="delete"
					value="<spring:message code ="tutorial.delete"/>" />
			</jstl:if>
		</security:authorize>
		</br>
	</div>
	<div id="slider">
		<ul>
			<jstl:forEach items="${tutorial.sponsorship}" var="s">
				<li><img src="${s.urlBanner}" height="150" width="200" /></li>
			</jstl:forEach>
		</ul>
	</div>
</form:form>

<input type="submit" name="cancel"
	value="<spring:message code ="tutorial.cancel"/>"
	onclick="javascript: relativeRedir('/welcome/index.do');" />