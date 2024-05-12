<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
<link rel="icon" href="assets/images/ffavicon.ico">
<title>Help Center</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-alpha.6/css/bootstrap.min.css" integrity="sha384-rwoIResjU2yc3z8GV/NPeZWAv56rSmLldC3R/AZzGRnGxQQKnKkoFVhFQhNUwEyJ" crossorigin="anonymous">
<link href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css" rel="stylesheet">
<link href="https://fonts.googleapis.com/css?family=Rubik:400,400i,500,500i,700,700i" rel="stylesheet">
<link href="assets/css/theme.css" rel="stylesheet">


    

    
    
    
    <link href="https://fonts.googleapis.com/css?family=Roboto:300,400,500,700" rel="stylesheet">
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.5.0/css/all.css" integrity="sha384-B4dIYHKNBt8Bc12p+WXckhzcICo0wtJAoU8YZTY5qE0Id1GSseTk6S+L3BlXeVIU" crossorigin="anonymous">
    <link rel="stylesheet" href="assets/css/form.css">
    

</head>
<body class="layout-default">


<header class="navbar navbar-toggleable-md navbar-light bg-white fixed-top mediumnavigation">
<button class="navbar-toggler navbar-toggler-right" type="button" data-toggle="collapse" data-target="#navbarsWow" aria-controls="navbarsWow" aria-expanded="false" aria-label="Toggle navigation">
<span class="navbar-toggler-icon"></span>
</button>
<div class="container">
	
	<a class="navbar-brand" href="index.html">
	<img src="assets/images/logo.png">
	</a>
	
	
	<div class="collapse navbar-collapse" id="navbarsWow">
		
		<ul class="navbar-nav ml-auto">
			<li class="nav-item">
			<a class="nav-link" href="home.jsp">Home</a>

            <li class="nav-item">
			<a class="nav-link" href="<%=request.getContextPath()%>/userticketlist">My Tickets</a>
			</li>
			<li class="nav-item">
			<a class="nav-link" href="<%=request.getContextPath()%>/userorderlist">Orders</a>
			</li>
			<li class="nav-item">
			<a class="nav-link" href="contact.html">Contact</a>
			</li>
			<li class="nav-item">
			<a target="_blank" class="nav-link highlight" href="#"><%= session.getAttribute("email") %></a>
			</li>
		</ul>
	
	</div>
</div>
</header>


	    <br><br><br><br>

	<section class="intro1">
	
	    <br><br><br><br>
    <main class="main">
    

      
<div class="container col-md-55">
		<div class="card1">
			<div class="card-body1">
				<c:if test="${ticket != null}">
					<form action="updateuserticket" method="post">
				</c:if>
				<c:if test="${ticket == null}">
					<form action="insertuserticket" method="post">
				</c:if>

				<caption>
					<h2>
						<c:if test="${ticket != null}">
            			Edit Ticket
            		</c:if>
						<c:if test="${ticket == null}">
            			Add New Ticket
            		</c:if>
					</h2>
				</caption>

				<c:if test="${ticket != null}">
					<input type="hidden" name="id" value="<c:out value='${ticket.id}' />" />
				</c:if>

				<fieldset class="form-group">
					<label>Subject</label> <input type="text"
						value="<c:out value='${ticket.name}' />" class="form-control"
						name="name" required="required">
				</fieldset>

				<fieldset class="form-group">
					 <input type="hidden"
						value="<c:out value='${ticket.email}' />" class="form-control"
						name="email">
				</fieldset>

				<fieldset class="form-group">
					<input type="hidden"
						value="<c:out value='${ticket.subject}' />" class="form-control"
						name="subject">
				</fieldset>
				
				<fieldset class="form-group">
					<label>Issue</label> <textarea name="issue" class="form-control">
						<c:out value='${ticket.issue}' />
						</textarea>
				</fieldset>

				<button type="submit" class="btn btn-success">Save</button>
				</form>
			</div>
		</div>
	</div>
	
      
      
      
    </main>

	</section>