<%--
  Created by IntelliJ IDEA.
  User: danielmaida
  Date: 08/10/15
  Time: 12:33
--%>

<%@ page contentType="text/html;charset=UTF-8" %>

<html>

<head>

    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>ResS - Dashboard</title>

    <!-- Bootstrap Core CSS -->
    <link href="../css/generatorDashboard/bootstrap.min.css" rel="stylesheet">

    <!-- Custom CSS -->
    <link href="../css/generatorDashboard/simple-sidebar.css" rel="stylesheet">

    <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>

    <![endif]-->
    <g:layoutHead/>
</head>

<body>

<div id="wrapper">

    <!-- Sidebar -->
    <div id="sidebar-wrapper">
        <ul class="sidebar-nav">
            <li class="sidebar-brand">
                <g:link controller="collectorDashboard" action="index">Painel de controle</g:link>
            </li>
            <li>
                <g:link controller="collectorDashboard" action="index">Coletas em aberto</g:link>
            </li>
            <li>
                <g:link controller="collectorDashboard" action="collectionHistory">Histórico de coletas</g:link>
            </li>
            <li>
                <g:link controller="collectorDashboard" action="generatorList">Geradores de resíduo</g:link>
            </li>
            <li>
                <g:link controller="collectorDashboard" action="accountConfig">Configurações de conta</g:link>
            </li>
            <li id="maps">
                <g:link controller="collectorDashboard" action="maps">Mapas</g:link>
            </li>
            <li id= "logout">
                <g:link controller="logout" action="index">Sair</g:link>
            </li>
        </ul>
    </div>
    <!-- /#sidebar-wrapper -->

    <!-- Page Content -->
    <g:layoutBody/>
    <!-- /#page-content-wrapper -->

</div>
<!-- /#wrapper -->

<!-- jQuery -->
<script src="../js/generatorDashboard/jquery.js"></script>

<!-- Bootstrap Core JavaScript -->
<script src="../js/generatorDashboard/bootstrap.min.js"></script>

<!-- Menu Toggle Script -->
<script>
    $("#menu-toggle").click(function (e) {
        e.preventDefault();
        $("#wrapper").toggleClass("toggled");
    });
</script>

</body>

</html>
