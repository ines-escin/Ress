<html>
<head>
	<meta name='layout' content='main'/>
	<title>ResS - Login</title>
	<link rel="stylesheet" type="text/css" href="../css/login.css"/>
	<g:javascript plugin="jquery"/>
	<g:javascript plugin="jquery-ui"/>

</head>

<body>
<nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">
	<div class="container">
		<!-- Brand and toggle get grouped for better mobile display -->
		<div class="navbar-header">
			<button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1">
				<span class="sr-only">Toggle navigation</span>
				<span class="icon-bar"></span>
				<span class="icon-bar"></span>
				<span class="icon-bar"></span>
			</button>
			<a class="navbar-brand" href="/">Home</a>
		</div>
		<!-- Collect the nav links, forms, and other content for toggling -->
		<div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
			<ul class="nav navbar-nav">
				<li>
					<g:link controller="home" action="login">Login</g:link>
				</li>
				<li>
					<a href="#services">Sobre</a>
				</li>
			</ul>
		</div>
		<!-- /.navbar-collapse -->
	</div>
	<!-- /.container -->
</nav>
<div class="body"></div>
<div class="grad"></div>
<div class="header">
	<div>Re<span>sS</span></div>
</div>
<br>
<div class='login'>
		<g:if test='${flash.message}'>
			<div class='login_message'>${flash.message}</div>
		</g:if>
		<form action='${postUrl}' method='POST' id='loginForm' class='cssform' autocomplete='off'>
			<input placeholder="username" type='text' class='text_' name='j_username' id='username'/>
			<input placeholder="password" type='password' class='text_' name='j_password' id='password'/>
			<input type='submit' id="submit" value='Login'/>
		</form>
	</div>
<script type='text/javascript'>
	<!--
	(function() {
		document.forms['loginForm'].elements['j_username'].focus();
	})();
	// -->
</script>
</body>
</html>
