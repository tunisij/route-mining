<!DOCTYPE html>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
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

		<div class="starter-template">
			<h1>Spring Boot Web JSP Example</h1>
			<h2>Zip Code: ${zipCode}</h2>
			<h2>Avg Household Value: ${avgHouseholdValue}</h2>
			<h2>Household Income: ${householdIncome}</h2>
			<h2>Median Age: ${medianAge}</h2>
			<h2>Number of Businesses: ${numBusiness}</h2>
			<h2>Number of Employees: ${numEmployee}</h2>
			<h2>Population: ${population}</h2>
		</div>

		<div>
			<form action="listPageParameters.jsp" method="post">
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
		</div>

	</div>

	<script type="text/javascript"
		src="webjars/bootstrap/3.3.7/js/bootstrap.min.js"></script>

</body>

</html>