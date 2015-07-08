<!DOCTYPE html>
<html lang="en">
<head>
<style type="text/css">
</style>
	<meta charset="utf-8">
	<meta name="viewport"    content="width=device-width, initial-scale=1.0">
	<meta name="description" content="">
	<meta http-equiv="X-UA-Compatible" content="IE=9" />
	
	<title>Classroom Hero Teacher Resources</title>
	
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
					<li><a href="home">My Class</a></li>
					<li><a href="index.html">Home</a></li>
					<li><a href="about.html">About</a></li>
					<li><a href="contact.html">Contact</a></li>
				</ul>
			</div>
		</div>
	</div> 


	<!-- Header -->
	<header id="head">
		<div class="containerTeacherResources">
				<h1>Instructions: How do I set up my class?<h1>
				<br>
				<ul>
					<li>Start creating your class by adding students to your Students list. Clicking the edit button makes each student editable with a small edit cog. </li><br>
					<li>Next add positive actions to reward students points for their accomplishments in class. A few examples are: On task, Being patient, Helped another student, Doing quality work, Good Transition, Organization, Responsibility, Respect, Safety, Homework. Reinforce any action, accomplishment, or behavior of your choosing! </li><br>
					<li>Now set up your class marketplace by adding items, privleges, or any reinforcers of your choosing to sell to the kids for the points you give them. Popular items are: Wear a hat in class, Homework pass, Bathroom pass, Choose a P.E. game for the class, Choose a book for the class, Candy, Hugs. Asking your students what they want is also a great way to set up your rewards.</li><br> 
					<li>Finally, set up a classroom jar. Choose a reward and a goal, and use your teacher stamp to add points to the jar on the application. Typical rewards are: Pizza party, Movie day, Breakfast party, Play outside for the day. Typically, teachers try to make the goal achievable in a 1-3 month span. </li><br>
					<li>Now turn your attention to the application and SnowShoe stamps. Log into the application using your account credentials from here and follow the instructions to register your stamp. Next you'll see the welcome screen; register your students by clicking the Students button at the bottom of the welcome screen, and then by clicking the register button. Each stamp should identify one student.</li><br>
					<li>Back on the welcome screen, swipe to the left(navigate to the right) to view the reward screen. From here you reward students for the positive actions you set up. Click the Actions button to select an action, and have a student stamp the coin on the screen. Once the student stamps, their name and score will appear, and they will be rewarded a point. Stamp your teacher stamp on the coin to reward the entire class a point! </li><br>
					<li>Keep swiping left to view the class jar and then the market screen. Only your teacher stamp can add points to the class jar. Use the market page to sell the items you set up earlier. Some teachers only sell items in the first and last 5 minutes of class, and some designate a student to be in charge of selling items.</li><br>
				</ul>

		</div>
	</header>
	<!-- /Header -->

	<div class="containerResources_link text-center"><br>
		<h1 style="text-size:160%;">Articles </h1><br>
		<ul>
			<li><a onclick="captain.action('explore')" href="http://iridescentlearning.blogspot.com.au/2013/05/gamification-and-education-core.html">Read this article about Gamification in Education. </a><br> </li>
			<li><a onclick="captain.action('explore')" href="http://www.knewton.com/gamification-education/">Check out this infographic on Gamification!</a><br></li>
			<li><a onclick="captain.action('explore')" href="http://en.wikipedia.org/wiki/Gamification_of_learning">Here's the Wiki page for Gamification of learning.</a></li>
			<li><a onclick="captain.action('explore')" href="http://blog.tophat.com/4-ways-to-gamify-learning-in-your-classroom/">4 Ways to bring Gamification of Education to your Classroom</a></li>
		
		</ul>	
	</div>
	
	<!-- Highlights - jumbotron -->
	<div class="jumbotron top-space">
		<div class="videosClass">
			<h1> Videos</h1><br>
			<iframe width="560" height="315" src="//www.youtube.com/embed/LVQdyXPMHI8" frameborder="0" allowfullscreen></iframe><br>
			<iframe src="https://embed-ssl.ted.com/talks/jane_mcgonigal_gaming_can_make_a_better_world.html" width="640" height="360" frameborder="0" scrolling="no" webkitAllowFullScreen mozallowfullscreen allowFullScreen></iframe>
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
				  name: 'Visit SnowShoe Site',
					  
			  }
		  });
		  
		  captain.action('explore', {
			  entity: {
				  type: 'article',
				  name: 'Gamification of education',
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