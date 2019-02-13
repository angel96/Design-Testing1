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

<p>
	<spring:message code="application.action.1" />
</p>

<display:table name="aolets" id="row" requestURI="aolet/list.do"
	pagesize="5" class="displaytag">

	<display:column property="title" titleKey="aolet.title" />
	<display:column property="body" titleKey="aolet.body" />
	<jstl:if test="${lang eq 'en'}">
		<display:column property="moment" titleKey="aolet.moment"
			format="{0,date,yy/MM/dd HH:mm}" />
	</jstl:if>
	<jstl:if test="${lang eq 'es' }">
		<display:column property="moment" titleKey="aolet.moment"
			format="{0,date,dd-MM-yy HH:mm}" />
	</jstl:if>
	<security:authorize access="hasRole('CUSTOMER')">
		<display:column>
			<a href="aolet/customer/edit.do?id=${row.id}"> <spring:message
					code="aolet.viewmore" />
			</a>
		</display:column>
	</security:authorize>

	<security:authorize access="hasRole('HANDY_WORKER')">
		<display:column>
			<a href="aolet/handyworker/edit.do?id=${row.id}"> <spring:message
					code="aolet.viewmore" />
			</a>
		</display:column>
	</security:authorize>

</display:table>

<script>
  		var table = document.getElementById("row");
 		var tbody = table.getElementsByTagName("tbody")[0];
  		var row = tbody.getElementsByTagName("tr");
 		// Guardamos la fecha actual
  		var actualDate = new Date();
  
  		// Llamamos a la variable lang del controlador
  		var lang = '${lang}';

  		for (i = 0; i < row.length; i++) {
			// Según el idioma, parseamos una fecha u otra, pues cada una posee un patrón único
    			if(lang == 'en') {
      				var value = row[i].getElementsByTagName("td")[2].firstChild.nodeValue.split("/");
      				console.log(value);
      				value = new Date("20"+value[0]+"-"+value[1]+"-"+value[2].substring(0,2));
    			}else if(lang == 'es') {
      				var value = row[i].getElementsByTagName("td")[2].firstChild.nodeValue.split("-");
      				value = new Date("20"+value[2].substring(0,2)+"-"+value[1]+"-"+value[0]);
    		}
	
	// Creamos dos variables, una que guarde la fecha de hace un mes y otra que guarde la fecha de hace dos meses
     		var beforeOneMonth = new Date();
    		var beforeTwoMonths = new Date();
     
     		if(actualDate.getMonth()+1 == 1) {
    	 	// Si el mes actual es enero, seteamos la fecha hace un mes, sumándole 11 meses y la fecha hace dos meses, sumándole 10 meses
    			beforeOneMonth.setFullYear(actualDate.getFullYear()-1, actualDate.getMonth()+11, actualDate.getDate());
    	 		beforeTwoMonths.setFullYear(actualDate.getFullYear()-1, actualDate.getMonth()+10, actualDate.getDate());
     
     		}else if(actualDate.getMonth()+1 == 2){
    	 	// Si el mes actual es febrero, seteamos la fecha hace dos meses, sumándole 10 meses
    			beforeOneMonth.setFullYear(actualDate.getFullYear(), actualDate.getMonth()-1, actualDate.getDate());
    	 		beforeTwoMonths.setFullYear(actualDate.getFullYear()-1, actualDate.getMonth()+10, actualDate.getDate());
     
    	 	}else{
    	 	// Para el resto de fechas "no especiales", tan solo hay que restar 1 mes para obtener la fecha hace un mes y 2 para obtener la fecha hace dos meses
    	 		beforeOneMonth.setFullYear(fecha_actual.getFullYear(), fecha_actual.getMonth()-1, fecha_actual.getDate());
    	 		beforeTwoMonths.setFullYear(fecha_actual.getFullYear(), fecha_actual.getMonth()-2, fecha_actual.getDate());
    		}

     // A continuación, colocamos las condiciones descritas utilizando las variables creadas anteriormente
     
     // Si la fecha de publicación tiene menos de un mes de antigüedad
     	if(value <= actualDate && value > beforeOneMonth) {
       		row[i].getElementsByTagName("td")[1].style.color = "#FFF8DC";
     // Si la fecha de publicación tiene entre uno (incluido) y dos meses (no incluido) de antigüedad
     	}else if (value <= beforeOneMonth && value > beforeTwoMonths) {
       		row[i].getElementsByTagName("td")[1].style.color = "green";
     // Si la fecha de publicación tiene más de dos meses (incluido) de antigüedad
     	}else{
       		row[i].getElementsByTagName("td")[1].style.color = "##778899";
     }
  }
</script>