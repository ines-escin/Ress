<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 19/10/2015
  Time: 15:32
--%>

<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <meta name="layout" content="generatorLayout">
    <title></title>
    <link rel="stylesheet" type="text/css" href="../css/table-data.css">
    <link rel="stylesheet" type="text/css" href="../css/generatorDashboard/button.css"/>
    <style type="text/css">
    </style>
</head>

<body>
<div id="wrapper">
    <h1>Editar Conta</h1>

    <g:form controller="collectorDashboard" action="saveUserChanges">
        <fieldset>
            <div class="control">
                <label for="username" class="label">Nome de usuário</label>
                <input name="username" type="text" id="username" class="required">
            </div>

            <div class="control">
                <label for="email" class="label">Email</label>
                <input name="email" type="email" id="email" class="required">
            </div>

            <div class="control">
                <label for="password" class="label">Senha</label>
                <input name="password" id="password" type="password">
            </div>

            <div>
                <input type="submit" class="btn btn-first" value="Confirmar"/>
                <g:link controller="collectorDashboard" action="accountConfig"> <button class="btn btn-first"> Cancelar </button> </g:link>
            </div>
        </fieldset>
    </g:form>
</div>

</body>
</html>