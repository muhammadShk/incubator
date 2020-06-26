<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>    

<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" type="text/css" media="screen" href="https://stackpath/bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css"/>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css">
<meta charset="ISO-8859-1">
<title>Login</title>
</head>
<body>
	<form:form method="POST" action="/registration" modelAttribute="user" >
			<div class="row align-items-center justify-content-center" style="padding:50px">
				<h1 style="color: red;">${accessDenied}</h1>
			<div class="col-8 card bg-light " style="padding:30px; box-shadow: 5px 10px 8px">
			<h3>Register</h3>
				<div class="form-group">
					<form:label path="name">Name:</form:label>
					<form:input type="type" class="form-control" path="name"></form:input>
					<form:errors path="name"/>
				</div>
				<div class="form-group">
					<form:label path="email">Email: </form:label>
					<form:input type="type" class="form-control" path="email"></form:input>
					<form:errors path="email"/>
				</div>
				
				<div class="form-group">
					<form:label path="password">Password :</form:label>
					<form:input type="type" class="form-control" path="password"></form:input>
					<form:errors path="password"/>
				</div>
				
				<div class="form-group">
					<form:label path="passwordConfirmation">Password Confirmation:</form:label>
					<form:input type="type" class="form-control" path="passwordConfirmation"></form:input>
				</div>
				<button class="btn btn-dark" type="submit"> Register </button>
			</div>
		</div>
	</form:form>
	
	
	<form action="/login" method="POST">
	<div class="row align-items-center justify-content-center" style="padding:50px">
		<div class="col-8 card bg-light" style="padding:30px; box-shadow: 5px 10px 8px">
		<h3>Login</h3>
		
		<p>${loginError}</p>
		<div class="form-group">
			<div class="input-group flex-nowrap">
			  <div class="input-group-prepend">
			    <span class="input-group-text" id="addon-wrapping">Email</span>
			  </div>
			  <input name="email" type="text" class="form-control" placeholder="Enter you email" aria-label="Username" aria-describedby="addon-wrapping">
			</div>
		</div>
		<div class="form-group">
			<div class="input-group flex-nowrap">
			  <div class="input-group-prepend">
			    <span class="input-group-text" id="addon-wrapping">Password</span>
			  </div>
			  <input name="password" type="text" class="form-control" placeholder="Enter your password" aria-label="Username" aria-describedby="addon-wrapping">
			</div>
		</div>
		<button class="btn btn-dark" type="submit"> Login </button>
		</div>
	</div>
	</form>

</body>
</html>