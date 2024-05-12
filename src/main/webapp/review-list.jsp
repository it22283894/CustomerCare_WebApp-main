<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>

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
          <div class="menu-title"><%= session.getAttribute("adminemail") %></div>

          <li class="item">
            <a href="<%=request.getContextPath()%>/list">Tickets</a>
          </li>


          <li class="item">
            <a href="<%=request.getContextPath()%>/rvlist">Reviews</a>
          </li>

          <li class="item">
            <a href="<%=request.getContextPath()%>/faqlist">FAQ</a>
          </li>
                      <li class="item">
            <a href="<%=request.getContextPath()%>/orderlist">Orders</a>
          </li>
        </ul>
      </div>
    </nav>

    <nav class="navbar">
      <i class="fa-solid fa-bars" id="sidebar-close"></i>
    </nav>

    <main class="main">
    
    <!-- table -->
      
      	<div class="row">
		

		<div class="container">
		<br><br><br><br>
			<h3 class="text-center">Reviews</h3>
			<hr>
			<br>
			<table class="table table-bordered">
				<thead>
					<tr>
						<th>ID</th>
						<th>Name</th>
						<th>Review</th>
						<th>Action</th>
					</tr>
				</thead>
				<tbody>
					
					<c:forEach var="review" items="${listReview}">

						<tr>
							<td><c:out value="${review.id}" /></td>
							<td><c:out value="${review.name}" /></td>
							<td><c:out value="${review.desc}" /></td>
							<td><a href="editrv?id=<c:out value='${review.id}' />">Edit</a>
								&nbsp;&nbsp;&nbsp;&nbsp; <a
								href="deleterv?id=<c:out value='${review.id}' />">Delete</a></td>
						</tr>
					</c:forEach>
					<!-- } -->
				</tbody>

			</table>
		</div>
	</div>
      
      
      
    </main>

    <script src="assets/js/dashboard.js"></script>
  </body>
</html>