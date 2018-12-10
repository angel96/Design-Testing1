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
<title><spring:message code="administrator.finder.title"></spring:message></title>
</head>
<body>
<form:form action="finder/administrator/edit.do" modelAttribute="finder">
			<h1>
				<spring:message code="administrator.finder.title"></spring:message>
			</h1>
			<p>
				<spring:message code="administrator.finder.description"></spring:message>
			</p>
			<form:label path="timeStored">	
				<spring:message code="finder.timeStored" />
			</form:label>
			<form:input path="timestored" placeholder="<fmt:formatDate value="${timeStored}" pattern="hh"/>"/>
		 	<form:input path="timestored" placeholder="<fmt:formatDate value="${timeStored}" pattern="mm"/>"/>
		 	
		<input type="submit" name="save" 
		value="<spring:message code='finder.save' />" />
		
		<input type="submit" name="cancel" 
		value="<spring:message code='mainView' />" />
	
</form:form>
</body>
</html>