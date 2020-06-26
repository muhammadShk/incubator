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
<title>home</title>
</head>
<body>
	<nav class="navbar navbar-expand-lg navbar-dark bg-dark navbar-collapse">
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
		<div class="row" style="padding:10px">
			<h4>Welcome, ${user.name}</h4>
		</div>
	
		<div class="row align-items-center justify-content-center" style="padding:50px">
			<table class="table">
				<thead class="thead-dark">
				    <tr>
				      <th>Idea: </th>
				      <th>Created By: </th>
				      <th>Likes </th>
				      <th>Action/Status</th>
				    </tr>
				</thead>
				<tbody>
					<c:forEach items="${allIdeas}" var="idea">
					    <tr>
					        <td><a href="/ideas/${idea.id}">${idea.name}</a></td>
					        <td>${idea.creator.name}</td>
					        <td>${idea.likedBy.size()}</td>
					        <td>
						        <c:if test="${idea.likedBy.contains(user)}">
						        	<form action="/ideas" method="post" >
										<input class="btn btn-info" type="submit" value="Dislike">
										<input name="like" type="hidden" value="false">
										<input name="idea" type="hidden" value="${idea.id}">
									</form>
						        </c:if>
						        <c:if test="${! idea.likedBy.contains(user)}">
						        	<form action="/ideas" method="post" >
										<input class="btn btn-info" type="submit" value="Like">
										<input name="like" type="hidden" value="True">
										<input name="idea" type="hidden" value="${idea.id}">
									</form>
						        </c:if>
					        </td>
					    </tr>
		    		</c:forEach>
	    		</tbody>
			</table>
		</div>
		<div class="row" style="padding:50px">
			<button class="btn btn-dark" onclick="window.location.href='/ideas/new';" >Plant an Idea</button>
		</div>
	</div>

</body>
</html>