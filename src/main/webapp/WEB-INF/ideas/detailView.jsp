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
<title>details</title>
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

	<div class="container" style="padding:10px" >
	
		<div class="row align-items-center justify-content-center" style="padding:50px">
			<div class="card">
			<h3 class="card-header">${idea.name}</h3>
			<div class="row card-body">
				<div class="col-9" >
					<h4>Created by: "${idea.creator.name}" </h4>
					<h4>Users who liked your idea: </h4>
					<ul>
						<c:forEach items="${idea.likedBy}" var="likedBy">
	    					<li>${likedBy.name}</li>
						</c:forEach>
					</ul>
				</div>
			</div>
		</div>
		</div>
		<div class="row align-items-center justify-content-center" style="padding:50px">
			<button class="btn btn-dark" onclick="window.location.href='/ideas/${idea.id}/edit';" >Edit</button>
		</div>
	</div>

</body>
</html>