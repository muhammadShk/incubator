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
<title>Edit</title>
</head>
<body>
	<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
		  <a class="navbar-brand" href="#">Incubator</a>
		  <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
		    <span class="navbar-toggler-icon"></span>
		  </button>
		  <div class="collapse navbar-collapse" id="navbarNav">
		    <ul class="navbar-nav">
		      <li class="nav-item active">
		        <a class="nav-link" href="/ideas">Home <span class="sr-only">(current)</span></a>
		      </li>
		      <li class="nav-item">
		        <a class="nav-link" href="/logout">Logout</a>
		      </li>
		    </ul>
		  </div>
	</nav>

	<div class="container" style="padding:50px" >
		<div class="row align-items-center justify-content-center" style="padding:40px">
			<div class="col-6">
				<form:form action="/ideas/${idea.id}/edit" method="post" modelAttribute="idea">
					<h1>${idea.name}</h1>
			    	<div class="form-group">
				        <form:label path="name">Name: </form:label>
				        <form:errors class="form-text text-muted" path="name"/>
				        <form:input class="form-control" path="name"/>
			    	</div>
	    			<input class="btn btn-dark"type="submit" value="Update"/>
				</form:form>
				<div class="row" style="padding:15px">
					<form action="/ideas/${idea.id}/delete" method="post" >
						<input class="btn btn-danger" type="submit" value="Delete">
					</form>
				</div>
			</div>
		</div>
	</div>

</body>
</html>