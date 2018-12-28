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
		<a href="box/message/list.do?boxId=${row.id}">Ver mensajes</a>
	</display:column>
</display:table>

<a href="box/create.do">Crear caja</a>