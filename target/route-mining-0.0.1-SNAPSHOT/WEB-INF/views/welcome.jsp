<!DOCTYPE html>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html lang="en">
<head>

<!-- Access the bootstrap Css like this,
		Spring boot will handle the resource mapping automcatically -->
<link rel="stylesheet" type="text/css"
	href="webjars/bootstrap/3.3.7/css/bootstrap.min.css" />

<c:url value="/css/main.css" var="jstlCss" />
<link href="${jstlCss}" rel="stylesheet" />

</head>
<body>

	<nav class="navbar navbar-inverse">
		<div class="container">
			<div class="navbar-header">
				<a class="navbar-brand" href="#">Route Mining</a>
			</div>
			<div id="navbar" class="collapse navbar-collapse">
				<ul class="nav navbar-nav">
					<li class="active"><a href="#">Home</a></li>
				</ul>
			</div>
		</div>
	</nav>

	<div class="container">
		<div>
			<form:form method="post" modelAttribute="zipCodeForm" action="processForm">
				<table>
					<tr>
						<td>Zip Code:</td>
						<td><form:input path="zipCode" value="${zipCode}"/></td>
					</tr>
					<tr>
						<td>Distance:</td>
						<td><form:input path="distance" value="${distance}"/></td>
					</tr>
				</table>
				<input type="submit" value="Submit" />
			</form:form>
			
			
			<!--
			<form action="/processForm" method="post">
				<table>
					<tr>
						<td>Zip Code:</td>
						<td><input type="text" name="zipCode" /></td>
					</tr>
					<tr>
						<td>Distance:</td>
						<td><input type="text" name="distance" /></td>
					</tr>
				</table>
				<input type="submit" value="Submit" />
			</form>
			-->
		</div>

		<table>

		</table>

		<table style="border: 1px solid;">
			<tr>
				<th>Zip Code</th>
				<th>Avg House Value</th>
				<th>Household Income</th>
				<th>Median Age</th>
				<th>Number of Businesses</th>
				<th>Number of Employees</th>
				<th>Population</th>
			</tr>
			<c:forEach var="zipCode" items="${zipCodeForm.zipCodes}">
				<tr>
					<td><c:out value="${zipCode.zipCode}" /></td>
					<td><c:out value="${zipCode.avgHouseValue}" /></td>
					<td><c:out value="${zipCode.householdIncome}" /></td>
					<td><c:out value="${zipCode.medianAge}" /></td>
					<td><c:out value="${zipCode.numBusinesses}" /></td>
					<td><c:out value="${zipCode.numEmployees}" /></td>
					<td><c:out value="${zipCode.population}" /></td>
				</tr>
			</c:forEach>
		</table>

		<table style="border: 1px solid;">
			<tr>
				<th>Zip Code</th>
				<th>Route</th>
				<th>Type of Route</th>
				<th>County Code</th>
				<th>Business Count</th>
				<th>Apartment Count</th>
				<th>PO Box Count</th>
				<th>Residential Count</th>
				<th>Total Deliveries</th>
				<th>Avg Household Income</th>
				<th>Avg Property Value</th>
			</tr>
			<c:forEach var="route" items="${zipCodeForm.routes}">
				<tr>
					<td><c:out value="${route.zipCode}" /></td>
					<td><c:out value="${route.route}" /></td>
					<td><c:out value="${route.type}" /></td>
					<td><c:out value="${route.countyCode}" /></td>
					<td><c:out value="${route.businessCount}" /></td>
					<td><c:out value="${route.apartmentCount}" /></td>
					<td><c:out value="${route.poBoxCount}" /></td>
					<td><c:out value="${route.residentialCount}" /></td>
					<td><c:out value="${route.totalDeliveries}" /></td>
					<td><c:out value="${route.avgHouseholdIncome}" /></td>
					<td><c:out value="${route.avgPropertyValue}" /></td>
				</tr>
			</c:forEach>
		</table>

	</div>

	<script type="text/javascript"
		src="webjars/bootstrap/3.3.7/js/bootstrap.min.js"></script>

</body>

</html>