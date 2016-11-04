<%--
  Created by IntelliJ IDEA.
  User: Marcos
  Date: 31/10/2016
  Time: 10:03
--%>

<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <meta name='layout' content='main'/>
    <title>ResS - Usuários</title>
    <link rel="stylesheet" type="text/css" href="../css/signup.css"/>
    <link rel="stylesheet" type="text/css" href="../css/table-data.css">

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
                <li id="lista">
                    <g:link controller="home" action="list">Usuários Cadastrados</g:link>
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
%{--<div class="body"></div>--}%
%{--<div class="grad"></div>--}%
%{--<div class="header">--}%
    %{--<div>Re<span>sS</span></div>--}%
%{--</div>--}%
<br>
%{--<div class='lista'>--}%
    <table>
        <thead>
        <tr>
            <th>Nome</th>
            <th>Email</th>
            <th>CNPJ</th>
            <th>Usuário</th>
            <th>Tipo de Usuário</th>
        </tr>
        </thead>
        <tbody>
        <g:each var="user" in="${collectors}">
            <tr>
                <td>${user.name}</td>

                <td>${user.email}</td>

                <td>${user.cnpj}</td>

                <td>${user.username}</td>

                <td>${user.typeUser}</td>
            </tr>
        </g:each>
        </tbody>
    </table>
%{--</div>--}%
<script type='text/javascript'>
    <!--
    (function() {
        document.forms['loginForm'].elements['j_username'].focus();
    })();
    // -->
</script>
</body>
</html>
