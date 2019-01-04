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

<p><spring:message code="curriculum.personal" /></p>

<p><spring:message code="curriculum.education.title" /></p>
<display:table name="educationrecord" id="row"
	requestURI="${requestURI}" pagesize="5"
	class="displaytag">

<display:column property="diplomaTitle" titleKey="curriculum.education.diplomaTitle"/>
<display:column property="startStudies" titleKey="curriculum.education.startStudies"/>
<display:column property="endStudies" titleKey="curriculum.education.endStudies"/>
<display:column property="institutionDiploma" titleKey="curriculum.education.institutionDiploma" />
<display:column property="attachment" titleKey="curriculum.attachment"/>
		
</display:table>
<a href="educationrecord/handyworker/create.do"><spring:message code="curriculum.education.create"/></a>

<p><spring:message code="curriculum.professional.title" /></p>
<display:table name="professionalrecord" id="row"
	requestURI="${requestURI}" pagesize="5"
	class="displaytag">

<display:column property="companyName" titleKey="curriculum.professional.companyName"/>
<display:column property="startWorking" titleKey="curriculum.professional.startWorking"/>
<display:column property="endWorking" titleKey="curriculum.professional.endWorking"/>
<display:column property="role" titleKey="curriculum.professional.role" />
<display:column property="attachment" titleKey="curriculum.attachment"/>
		
</display:table>

