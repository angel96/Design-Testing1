<%--
 * action-1.jsp
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
		<display:column titleKey="wage.edit">
			<jstl:if test="${row.finalMode eq 'false'}">
				<a href="wage/customer/edit.do?id=${row.id}"> <spring:message code="wage.edit" /></a>
			</jstl:if>
		</display:column>
		</security:authorize>
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
  	console.log(value[0]);
	console.log(value[1]);
	console.log(value[2]);
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
