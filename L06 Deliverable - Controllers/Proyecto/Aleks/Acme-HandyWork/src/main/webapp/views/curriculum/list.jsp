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

<jstl:if test="${linkedin == null}">
<p><spring:message code="curriculum.createlinkedin.info"/></p>
<input type="button" name="linkedin" value="<spring:message code="curriculum.createlinkedin"/>"
onclick="javascript:relativeRedir('profile/create.do');"/>
</jstl:if>
<jstl:if test="${linkedin != null}">
<h2><spring:message code="curriculum.personal" /></h2>
<p><spring:message code="curriculum.personal.fullName" />:&nbsp;<jstl:out value="${handyworker.name}"></jstl:out>
<p><spring:message code="curriculum.personal.photo" />:&nbsp;<jstl:out value="${handyworker.photo}"></jstl:out>
<p><spring:message code="curriculum.personal.email" />:&nbsp;<jstl:out value="${handyworker.email}"></jstl:out>
<p><spring:message code="curriculum.personal.phone" />:&nbsp;<jstl:out value="${handyworker.phone}"></jstl:out>
<p><spring:message code="curriculum.personal.url" />:&nbsp;<jstl:out value="${linkedin.link}"></jstl:out>

<h2><spring:message code="curriculum.education.title" /></h2>
<display:table name="educationrecord" id="row"
	requestURI="${requestURI}" pagesize="5"
	class="displaytag">

<display:column property="diplomaTitle" titleKey="curriculum.education.diplomaTitle"/>
<display:column property="startStudies" titleKey="curriculum.education.startStudies"/>
<display:column property="endStudies" titleKey="curriculum.education.endStudies"/>
<display:column property="institutionDiploma" titleKey="curriculum.education.institutionDiploma" />
<display:column property="attachment" titleKey="curriculum.attachment"/>
<display:column property="comments" titleKey="curriculum.comments"/>
		
</display:table>
<a href="educationrecord/handyworker/create.do"><spring:message code="curriculum.education.create"/></a>

<h2><spring:message code="curriculum.professional.title" /></h2>
<display:table name="professionalrecord" id="row"
	requestURI="${requestURI}" pagesize="5"
	class="displaytag">

<display:column property="companyName" titleKey="curriculum.professional.companyName"/>
<display:column property="startWorking" titleKey="curriculum.professional.startWorking"/>
<display:column property="endWorking" titleKey="curriculum.professional.endWorking"/>
<display:column property="role" titleKey="curriculum.professional.role" />
<display:column property="attachment" titleKey="curriculum.attachment"/>
<display:column property="comments" titleKey="curriculum.comments"/>
</display:table>
<a href="professionalrecord/handyworker/create.do"><spring:message code="curriculum.professional.create"/></a>
	
<h2><spring:message code="curriculum.endorser.title" /></h2>
<display:table name="endorserrecord" id="row"
	requestURI="${requestURI}" pagesize="5"
	class="displaytag">

<display:column property="fullNameEndorser" titleKey="curriculum.endorser.fullNameEndorser"/>
<display:column property="email" titleKey="curriculum.endorser.email"/>
<display:column property="phone" titleKey="curriculum.endorser.phone" />
<display:column property="linkedin" titleKey="curriculum.endorser.linkedin"/>
<display:column property="comments" titleKey="curriculum.comments"/>
</display:table>
<a href="endorserrecord/handyworker/create.do"><spring:message code="curriculum.endorser.create"/></a>

<h2><spring:message code="curriculum.miscellaneous" /></h2>
<display:table name="miscellaneousrecord" id="row"
	requestURI="${requestURI}" pagesize="5"
	class="displaytag">

<display:column property="title" titleKey="curriculum.miscellaneous.title"/>
<display:column property="attachment" titleKey="curriculum.attachment"/>
<display:column property="comments" titleKey="curriculum.comments"/>
</display:table>
<a href="miscellaneousrecord/handyworker/create.do"><spring:message code="curriculum.miscellaneous.create"/></a>
</jstl:if>