<!DOCTYPE html>
<html lang="en">

<head>

<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta name="description" content="">
<meta http-equiv="X-UA-Compatible" content="IE=9" />
<title>Classroom Hero</title>
<meta title="description"
	content="Classroom Hero is a point reward system used by teachers at Strawberry Elementary School to motivate students and imrpove education">

<link rel="shortcut icon" href="${pageContext.request.contextPath}/assets/images/ch_logo.png" type="image/icon">

<link rel="stylesheet"
	href="assets/bootstrap/dist/css/bootstrap.min.css">
<link rel="stylesheet"
	href="assets/bootstrap/dist/css/font-awesome.min.css" type="text/css">

<!-- Custom CSS -->
<link href="assets/css/agency.css" rel="stylesheet" type="text/css">

<!-- Custom Fonts -->
<link href="http://fonts.googleapis.com/css?family=Montserrat:400,700"
	rel="stylesheet" type="text/css">
<link href='http://fonts.googleapis.com/css?family=Kaushan+Script'
	rel='stylesheet' type='text/css'>
<link
	href='http://fonts.googleapis.com/css?family=Droid+Serif:400,700,400italic,700italic'
	rel='stylesheet' type='text/css'>
<link
	href='http://fonts.googleapis.com/css?family=Roboto+Slab:400,100,300,700'
	rel='stylesheet' type='text/css'>

<!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
<!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
<!--[if lt IE 9]>
        <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
        <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->

<script src="https://apis.google.com/js/platform.js" async defer></script>

<script>
	(function(i, s, o, g, r, a, m) {
		i['GoogleAnalyticsObject'] = r;
		i[r] = i[r] || function() {
			(i[r].q = i[r].q || []).push(arguments)
		}, i[r].l = 1 * new Date();
		a = s.createElement(o), m = s.getElementsByTagName(o)[0];
		a.async = 1;
		a.src = g;
		m.parentNode.insertBefore(a, m)
	})(window, document, 'script', '//www.google-analytics.com/analytics.js',
			'ga');

	ga('create', 'UA-53944345-1', 'auto');
	ga('send', 'pageview');
</script>

</head>

