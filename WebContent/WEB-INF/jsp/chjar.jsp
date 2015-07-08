<!DOCTYPE html>
<html lang="en">
<head>
<style type="text/css">

</style>
	<meta charset="utf-8">
	<meta name="viewport"    content="width=device-width, initial-scale=1.0">
	<meta name="description" content="">
	<meta http-equiv="X-UA-Compatible" content="IE=9" />
	
	<title>Classroom Hero Jar Page</title>
	
	<link rel="shortcut icon" href="${pageContext.request.contextPath}/assets/images/appicon.png" type="image/icon">
	
	<link rel="stylesheet" media="screen" href="http://fonts.googleapis.com/css?family=Open+Sans:300,400,700">
	<link rel="stylesheet" href="assets/bootstrap/dist/css/bootstrap.min.css">
	<link rel="stylesheet" href="assets/bootstrap/dist/css/font-awesome.min.css">
	
	<link href="assets/customCSS/main.css" rel="stylesheet" type="text/css" />

	<!-- Custom styles for our template -->
	<link rel="stylesheet" href="assets/bootstrap/dist/css/bootstrap-theme.css" media="screen" >
	<link rel="stylesheet" href="assets/bootstrap/dist/css/main.css">

	
</head>


<body class="home">
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
		<div class="containerJar">
				<h1>Classroom Hero Jar</h1><br>
				<ul>
					<li>Each point in the Classroom Hero jar signifies one positive, reinforcing interaction between a teacher and student.</li><br>
					<li>Teachers use jars with their students to set goals for the entire class. The Classroom Hero jar is a goal for every school using the system, and every school using the system will be rewarded when the jar is full.</li><br>
					<li>Once one hundred thousand teacher-student interactions occur, Classroom Hero will reward each school appropriately!</li>			
				</ul>
				
		</div>
	</header>
	
	<!-- /Header -->

	<!-- Intro -->
	<div class="container text-center">
			<div class="col-md-12 col-sm-12 col-md-12 col-lg-12 highlight">		
			<div class="jar_container2">
				<img src="assets/images/jar_full.png" style="width:30%; height:380px; z-index:2; position:absolute; clip:rect(${coinPos}px, 700px, 1200px, 0px);"/>
				<img src="assets/images/intact_jar.png" style="width:30%; height:380px; z-index:1; position:relative;"/>
				
				
				<br><br>
			</div>
		</div>
		<br> <br>
	</div>
	<!-- /Intro-->
	
	<div class="jumbotron top-space">
		<div class="aboutTeam">
			<h1> Total Points: ${coinTotal} / 100000 </h1><hr>

		</div>
	</div>
		
<div id='cptup-ready'></div>
<script data-cfasync='false' type='text/javascript'>
    window.captain = {up: function(fn) { captain.topics.push(fn) }, topics: []};
    /* Add your settings here: */
    captain.up({
        api_key: '53f4134673873a635a000003'
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