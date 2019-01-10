<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>

<display:table name="boxes" id="row" requestURI="${requestURI}"
	pagesize="5" class="displaytag">
	<display:column property="name" titleKey="box.name" />
	<display:column property="fromSystem" titleKey="box.fromSystem" />
	<display:column titleKey="box.messages">
		<a href="box/mess/list.do?boxId=${row.id}"><spring:message
				code="box.messages" /></a>
	</display:column>
	<display:column titleKey="box.delete" >
		<jstl:if test="${not row.fromSystem}">

			<a href="box/delete.do?id=${row.id}"><spring:message
					code="box.delete" /></a>

		</jstl:if>
	</display:column>
</display:table>

<a href="box/create.do"><spring:message code="box.create" /></a>