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

<form:form method="post" modelAttribute="zipCodeForm" action="processForm">
	<div class="container">
		<div class="buttonsTable">
			
				<table>
					<tr>
						<td align="right">Zip Code:</td>
						<td align="left"><form:input path="zipCode" value="${zipCode}" /></td>
						<td align="right">Total Budget:</td>
						<td align="left"><form:input path="budget" value="${budget}" /></td>
					</tr>
					<tr>
						<td align="right">Distance:</td>
						<td align="left"><form:input path="distance" value="${distance}" /></td>
						<td align="right">Single Mail Piece Price:</td>
						<td align="left"><form:input path="price" value="${price}" /></td>
					</tr>
					<tr>
						<td align="right">House Value Range:</td>
						<td align="left"><form:input path="houseValueLower" value="${houseValueLower}" /> to <form:input path="houseValueUpper" value="${houseValueUpper}" /></td>
						<td align="right">Income Range:</td>
						<td align="left"><form:input path="incomeLower" value="${incomeLower}" /> to <form:input path="incomeUpper" value="${incomeUpper}" /></td>
					</tr>
				</table>
		</div>

		<c:choose>
			<c:when test="${!empty zipCodeForm.errors}">
				<div id="errors"><c:out value="${zipCodeForm.errors}" /></div>
			</c:when>
		</c:choose>

		<table>
		<tr>
			<td style="padding: 5px 0px 5px 0px" align="right" colspan="4">
				<input type="submit" name="autoSelect" value="Auto Select" />
				<input type="submit" value="Submit" />
			</td>
		</tr>
		<tr>
		<c:choose>
			<c:when test="${!empty zipCodeForm.zipCodes}">
			<td>
				<div class="tableContainerZipCodes">
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
									<td><form:checkbox path="selectedZipCodes" value="${zipCode.zipCode}"/></td>
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
				<td><input type="submit" name="rightArrow" value="&gt;&gt;" /></td>
		<td>
				<div class="tableContainerRoutes">
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
								<tr>
									<td><form:checkbox path="selectedRoutes" value="${route.key}"/></td>
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
		</tr>
		<tr>
			<td style="padding: 5px 0px" colspan="3" align="right"><input type="submit" name="generateReport" value="Generate Report" /></td>
		</tr>
		</table>
		<br /> <br />
		
	</div>
	<br />
	<br />
	
	</form:form>
	
	<script type="text/javascript"
		src="webjars/bootstrap/3.3.7/js/bootstrap.min.js">
	</script>

</body>

<style>
html, body {
	height: 100%;
}

div.tableContainerZipCodes {
	clear: both;
	border: 1px solid #963;
	overflow: auto;
	height: 500px;
	width: 400px;
}

div.tableContainerRoutes {
	clear: both;
	border: 1px solid #963;
	overflow: auto;
	height: 500px;
	width: 850px;
}

div.buttonsTable td {
	padding: 2px;
}

#errors {
	color: red;
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