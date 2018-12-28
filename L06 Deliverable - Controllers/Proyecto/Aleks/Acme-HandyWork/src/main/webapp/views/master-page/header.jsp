<%--
 * header.jsp
 *
 * Copyright (C) 2018 Universidad de Sevilla
 * 
 * The use of this project is hereby constrained to the conditions of the 
 * TDG Licence, a copy of which you may download from 
 * http://www.tdg-seville.info/License.html
 --%>

<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>
<div>
	<a href="#"><img src="images/header.png"
		alt="Acme Handy Worker Inc." height="150" width="850" /></a>
</div>

<div>
	<ul id="jMenu">
		<!-- Do not forget the "fNiv" class for the first level links !! -->
		<security:authorize access="hasRole('ADMIN')">
			<li><a class="fNiv"><spring:message
						code="master.page.administrator.warranty" /></a>
				<ul>
					<li class="arrow"></li>
					<li><a href="warranty/administrator/list.do"><spring:message
								code="master.page.administrator.warranty.show" /></a></li>
					<li><a href="warranty/administrator/create.do"><spring:message
								code="master.page.administrator.warranty.create" /></a></li>
				</ul></li>
			<li><a class="fNiv"><spring:message
						code="master.page.administrator.category" /></a>
				<ul>
					<li class="arrow"></li>
					<li><a href="category/administrator/list.do"><spring:message
								code="master.page.administrator.category.show" /></a></li>
					<li><a href="category/administrator/create.do"><spring:message
								code="master.page.administrator.category.create" /></a></li>
				</ul></li>
			<li><a class="fNiv"><spring:message
						code="master.page.administrator.systemusers" /></a>
				<ul>
					<li class="arrow"></li>
					<li><a href=""><spring:message
								code="master.page.administrator.systemusers.show" /></a></li>
					<li><a href=""><spring:message
								code="master.page.administrator.systemusers.show.ban" /></a></li>
					<li><a href="administrator/create.do"><spring:message
								code="master.page.administrator.systemusers.create" /></a></li>
								<li><a href="administrator/personal.do">Personal data</a></li>
				</ul></li>
			<li><a class="fNiv"><spring:message
						code="master.page.administrator.dashboard" /></a>
				<ul>
					<li class="arrow"></li>
					<li><a href=""><spring:message
								code="master.page.administrator.dashboard.termscomplaints" /></a></li>
					<li><a href=""><spring:message
								code="master.page.administrator.dashboard.tops" /></a></li>
					<li><a href=""><spring:message
								code="master.page.administrator.dashboard.general" /></a></li>
				</ul></li>
		</security:authorize>

		<security:authorize access="hasRole('CUSTOMER')">
			<li><a class="fNiv"><spring:message
						code="master.page.customer.fixuptask" /></a>
				<ul>
					<li class="arrow"></li>
					<li><a href="fixuptask/customer/list.do"><spring:message
								code="master.page.customer.action.1" /></a></li>
					<li><a href="fixuptask/customer/edit.do"><spring:message
								code="master.page.customer.action.2" /></a></li>
				</ul></li>
			<li><a class="fNiv"><spring:message
						code="master.page.customer.complaint" /></a>
				<ul>
					<li class="arrow"></li>
					<li><a href="complaint/customer/list.do"><spring:message
								code="master.page.customer.action.1" /></a></li>
					<li><a href="complaint/customer/create.do"><spring:message
								code="master.page.customer.action.2" /></a></li>
				</ul></li>
				<li><a class="fNiv"><spring:message
						code="master.page.customer.note" /></a>
				<ul>
					<li class="arrow"></li>
					<li><a href="note/customer/list.do"><spring:message
								code="master.page.customer.action.1" /></a></li>
					<li><a href="note/customer/create.do"><spring:message
								code="master.page.customer.action.2" /></a></li>
				</ul></li>
				</security:authorize>

		<security:authorize access="hasRole('HANDY_WORKER')">
			<li><a class="fNiv"><spring:message
						code="master.page.handyworker.fixuptask" /></a>
				<ul>
					<li class="arrow"></li>
					<li><a href="fixuptask/handyworker/list.do"><spring:message
								code="master.page.handyworker.action.1" /></a></li>
				</ul></li>
			<li><a class="fNiv"><spring:message
						code="master.page.handyworker.application" /></a>
				<ul>
					<li class="arrow"></li>
					<li><a href="application/handyworker/list.do"><spring:message
								code="master.page.handyworker.action.1" /></a></li>
				</ul></li>
			<li><a class="fNiv"><spring:message
						code="master.page.handyworker.curriculum" /></a>
				<ul>
					<li class="arrow"></li>
					<li><a href="curriculum/handyworker/list.do"><spring:message
								code="master.page.handyworker.action.1" /></a></li>
				</ul></li>
			<li><a class="fNiv"><spring:message
						code="master.page.handyworker.tutorial" /></a>
				<ul>
					<li class="arrow"></li>
					<li><a href="tutorial/handyworker/list.do"><spring:message
								code="master.page.handyworker.action.1" /></a></li>
					<li><a href="tutorial/handyworker/edit.do"><spring:message
								code="master.page.handyworker.action.2" /></a></li>
				</ul></li>
			<li><a class="fNiv"><spring:message
						code="master.page.handyworker.endorsement" /></a>
				<ul>
					<li class="arrow"></li>
					<li><a href="endorsement/handyworker/list.do"><spring:message
								code="master.page.handyworker.action.1" /></a></li>
				</ul></li>
			<li><a class="fNiv"><spring:message
						code="master.page.handyworker.profile" /></a>
				<ul>
					<li class="arrow"></li>
					<li><a href="profile/customer/list.do"><spring:message
								code="master.page.handyworker.action.1" /></a></li>
				</ul></li>
			<li><a class="fNiv"><spring:message
						code="master.page.handyworker.message" /></a>
				<ul>
					<li class="arrow"></li>
					<li><a href="message/customer/list.do"><spring:message
								code="master.page.handyworker.action.1" /></a></li>
				</ul></li>
		</security:authorize>

		<security:authorize access="hasRole('REFEREE')">
			<li><a class="fNiv"><spring:message
						code="master.page.referee" /></a>
				<ul>
					<li class="arrow"></li>
					<li><a href="customer/action-1.do"><spring:message
								code="master.page.customer.action.1" /></a></li>
					<li><a href="customer/action-2.do"><spring:message
								code="master.page.customer.action.2" /></a></li>
				</ul></li>
		</security:authorize>

		<security:authorize access="hasRole('SPONSOR')">
			<li><a class="fNiv"><spring:message
						code="master.page.sponsor" /></a>
				<ul>
					<li class="arrow"></li>
					<li><a href="customer/action-1.do"><spring:message
								code="master.page.customer.action.1" /></a></li>
					<li><a href="customer/action-2.do"><spring:message
								code="master.page.customer.action.2" /></a></li>
				</ul></li>
		</security:authorize>

		<security:authorize access="isAnonymous()">
			<li><a class="fNiv" href="security/login.do"><spring:message
						code="master.page.login" /></a></li>
		</security:authorize>

		<security:authorize access="isAuthenticated()">
			<li><a class="fNiv"> <spring:message
						code="master.page.profile" /> (<security:authentication
						property="principal.username" />)
			</a>
				<ul>
					<li class="arrow"></li>
					<li><a href="box/list.do"><spring:message
								code="master.page.box" /></a></li>
					<li><a href="profile/action-2.do"><spring:message
								code="master.page.profile.action.2" /></a></li>
					<li><a href="profile/action-3.do"><spring:message
								code="master.page.profile.action.3" /></a></li>
					<li><a href="j_spring_security_logout"><spring:message
								code="master.page.logout" /> </a></li>
				</ul></li>
		</security:authorize>
	</ul>
</div>

<div>
	<a href="?language=en">en</a> | <a href="?language=es">es</a>
</div>

