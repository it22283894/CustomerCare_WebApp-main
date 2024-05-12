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
          <div class="menu-title"><%= session.getAttribute("email") %></div>

          <li class="item">
            <a href="<%=request.getContextPath()%>/list">Tickets</a>
          </li>


          <li class="item">
            <a href="<%=request.getContextPath()%>/rvlist">Reviews</a>
          </li>

          <li class="item">
            <a href="<%=request.getContextPath()%>/faqlist">FAQ</a>
          </li>
        </ul>
      </div>
    </nav>

    <nav class="navbar">
      <i class="fa-solid fa-bars" id="sidebar-close"></i>
    </nav>

    <main class="main">
    
    <!-- form -->
      
 	<div class="container col-md-5">
		<div class="card">
			<div class="card-body">
				<c:if test="${review != null}">
					<form action="updaterv" method="post">
				</c:if>
				<c:if test="${review == null}">
					<form action="insertrv" method="post">
				</c:if>

				<caption>
					<h2>
						<c:if test="${review != null}">
            			Edit Review
            		</c:if>
						<c:if test="${review == null}">
            			Add New Review
            		</c:if>
					</h2>
				</caption>

				<c:if test="${review != null}">
					<input type="hidden" name="id" value="<c:out value='${review.id}' />" />
				</c:if>

				<fieldset class="form-group">
					<label>Name</label> <input type="text"
						value="<c:out value='${review.name}' />" class="form-control"
						name="name" required="required">
				</fieldset>

				<fieldset class="form-group">
					<label>Review</label> <input type="text"
						value="<c:out value='${review.desc}' />" class="form-control"
						name="des">
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