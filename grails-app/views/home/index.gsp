

<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html lang="en">

<head>

    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>Coopere UFPE</title>

    <!-- Bootstrap Core CSS -->
    <link href="css/homepage/bootstrap.min.css" rel="stylesheet">

    <!-- Custom CSS -->
    <link href="css/homepage/one-page-wonder.css" rel="stylesheet">

    <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>

    <![endif]-->

</head>

<body>

<!-- Navigation -->
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
            <a class="navbar-brand" href="#">Home</a>
        </div>
        <!-- Collect the nav links, forms, and other content for toggling -->
        <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
            <ul class="nav navbar-nav">
                <li id="login">
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

<!-- Full Width Image Header -->
<header class="header-image">
    <div class="headline" >
        <div class="container">
            <h1>Coopere UFPE</h1>
        </div>
    </div>
</header>

<!-- Page Content -->
<div class="container">

    <hr class="featurette-divider">

    <!-- First Featurette -->
    <div class="featurette" id="about">
        <img class="featurette-image img-circle img-responsive pull-right" src="${resource(dir: 'images', file: 'onde_jogar.jpg')}">
        <h2 class="featurette-heading">Reciclagem é a
            <span class="text-muted">solução</span>
        </h2>
        <p class="lead">Atitudes corretas para um mundo melhor.</p>
    </div>

    <hr class="featurette-divider">

    <!-- Second Featurette -->
    <div class="featurette" id="services">
        <img class="featurette-image img-circle img-responsive pull-left" src="${resource(dir: 'images', file: 'reciclagem.jpg')}">
        <h2 class="featurette-heading">Natureza
            <span class="text-muted">sustentável.</span>
        </h2>
        <p class="lead">Coopere UFPE é um Sistema de Gerenciamento de Coleta de Óleo de Fritura nas cantinas e restaurantes da UFPE, onde o óleo coletado será destinado para reciclagem.</p>
    </div>

    <hr class="featurette-divider">

    <!-- Footer -->
    <footer>
        <div class="row">
            <div class="col-lg-12">
                <p>Copyright &copy; INES-CIN</p>
            </div>
        </div>
    </footer>

</div>
<!-- /.container -->

<!-- jQuery -->
<script src="js/jquery.js"></script>

<!-- Bootstrap Core JavaScript -->
<script src="js/bootstrap.min.js"></script>

</body>

</html>
