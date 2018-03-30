<!DOCTYPE html>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html lang="en">
<head>

<link rel="stylesheet" type="text/css"
	href="webjars/bootstrap/3.3.7/css/bootstrap.min.css" />

<c:url value="/css/main.css" var="jstlCss" />
<link href="${jstlCss}" rel="stylesheet" />

</head>
<body>

	<nav class="navbar navbar-inverse">
		<div class="container">
			<div class="navbar-header">
				<a class="navbar-brand" href="/">Route Mining</a>
			</div>
			<div id="navbar" class="collapse navbar-collapse">
				<ul class="nav navbar-nav">
					<li class="active"><a href="/">Home</a></li>
				</ul>
			</div>
		</div>
	</nav>
	<table id="reportTop">
		<tr>
			<td>Budget:</td>
			<td><c:out value="${reportForm.budget}" /></td>
		</tr>
		<tr>
			<td>Price Per Piece:</td>
			<td><c:out value="${reportForm.price}" /></td>
		</tr>
		<tr>
			<td>Total Deliveries:</td>
			<td><c:out value="${reportForm.totalDeliveries}" /></td>
		</tr>
		<tr>
			<td>Total Cost:</td>
			<td><c:out value="${reportForm.totalCost}" /></td>
		</tr>
	</table>

	<table id="homeTable">
		<thead class="fixedHeader">
			<tr>
				<th>Zip Code</th>
				<th>Avg House Value</th>
				<th>Household Income</th>
				<th>Median Age</th>
				<th>Number of Businesses</th>
				<th>Number of Employees</th>
				<th>Population</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="zipCode" items="${reportForm.zipCodes}" varStatus="loop">
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
		</tbody>
	</table>

	<table id="homeTable">
		<thead class="fixedHeader">
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
		</thead>
		<tbody>
			<c:forEach var="route" items="${reportForm.routes}" varStatus="loop">
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
		</tbody>
	</table>

	<script type="text/javascript"
		src="webjars/bootstrap/3.3.7/js/bootstrap.min.js">
	</script>

</body>

<style>
html, body {
	height: 100%;
}

#reportTop {
	width: 30%;
    margin-left: auto;
    margin-right: auto;
}

#homeTable {
	font-family: "Trebuchet MS", Arial, Helvetica, sans-serif;
	border-collapse: collapse;
	width: 100%;
}

#homeTable td, #homeTable th {
	border: 1px solid #ddd;
	padding: 8px;
}

#homeTable tr:nth-child(even) {
	background-color: #f2f2f2;
}

#homeTable tr:hover {
	background-color: #ddd;
}

#homeTable th {
	padding-top: 12px;
	padding-bottom: 12px;
	text-align: left;
	background-color: #333366;
	color: white;
}
</style>

</html>