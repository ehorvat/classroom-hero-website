<!DOCTYPE html>
<html lang="en">
<head>
<style type="text/css">
</style>
	<meta charset="utf-8">
	<meta name="viewport"    content="width=device-width, initial-scale=1.0">
	<meta name="description" content="">
	<meta http-equiv="X-UA-Compatible" content="IE=9" />
	
	<title>Classroom Hero About Page</title>
	
	<link rel="shortcut icon" href="${pageContext.request.contextPath}/assets/images/appicon.png" type="image/icon">
	
	<link rel="stylesheet" media="screen" href="http://fonts.googleapis.com/css?family=Open+Sans:300,400,700">
	<link rel="stylesheet" href="assets/bootstrap/dist/css/bootstrap.min.css">
	<link rel="stylesheet" href="assets/bootstrap/dist/css/font-awesome.min.css">
	<link href="assets/customCSS/main.css" rel="stylesheet" type="text/css" />

	<!-- Custom styles for our template -->
	<link rel="stylesheet" href="assets/bootstrap/dist/css/bootstrap-theme.css" media="screen" >
	<link rel="stylesheet" href="assets/bootstrap/dist/css/main.css">
	<script src="https://apis.google.com/js/platform.js" async defer></script>

	
</head>
<body class="home">
	<div id="fb-root"></div>
	<script>(function(d, s, id) {
	  var js, fjs = d.getElementsByTagName(s)[0];
	  if (d.getElementById(id)) return;
	  js = d.createElement(s); js.id = id;
	  js.src = "//connect.facebook.net/en_US/sdk.js#xfbml=1&version=v2.0";
	  fjs.parentNode.insertBefore(js, fjs);
	}(document, 'script', 'facebook-jssdk'));</script>
	
	<!-- Fixed navbar -->
	<div class="navbar navbar-default navbar-fixed-top headroom">
		<div class="container">
			<div class="navbar-header">
				<!-- Button for smallest screens -->
				<button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse"><span class="icon-bar"></span> <span class="icon-bar"></span> <span class="icon-bar"></span> </button>
				<a id="company" class="navbar-brand" href="index.html"><img  src="assets/images/appicon.png"/></a>
				
				<a class="navbar-brand" href="index.html"> <img src="assets/images/classroom_hero_text.png" alt="Classroom Hero"/></a>
				
			</div>
			<div class="navbar-collapse collapse">
				<ul class="nav navbar-nav pull-right">
					<li><a href="index.html">Home</a></li>
					<li><a href="login.html">Sign In</a></li>
					<li><a href="about.html">About</a></li>
					<li><a href="contact.html">Contact</a></li>
				</ul>
			</div>
		</div>
	</div> 


	<!-- Header -->
	<header id="head">
		<div class="containerAbout">
				<h1>Classroom Hero is the most efficient token economy in the world.</h1>
				<br>
				<ul>
					<li>Combined with the magic of <a onclick="captain.action('snowshoesite')" href="http://beta.snowshoestamp.com/">SnowShoe Stamps</a>, Classroom Hero technology motivates kids to excel in school.</li><br>
					<li>Classroom Hero does this by enforcing positive actions in a meaningful way, and letting kids decide how to spend the points they earn.</li><br>
				</ul>

		</div>
	</header>
	<!-- /Header -->

	<div class="containerAbout_link text-center"><br>
		<a onclick="captain.action('snowshoesite') "href="http://www.youtube.com/watch?v=RIaV5qPkqB8">Watch this informational video to learn more about SnowShoe Stamps!</a><br>
		<div class="g-plusone" data-annotation="inline" data-width="200"></div>
		<div class="fb-like" data-href="https://www.classroom-hero.com" data-width="200" data-layout="standard" data-action="like" data-show-faces="true" data-share="true"></div>
	</div>
	
	<!-- Highlights - jumbotron -->
	<div class="jumbotron top-space">
		<div class="aboutTeam">
			<h1> The Team </h1><hr>
			<h2> Kody Anderson - CEO </h2>
			<h3> Kody invented Classroom Hero to address the problem of motivation in school. He leads strategy, fundraising, customer relations, and team-building for Classroom Hero. Before Classroom Hero, Kody received his Associates Degree in business from the Santa Rosa Junior College.  </h3><br>
			<h2> Eric Horvat - CTO</h2>
			<h3> Eric co-founded Classroom Hero with Kody and leads development for the application and website. Prior to Classroom Hero, Eric studied Computer Science at Southern Oregon University. His passion is creating impactful software that improves education! </h3><br>
			<h2> Josh Nussbaum - Lead Developer</h2>
			<h3> Josh leads iOS development and helps with application and web development. He's currently completing his Computer Science BS at University of California, Santa Cruz. His passion is making works of art that teachers, students, and parents can enjoy.</h3><br>
			<hr>
		</div>
	</div>
	
	
		
<div id='cptup-ready'></div>
<script data-cfasync='false' type='text/javascript'>
    window.captain = {up: function(fn) { captain.topics.push(fn) }, topics: []};
    /* Add your settings here: */
    captain.up({
        api_key: '53f4134673873a635a000003'
    });
    
    captain.up(function() {
		  // Report an action to Captain Up
		  captain.action('snowshoe', {
		    entity: {
		      type: 'snowshoe',
		      name: 'SnowShoe Video',
		    }
		  });
		  captain.action('snowshoesite', {
			  entity: {
				  type: 'snowshoesite',
				  name: 'Visit SnowShoe Site'
					  
			  }
		  });
    });
</script>
<script data-cfasync='false' type='text/javascript'>
    (function() {
        var cpt = document.createElement('script'); cpt.type = 'text/javascript'; cpt.async = true;
        cpt.src = 'http' + (location.protocol == 'https:' ? 's' : '') + '://captainup.com/assets/embed.js';
        (document.getElementsByTagName('head')[0] || document.getElementsByTagName('body')[0]).appendChild(cpt);
     })();
</script>
	
	<!-- Social links. @TODO: replace by link/instructions in template -->
	<section id="social">
		<div class="container">
			<div class="wrapper clearfix">
				<!-- AddThis Button BEGIN -->
				<div class="addthis_toolbox addthis_default_style">
				<a class="addthis_button_facebook_like" fb:like:layout="button_count"></a>
				<a class="addthis_button_tweet"></a>
				<a class="addthis_button_linkedin_counter"></a>
				<a class="addthis_button_google_plusone" g:plusone:size="medium"></a>
				</div>
				<!-- AddThis Button END -->
			</div>
		</div>
	</section>
	<!-- /social links -->

	<!-- JavaScript libs are placed at the end of the document so the pages load faster -->
	<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.10.2/jquery.min.js"></script>
	<script src="http://netdna.bootstrapcdn.com/bootstrap/3.0.0/js/bootstrap.min.js"></script>
	<script src="assets/swfobject.js"></script>
	
	
	
	
	<script>
  (function(i,s,o,g,r,a,m){i['GoogleAnalyticsObject']=r;i[r]=i[r]||function(){
  (i[r].q=i[r].q||[]).push(arguments)},i[r].l=1*new Date();a=s.createElement(o),
  m=s.getElementsByTagName(o)[0];a.async=1;a.src=g;m.parentNode.insertBefore(a,m)
  })(window,document,'script','//www.google-analytics.com/analytics.js','ga');

  ga('create', 'UA-53944345-1', 'auto');
  ga('send', 'pageview');

	</script>
	
</body>
</html>


