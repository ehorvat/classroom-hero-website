<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta name="description" content="">
<meta name="author" content="Sergey Pozhilov (GetTemplate.com)">

<title>Classroom Hero</title>
<link rel="shortcut icon"
	href="${pageContext.request.contextPath}/assets/images/appicon.png"
	type="image/icon">


<link rel="stylesheet" media="screen"
	href="http://fonts.googleapis.com/css?family=Open+Sans:300,400,700">
<link rel="stylesheet"
	href="assets/bootstrap/dist/css/bootstrap.min.css">
<link rel="stylesheet"
	href="assets/bootstrap/dist/css/font-awesome.min.css">

<!-- Custom styles for our template -->
<link rel="stylesheet"
	href="assets/bootstrap/dist/css/bootstrap-theme.css" media="screen">
<link rel="stylesheet" href="assets/bootstrap/dist/css/main.css">
<!-- Jquery-validate CSS -->
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/assets/plugins/jquery-validation/demo/css/core.css">
<!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->
<!--[if lt IE 9]>
	<script src="assets/js/html5shiv.js"></script>
	<script src="assets/js/respond.min.js"></script>
	<![endif]-->

<style>
#head {
	background: #00B2EE;
	background-size: cover;
	text-align: center;
	padding-top: 70px;
	color: white;
	font-family: "Open sans, Helvetica, Arial";
	font-weight: 300;
}

.radio-margin {
	margin-right: 8px;
}

#signup input.error {
	border: 1px solid #B80000;
}

#signup input.valid {
	border: 1px solid #00C957;
}

#successMsg {
	font-size: 30px;
	visibility: hidden;
}
</style>


</head>

