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
<script type="text/javascript">
	$(function() {
		$("#datepicker-1").datepicker({
			appendText : "(yy/mm/dd)",
			dateFormat : "yy/mm/dd"
		});

		$(function() {
			$("#datepicker-2").datepicker({
				appendText : "(yy/mm/dd)",
				dateFormat : "yy/mm/dd"
			});
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
	<jstl:set var="lang" value="${lang}" />
</p>

<form:form action="fixuptask/customer/edit.do"
	modelAttribute="fixUpTask">

	<form:hidden path="id" />
	<form:hidden path="version" />
	<form:hidden path="ticker" />
	<form:hidden path="moment" />
	<form:label path="ticker">
		<spring:message code="fixuptask.tickers" />
	</form:label>
	: <jstl:out value="${fixUpTask.ticker}" />
	<br>
	<form:label path="description">
		<spring:message code="fixuptask.description" />
	</form:label>
	<form:input path="description" readonly="${view}" />
	<form:errors cssClass="error" path="description"></form:errors>
	<br />

	<form:label path="address">
		<spring:message code="fixuptask.address" />
	</form:label>
	<form:input path="address" readonly="${view}" />
	<form:errors cssClass="error" path="address"></form:errors>
	<br />

	<form:label path="maximumPrice">
		<spring:message code="fixuptask.maximunPrice" />
	</form:label>
	<form:input path="maximumPrice" readonly="${view}" />
	<form:errors cssClass="error" path="maximumPrice"></form:errors>
	<br />

	<form:label path="start">
		<spring:message code="fixuptask.startDate" />
	</form:label>
	<form:input path="start" type="text" id="datepicker-1"
		readonly="${view}" />
	<br />

	<form:label path="end">
		<spring:message code="fixuptask.endDate" />
	</form:label>
	<form:input path="end" readonly="${view}" id="datepicker-2" />
	<br />


	<form:select path="category" disabled="${view}">
		<form:option value="0" label="---" />
		<jstl:if test="${lang eq 'en'}">
			<form:options items="${categories}" itemValue="id" itemLabel="name" />
		</jstl:if>
		<jstl:if test="${lang eq 'es'}">
			<form:options items="${categories}" itemValue="id"
				itemLabel="otherlanguages[0]" />
		</jstl:if>
	</form:select>
	<br />

	<form:select path="warranty" disabled="${view}">
		<option value="0" label="---" />
		<form:options items="${warranties}" itemValue="id" itemLabel="title" />
	</form:select>

	<security:authorize access="hasRole('HANDY_WORKER')">
		<jstl:if test="${res eq true}">
			<form:label path="application">
				<spring:message code="fixuptask.application" />
			</form:label>
			<a href="application/handyworker/create.do?fixUpId=${fixUpTask.id}"><spring:message
					code="fixuptask.apply" /></a>
		</jstl:if>
	</security:authorize>

	<security:authorize access="hasRole('CUSTOMER')">
		<jstl:if test="${not view}">
			<input type="submit" name="save"
				value="<spring:message code="fixuptask.save" />" />

			<input type="submit" name="delete"
				value="<spring:message code="fixuptask.delete" />" />
		</jstl:if>
	</security:authorize>
	<security:authorize access="hasRole('HANDY_WORKER')">
		<a href="customer/handyworker/show.do?fixup=${fixUpTask.id}"><spring:message
				code="fixuptask.customer" /></a>
	</security:authorize>
<jstl:if test="${fixUpTask.id !=0 }">
<display:table name="wages" id="row" requestURI="${requestURI}"
	pagesize="5" class="displaytag">
	
	<display:column property="ticker" titleKey="wage.ticker" />
	<jstl:if test="${lang eq 'en' }">
	<display:column property="publicationMoment" titleKey="wage.moment" format="{0,date,yy/MM/dd HH:mm}"/>
	</jstl:if>
	<jstl:if test="${lang eq 'es' }">
	<display:column property="publicationMoment" titleKey="wage.moment" format="{0,date,dd-MM-yy HH:mm}"/>
	</jstl:if>
	<display:column property="body" titleKey="wage.body" />
	<display:column property="url"	titleKey="wage.url" />
	<display:column property="finalMode" titleKey="wage.finalMode"></display:column>
	
	<security:authorize access="hasRole('CUSTOMER')">
			<display:column>
				<a href="wage/customer/view.do?id=${row.id}&view=true"> <img
					src="images/viewmore.png" />
				</a>
		</display:column>
		</security:authorize>
		
		<security:authorize access="hasRole('HANDY_WORKER')">
			<display:column>
				<a href="wage/handyworker/view.do?id=${row.id}&view=true"> <img
					src="images/viewmore.png" />
				</a>
		</display:column>
	</security:authorize>
	</display:table>

<security:authorize access="hasRole('CUSTOMER')">
	<input type="submit" name="cancel"
		value="<spring:message code ="fixuptask.cancel"/>"
		onclick="javascript: relativeRedir('fixuptask/customer/list.do');" />
</security:authorize>

<security:authorize access="hasRole('HANDY_WORKER')">

	<input type="submit" name="cancel"
		value="<spring:message code ="fixuptask.cancel"/>"
		onclick="javascript: relativeRedir('fixuptask/handyworker/list.do');" />
</security:authorize>
</jstl:if>
</form:form>
<script>
  var table = document.getElementById("row");
  var tbody = table.getElementsByTagName("tbody")[0];
  var row = tbody.getElementsByTagName("tr");
  var fecha_actual = new Date();
     
    var lang = '${lang}';

  for (var i = 0; i < row.length; i++) {
    if(lang == 'en') {
      var value = row[i].getElementsByTagName("td")[1].firstChild.nodeValue.split("/");
      value = new Date("20"+value[0]+"-"+value[1]+"-"+value[2].substring(0,2));
    }else if(lang == 'es'){
      var value = row[i].getElementsByTagName("td")[1].firstChild.nodeValue.split("-");
      value = new Date("20"+value[2].substring(0,2)+"-"+value[1]+"-"+value[0]);
    }
     var fecha_hace_un_mes = new Date();
     var fecha_hace_dos_meses = new Date();
     if(fecha_actual.getMonth()+1 == 1) {
       fecha_hace_un_mes.setFullYear(fecha_actual.getFullYear()-1, fecha_actual.getMonth()+11, fecha_actual.getDate());
       fecha_hace_dos_meses.setFullYear(fecha_actual.getFullYear()-1, fecha_actual.getMonth()+10, fecha_actual.getDate());
     }else if(fecha_actual.getMonth()+1 == 2){
       fecha_hace_un_mes.setFullYear(fecha_actual.getFullYear(), fecha_actual.getMonth()-1, fecha_actual.getDate());
       fecha_hace_dos_meses.setFullYear(fecha_actual.getFullYear()-1, fecha_actual.getMonth()+10, fecha_actual.getDate());
     }else{
       fecha_hace_un_mes.setFullYear(fecha_actual.getFullYear(), fecha_actual.getMonth()-1, fecha_actual.getDate());
       fecha_hace_dos_meses.setFullYear(fecha_actual.getFullYear(), fecha_actual.getMonth()-1, fecha_actual.getDate());
    }

     
     if(value <= fecha_actual && value > fecha_hace_un_mes) {
       row[i].getElementsByTagName("td")[1].style.color = "darkOrange";
     }else if (value <= fecha_hace_un_mes && value > fecha_hace_dos_meses) {
       row[i].getElementsByTagName("td")[1].style.color = "slateGrey";
     }else{
       row[i].getElementsByTagName("td")[1].style.color = "tomato";
     }

  }
  
</script>