<body id="page-top" class="index">

	<div id="fb-root"></div>
	<script>
		(function(d, s, id) {
			var js, fjs = d.getElementsByTagName(s)[0];
			if (d.getElementById(id))
				return;
			js = d.createElement(s);
			js.id = id;
			js.src = "//connect.facebook.net/en_US/sdk.js#xfbml=1&version=v2.0";
			fjs.parentNode.insertBefore(js, fjs);
		}(document, 'script', 'facebook-jssdk'));
	</script>

	<!-- Navigation -->
	<nav class="navbar navbar-default navbar-fixed-top">
		<div class="container">
			<!-- Brand and toggle get grouped for better mobile display -->
			<div class="navbar-header page-scroll">
				<button type="button" class="navbar-toggle" data-toggle="collapse"
					data-target="#bs-example-navbar-collapse-1">
					<span class="sr-only">Toggle navigation</span> <span
						class="icon-bar"></span> <span class="icon-bar"></span> <span
						class="icon-bar"></span>
				</button>
				<span> <a class="navbar-brand page-scroll" href="#page-top"><img
						id="logo" src="assets/images/ch_logo.png" alt="" />Classroom Hero</a>
				</span>
			</div>

			<!-- Collect the nav links, forms, and other content for toggling -->
			<div class="collapse navbar-collapse"
				id="bs-example-navbar-collapse-1">
				<ul class="nav navbar-nav navbar-right">
					<li class="hidden"><a href="#page-top"></a></li>
					<li><a class="page-scroll" href="#services">Tutorial</a></li>
					<li><a class="page-scroll" href="#community">Community</a></li>
					<li><a class="page-scroll" href="#portfolio">The Team</a></li>
					<li><a class="page-scroll" href="#contact">Contact</a></li>
					<li><a class="page-scroll" href="login.html">Sign In</a></li>
				</ul>
			</div>
			<!-- /.navbar-collapse -->
		</div>
		<!-- /.container-fluid -->
	</nav>

	<!-- Header -->
	<header>
		<div class="container">
			<div class="intro-text">
				<h1>Classroom Hero is a fun and interactive token economy</h1>
				<br> <br>
				<h5>
					This is not your ordinary reward system. With the magic of <a href="http://beta.snowshoestamp.com/">SnowShoe
						Stamps</a>, Classroom Hero instantly creates powerful teacher-student
					interactions.
				</h5>
				<br> <a href="signup.html" class="page-scroll btn btn-xl">Sign
					Up</a>
			</div>
		</div>
	</header>

	<!-- Services Section -->
	<section id="services">
		<div class="container">
			<div class="row">
				<div class="col-lg-12 text-center">
					<h3 class="section-subheading">Classroom Hero is an app used
						by teachers. It is available for both Android and iOS.</h3>

					<h2 class="section-heading">How to use Classroom Hero</h2>
				</div>
			</div>
			<div class="row text-center">
				<div class="col-md-4">
					<iframe src="https://www.youtube.com/v/yOO31PwUpOs" width="380"
						height="300" frameborder="5" scrolling="no" webkitAllowFullScreen
						mozallowfullscreen allowFullScreen></iframe>
					<h4 class="service-heading">Reward Points</h4>
					<p class="text-muted">Have your students simply stamp our
						reward screen, and they are instantly rewarded a point for an
						action that you as the teacher create.</p>
				</div>
				<div class="col-md-4">
					<iframe src="https://www.youtube.com/v/g0gtK-tmDE0" width="380"
						height="300" frameborder="5" scrolling="no" webkitAllowFullScreen
						mozallowfullscreen allowFullScreen></iframe>
					<h4 class="service-heading">Sell Items and Privileges</h4>
					<p class="text-muted">Have your students stamp the marketplace
						screen to exchange their points for items and privileges created
						by you!</p>
				</div>
				<div class="col-md-4">
					<iframe src="https://www.youtube.com/v/gisLhagdeDg" width="380"
						height="300" frameborder="5" scrolling="no" webkitAllowFullScreen
						mozallowfullscreen allowFullScreen></iframe>
					<h4 class="service-heading">Classroom Jar</h4>
					<p class="text-muted">Give your entire class a long term goal!
						When you notice your class is on task, touch the teacher stamp to
						the screen to instantly reward your whole class! When the Jar is
						full, celebrate with some pizza! Or anything you'd like!</p>
				</div>
			</div>
		</div>
	</section>


	<!-- Community Grid Section -->
	<section id="community" class="bg-light-gray">
		<div class="container" style="text-align: center;">

			<h3 class="section-heading">What teachers think about Classroom
				Hero</h3>
			<br />
			<h6 class="tagline">"Classroom Hero improved my students'
				behavior the day we started using it!" - Lauren Raffi</h6>
			<br />
			<h6 class="tagline">"I have so much fun using Classroom Hero and
				the kids love it!" - Carole Lane</h6>
			<br />
			<h6 class="tagline">"I started using the points today and the
				kids LOVE it! I have almost every hand up for every question!" -
				Jamie Hoyer</h6>
			<br />
			<h6 class="tagline">"GAME ON!" - Lori Rowden</h6>


			<br>
			<h3 class="section-heading">What students think about Classroom
				Hero</h3>
			<br>
			<div class="row text-center">
				<div class="col-md-6">
					<iframe src="https://www.youtube.com/v/bJ4EFh7oWJc" width="380"
						height="300" frameborder="3" scrolling="no" webkitAllowFullScreen
						mozallowfullscreen allowFullScreen></iframe>
				</div>
				<div class="col-md-6">
					<iframe src="https://www.youtube.com/v/RX2e2b4o6fk" width="380"
						height="300" frameborder="3" scrolling="no" webkitAllowFullScreen
						mozallowfullscreen allowFullScreen></iframe>
				</div>
			</div>



		</div>
	</section>


	<!-- Portfolio Grid Section -->
	<section id="portfolio" class="bg-light-gray">
		<div class="container">

			<h3 style="float: center;">The Team</h3>

			<br>

			<div>
				<p style="float: left;">
					<img src="assets/images/kody.jpg" height="200px" width="200px"
						border="1px" style="padding-right: 15px;">
				</p>
				<p">
				<h5 style="color: #436EEE;">Kody Anderson - Founder and CEO</h5>
				<br>
				<br> Kody invented Classroom Hero to address the problem of
				motivation in school. He leads strategy, fundraising, customer
				relations, and team-building for Classroom Hero. Before Classroom
				Hero, Kody received one Associates degree in Natural Science and one
				in Anthropology from the Santa Rosa Junior College.
				</p>
			</div>

			<div style="clear: left;">
				<p style="float: left;">
					<img src="assets/images/e.jpg" height="250px" width="200px"
						border="1px" style="padding-right: 15px;">
				</p>
				<p style="padding-left: 15px;">
				<h5 style="color: #436EEE;">Eric Horvat - Co-Founder and CTO</h5>
				<br>
				<br> Eric co-founded Classroom Hero with Kody and leads
				development for the application and website. Prior to Classroom
				Hero, Eric studied Computer Science at Southern Oregon University.
				He later began attending the junior college in his hometown, Santa
				Rosa, CA. This is when Kody and Eric began collaborating on this
				project. His passion is creating impactful software that improves
				education!
				</p>
			</div>

			<div style="clear: left;">
				<p style="float: left;">
					<img src="assets/images/jaash.jpg" height="200px" width="200px"
						border="1px" style="padding-right: 15px;">
				</p>
				<p style="padding-left: 15px;">
				<h5 style="color: #436EEE;">Josh Nussbaum - Co-Founder and
					Project Manager</h5>
				<br>
				<br> Josh leads iOS development and helps with application and
				web development. He's currently completing his Computer Science BS
				at University of California, Santa Cruz. His passion is making works
				of art that teachers, students, and parents can enjoy.
				</p>
			</div>
		</div>
	</section>


	<section id="contact">
		<div class="container">
			<div class="row">
				<div class="col-lg-12 text-center">
					<h2 class="section-heading">Contact Us</h2>
					<h3 class="section-subheading text-muted">Questions, concerns,
						or just want to chat? Please contact us!</h3>
				</div>
			</div>
			<div class="row">
				<div class="col-lg-12">
					<form name="sentMessage" id="contactForm"
						method="get"
						enctype="text/plain" novalidate>
						<div class="row">
							<div class="col-md-6">
								<div class="form-group">
									<input type="text" class="form-control"
										placeholder="Your Name *" id="name" required
										data-validation-required-message="Please enter your name.">
									<p class="help-block text-danger"></p>
								</div>
								<div class="form-group">
									<input type="email" class="form-control"
										placeholder="Your Email *" id="email" required
										data-validation-required-message="Please enter your email address.">
									<p class="help-block text-danger"></p>
								</div>
							</div>
							<div class="col-md-6">
								<div class="form-group">
									<textarea class="form-control" placeholder="Your Message *"
										id="message" required
										data-validation-required-message="Please enter a message."></textarea>
									<p class="help-block text-danger"></p>
								</div>
							</div>
							<div class="clearfix"></div>
							<div class="col-lg-12 text-center">
								<div id="success"></div>
								<button type="submit" class="btn btn-xl">Send Message</button>
							</div>
						</div>
					</form>
				</div>
			</div>
		</div>
	</section>

	<footer>
		<div class="container">
			<div class="row">
				<div class="col-md-4">
					<span class="copyright">Copyright &copy;
						www.classroom-hero.com 2014</span>
				</div>
				<div class="col-md-4">
					<ul class="list-inline social-buttons">
						<li><div class="g-plusone" data-annotation="inline"
								data-width="200"></div></li>
						<li><div class="fb-like"
								data-href="https://www.classroom-hero.com" data-width="200"
								data-layout="standard" data-action="like" data-show-faces="true"
								data-share="true"></div></li>
					</ul>
				</div>
				<div class="col-md-4">
					<ul class="list-inline quicklinks">
						<li><a href="privacypolicy.html">Privacy Policy</a></li>
						<li><a href="#">Terms of Use</a></li>
					</ul>
				</div>
			</div>
		</div>
	</footer>



	<script
		src="http://ajax.googleapis.com/ajax/libs/jquery/1.10.2/jquery.min.js"></script>
	<script
		src="http://netdna.bootstrapcdn.com/bootstrap/3.0.0/js/bootstrap.min.js"></script>

	<!-- Plugin JavaScript -->
	<script
		src="http://cdnjs.cloudflare.com/ajax/libs/jquery-easing/1.3/jquery.easing.min.js"></script>
	<script src="assets/js/classie.js"></script>
	<script src="assets/js/cbpAnimatedHeader.js"></script>

	<!-- Contact Form JavaScript -->
	<script src="assets/js/jqBootstrapValidation.js"></script>
	<script src="assets/js/contact_me.js"></script>

	<!-- Custom Theme JavaScript -->
	<script src="assets/js/agency.js"></script>

</body>

</html>