<body class="home">

	<div class="navbar navbar-default navbar-fixed-top headroom">
		<div class="container">
			<div class="navbar-header">
				<!-- Button for smallest screens -->
				<button type="button" class="navbar-toggle" data-toggle="collapse"
					data-target=".navbar-collapse">
					<span class="icon-bar"></span> <span class="icon-bar"></span> <span
						class="icon-bar"></span>
				</button>
				<a id="company" class="navbar-brand" href="index.html"
					style="color: #1E90FF;"><img
					style="width: 100px; height: 100px;"
					src="assets/images/ch_logo.png" /></a>
			</div>
			<div class="navbar-collapse collapse">
				<ul class="nav navbar-nav pull-right">
					<li><a href="/">Home</a></li>
					<li><a href="login.html">Sign In</a></li>

				</ul>
			</div>
			<!--/.nav-collapse -->
		</div>
	</div>
	<!-- /.navbar -->

	<!-- Header -->
	<header id="signup-head">
		<div class="container">


			<h1>Sign Up</h1>

		</div>


	</header>
	<!-- /Header -->


	<!-- Highlights - jumbotron -->
	<div class="jumbotron top-space">

		<div class="container">

			<div class="row">


				<form id="signup" class="col-md-12 col-lg-5 center" method="POST"
					style="text-align: center;">
					<p id="passMis"
						style="color: red; visibility: hidden; display: none;">Passwords
						don't match.</p>
					<p id="schoolPass"
						style="color: red; visibility: hidden; display: none;">League
						password is incorrect.</p>
					<p id="invalidEmail"
						style="color: red; visibility: hidden; display: none;">Email
						already exists.</p>
					<h2 id="signup" style="text-align: center;">Sign Up</h2>
					<button class="btn btn-primary" style="margin-bottom: 10px;"
						disabled>
						<a href="startleague"
							style="margin: 0 auto; float: none; color: white;">School
							Administrator? Click here!<br>
						<span style="color: black">Feature Coming Soon!</span>
						</a>
					</button>
					<div class="form-group">
						<input name="fname" type="text" class="form-control input-lg"
							placeholder="First Name">
					</div>
					<div class="form-group">
						<input name="lname" type="text" class="form-control input-lg"
							placeholder="Last Name">
					</div>
					<div class="form-group">
						<input name="email" type="text" class="form-control input-lg"
							placeholder="Email">
					</div>

					<div class="form-group">
						<input name="password" type="password"
							class="form-control input-lg" placeholder="Password">
					</div>
					<div class="form-group">
						<input name="confpass" type="password"
							class="form-control input-lg" placeholder="Confirm Password">
					</div>

					<div id="leagueDiv" class="form-group"
						style="display: none; visibility: hidden;">
						<div id="leagues" class="control-group ">
							<select name="school" id="school"
								class="form-control selects fields">
								<option value="">Select a League...</option>
								<c:forEach var='league' items='${leagues}' varStatus="theCount">
									<option value='${league.name},${league.visibility}'>${league.name}
										(${league.visibility})</option>
								</c:forEach>
							</select>
						</div>
					</div>

					<div id="leaguePassDiv" class="form-group"
						style="display: none; visibility: hidden;">
						<input style="border: 1px solid gold;" name="leaguePass"
							type="password" class="form-control input-lg"
							placeholder="League Password">
					</div>


					<div id="schoolNameDiv" class="control-group ">
						<select id="schoolName" name="schoolName"
							class="form-control selects fields">
							<option value="">Select School...</option>
								<c:forEach var='school' items='${schools}' varStatus="theCount">
									<option value='${school.name}'>${school.name}</option>
								</c:forEach>
						</select>
					</div>




					<div class="control-group" style="padding-bottom: 40px;">
						<label class="control-label" for="radios" style="display: inline; padding-right: 25px;">Grade:</label>
						<label class="radio-inline radio-margin" for="radios-0">
						 	<input class="radio-inline radio-margin" name="radios" id="radios-0" value="1" checked="checked" type="radio">
						 	1
						</label>
						<label class="radio-inline radio-margin" for="radios-0">
						 	<input class="radio-inline radio-margin" name="radios" id="radios-0" value="2" checked="checked" type="radio">
						 	2
						</label>
							 <label class="radio-inline radio-margin" for="radios-0">
						 	<input class="radio-inline radio-margin" name="radios" id="radios-0" value="3" checked="checked" type="radio">
						 	3
						</label>
						 <label class="radio-inline radio-margin" for="radios-0">
						 	<input class="radio-inline radio-margin" name="radios" id="radios-0" value="4" checked="checked" type="radio">
						 	4
						</label>
						<label class="radio-inline radio-margin" for="radios-1">
							<input name="radios" id="radios-1" value="5" type="radio">
							5
						</label> <label class="radio-inline radio-margin" for="radios-2">
							<input name="radios" id="radios-2" value="6" type="radio">
							6
						</label>

					</div>


					<div class="row">
						<input type="submit" name="submit" class="btn btn-success btn-lg"
							value="Finish" />
						<button id="toggleField" name="joinLeague" class="btn btn-danger"
							onclick="spawnLeagueField()" type="button" disabled>
							Join a League<br>
							<span style="color: black">Feature Coming Soon!</span>
						</button>

					</div>



				</form>


			</div>
			<p id="successMsg">
				Congratulations! Your account was successfully created. Click <a
					href="login.html">here</a> to sign in.
			</p>
		</div>
	</div>
	<!-- /Highlights -->



	<!-- Social links. @TODO: replace by link/instructions in template -->
	<section id="social">
		<div class="container">
			<div class="wrapper clearfix">
				<!-- AddThis Button BEGIN -->
				<div class="addthis_toolbox addthis_default_style">
					<a class="addthis_button_facebook_like"
						fb:like:layout="button_count"></a> <a class="addthis_button_tweet"></a>
					<a class="addthis_button_linkedin_counter"></a> <a
						class="addthis_button_google_plusone" g:plusone:size="medium"></a>
				</div>
				<!-- AddThis Button END -->
			</div>
		</div>
	</section>
	<!-- /social links -->








	<!-- JavaScript libs are placed at the end of the document so the pages load faster -->
	<!-- Jquery Library -->
	<script type="text/javascript"
		src="assets/libraries/jquery-ui/js/jquery-1.10.2.js"></script>
	<script type="text/javascript"
		src="assets/plugins/jquery-validation/dist/jquery.validate.min.js"></script>
	<script type="text/javascript"
		src="assets/plugins/jquery-validation/dist/additional-methods.js"></script>
	<script
		src="http://netdna.bootstrapcdn.com/bootstrap/3.0.0/js/bootstrap.min.js"></script>
	<script src="assets/js/headroom.min.js"></script>
	<script src="assets/js/jQuery.headroom.min.js"></script>
	<script src="assets/js/template.js"></script>
	<script src="assets/customJS/teacherForm.js"></script>



</body>
</html>