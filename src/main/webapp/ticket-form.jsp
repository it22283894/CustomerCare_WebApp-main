<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<!-- Coding By CodingNepal - codingnepalweb.com -->
<html lang="en">
  <head>
    <meta charset="UTF-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>Admin Panel</title>
    <link rel="stylesheet" href="assets/css/dashboardstyles.css" />
    
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.2.1/css/all.min.css" />
    <link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
	integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
	crossorigin="anonymous">
    
    
  </head>
  <body>
    <nav class="sidebar">
      <a href="#" class="logo">HelpCenter</a>

      <div class="menu-content">
        <ul class="menu-items">
          <div class="menu-title">Your menu title</div>

          <li class="item">
            <a href="#">Tickets</a>
          </li>


          <li class="item">
            <a href="#">Reviews</a>
          </li>

          <li class="item">
            <a href="#">Your third link</a>
          </li>
        </ul>
      </div>
    </nav>

    <nav class="navbar">
      <i class="fa-solid fa-bars" id="sidebar-close"></i>
    </nav>

    <main class="main">
    
    <!-- table eka-->
      
 	<div class="container col-md-5">
		<div class="card">
			<div class="card-body">
				<c:if test="${ticket != null}">
					<form action="update" method="post">
				</c:if>
				<c:if test="${ticket == null}">
					<form action="insert" method="post">
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
					<label>Name</label> <input type="text"
						value="<c:out value='${ticket.name}' />" class="form-control"
						name="name" required="required">
				</fieldset>

				<fieldset class="form-group">
					<label>Email</label> <input type="text"
						value="<c:out value='${ticket.email}' />" class="form-control"
						name="email">
				</fieldset>

				<fieldset class="form-group">
					<input type="hidden"
						value="<c:out value='${ticket.subject}' />" class="form-control"
						name="subject">
				</fieldset>
				
				<fieldset class="form-group">
					<label>Issue</label> <input type="text"
						value="<c:out value='${ticket.issue}' />" class="form-control"
						name="issue">
				</fieldset>

				<button type="submit" class="btn btn-success">Save</button>
				</form>
			</div>
		</div>
	</div>
      
      
      
    </main>

    <script src="assets/js/dashboard.js"></script>
  </body>
</html>