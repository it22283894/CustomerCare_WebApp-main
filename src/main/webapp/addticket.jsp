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
<!-- Begin tracking codes here, including ShareThis/Analytics -->

    <title>Raise A Ticket</title>
    <link href="https://fonts.googleapis.com/css?family=Roboto:300,400,500,700" rel="stylesheet">
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.5.0/css/all.css" integrity="sha384-B4dIYHKNBt8Bc12p+WXckhzcICo0wtJAoU8YZTY5qE0Id1GSseTk6S+L3BlXeVIU" crossorigin="anonymous">
    <link rel="stylesheet" href="assets/css/form.css">
    
<!-- End tracking codes here, including ShareThis/Analytics -->
</head>
<body class="layout-default">
<!-- Begin Menu Navigation
================================================== -->
<header class="navbar navbar-toggleable-md navbar-light bg-white fixed-top mediumnavigation">
<button class="navbar-toggler navbar-toggler-right" type="button" data-toggle="collapse" data-target="#navbarsWow" aria-controls="navbarsWow" aria-expanded="false" aria-label="Toggle navigation">
<span class="navbar-toggler-icon"></span>
</button>
<div class="container">
	<!-- Begin Logo -->
	<a class="navbar-brand" href="index.html">
	<img src="assets/images/logo.png">
	</a>
	<!-- End Logo -->
	<!-- Begin Menu -->
	<div class="collapse navbar-collapse" id="navbarsWow">
		<!-- Begin Menu -->
		<ul class="navbar-nav ml-auto">
			<li class="nav-item">
			<a class="nav-link" href="home.jsp">Home</a>

            <li class="nav-item">
			<a class="nav-link" href="category.html">Category</a>
			</li>
			<li class="nav-item">
			<a class="nav-link" href="about.html">About</a>
			</li>
			<li class="nav-item">
			<a class="nav-link" href="contact.html">Contact</a>
			</li>
			<li class="nav-item">
			<a target="_blank" class="nav-link highlight" href="#">Logout</a>
			</li>
		</ul>
		<!-- End Menu -->
	</div>
</div>
</header>
<!-- End Menu Navigation
================================================== -->
<div class="site-content">
	<!-- Home Jumbotron
    ================================================== -->
	<section class="intro">
	
	
	 <div class="testbox">
      <form action="addTicket" method="post">
        <h1>Raise A Ticket</h1>
        <h4>Subject<span>*</span></h4>
        <select id="subject" name="subject">
          <option value=""></option>
          <option value="Billing">Billing</option>
          <option value="General Inquiry">General Inquiry</option>
          <option value="Technical Support">Technical Support</option>
          <option value="Orders">Orders</option>
        </select>

        <h4>Email Address<span>*</span></h4>
        <input type="text" name="email" />
        <h4>Name<span>*</span></h4>
        <input type="text" name="name"/>
        <h4>Issue</h4>
        <input type="text" name="issue"/>

        <div class="btn-block">
          <button type="submit" href="/">Send</button>
        </div>
      </form>
    </div>

	</section>
	</html>