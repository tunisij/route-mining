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
				<a class="navbar-brand" href="/">Route Mining</a>
			</div>
			<div id="navbar" class="collapse navbar-collapse">
				<ul class="nav navbar-nav">
					<li class="active"><a href="/">Home</a></li>
				</ul>
			</div>
		</div>
	</nav>

	<div class="container">
		<div class="buttonsTable">
			<form:form method="post" modelAttribute="zipCodeForm"
				action="processForm">
				<table>
					<tr>
						<td align="right">Zip Code:</td>
						<td align="left"><form:input path="zipCode"
								value="${zipCode}" /></td>
						<td align="right">Total Budget:</td>
						<td align="left"><form:input path="budget" value="${budget}" /></td>
					</tr>
					<tr>
						<td align="right">Distance:</td>
						<td align="left"><form:input path="distance"
								value="${distance}" /></td>
						<td align="right">Single Mail Piece Price:</td>
						<td align="left"><form:input path="price" value="${price}" /></td>
						<td><input type="submit" value="Submit" /></td>
					</tr>
				</table>
			</form:form>
		</div>

		<br /> <br />
		<c:choose>
			<c:when test="${!empty zipCodeForm.zipCodes}">
				<div class="tableContainer">
					<table id="homeTable">
						<thead class="fixedHeader">
							<tr>
								<th></th>
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
							<c:forEach var="zipCode" items="${zipCodeForm.zipCodes}" varStatus="loop">
								<tr>
									<td><input type="checkbox" name="checkboxgroup" value="c:out value='${zipCode}'"/></td>
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
				</div>
			</c:when>
		</c:choose>

		<br /> <br />

		<c:choose>
			<c:when test="${!empty zipCodeForm.routes}">
				<div class="tableContainer">
					<table id="homeTable">
						<thead class="fixedHeader">
							<tr>
								<th></th>
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
							<c:forEach var="route" items="${zipCodeForm.routes}" varStatus="loop">
								<tr onclick="selectRow(${loop.index})">
									<td><input type="checkbox" name="checkboxgroup" value="c:out value='${route}'"/></td>
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
				</div>
			</c:when>
		</c:choose>
	</div>
	<br />
	<br />

	<script type="text/javascript"
		src="webjars/bootstrap/3.3.7/js/bootstrap.min.js">
	</script>

</body>

<style>
html, body {
	height: 100%;
}

div.tableContainer {
	clear: both;
	border: 1px solid #963;
	height: 285px;
	overflow: auto;
	width: 756px;
}

div.buttonsTable td {
	padding: 2px 10px 2px 10px;
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