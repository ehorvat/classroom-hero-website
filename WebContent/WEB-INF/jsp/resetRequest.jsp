
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

#loginform input.error {
	border: 1px solid #B80000;
}

#loginform input.valid {
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
					
					<li><a href="index2.html">Home</a></li>
					<li><a href="signup.html">Sign Up</a></li>
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

			
			
			
			<form id="resetForm" class="col-md-12 col-lg-5 center" method="POST"
				action="http://www.classroom-hero.com/reset"
				style="text-align: center;" style="display:none;">
				<h3>Reset Password</h3>
				<br>
				<h6 style="text-align: center; color: red;">${resetFailed}</h6>
				<div class="form-group">
					<input name="email" type="text" class="form-control input-lg"
						placeholder="Email">
				</div>

				<a href="login.html" class="btn btn-success btn-lg">Back</a>

				<input type="submit" name="submit" class="btn btn-success btn-lg"
					value="Reset Password" /> <br>

			</form>
			
			
			

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
	

	<!-- Bootstrap core CSS -->


	<script type="text/javascript">
		var payload;
		jQuery(function($) {

			$("#resetForm").validate({
				onkeyup : true,
				rules : {
					email : {
						required : true
					}
				},
				errorPlacement : function() {
					return false;
				}

			});

		});

	</script>


</body>
</html>