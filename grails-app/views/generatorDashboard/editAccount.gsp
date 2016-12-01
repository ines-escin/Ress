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
    <link rel="stylesheet" type="text/css" href="../css/ioview.css">
    <style type="text/css">

    </style>
</head>

<body>
<div class="content">
    <div id="wrapper2" class="div-edit">
        <h1>Editar Conta</h1>

        <g:if test="${flash.error}">
            <div class="edit-message">${flash.error}</div>
        </g:if>

        <g:form controller="generatorDashboard" action="saveUserChanges">
            <fieldset>
                <label for="name" class="label">Nome</label>
                <input placeholder="Nome" type='text' name='name' id='name' value="${user.name}"/>
                <label for="email" class="label">E-mail</label>
                <input placeholder="E-mail" type='email' name='email' id='email' value="${user.email}"/>
                <label for="cnpj" class="label">CNPJ</label>
                <input placeholder="CNPJ" type='text' name='cnpj' id='cnpj' value="${user.cnpj}"/>

                <label for="street" class="label">Rua</label>
                <input placeholder="Rua" type='text' name='street' id='street' value="${address.street}"/>
                <label for="streetNumber" class="label">Número</label>
                <input placeholder="Número" type='text' name='streetNumber' id='streetNumber' value="${address.streetNumber}"/>
                <label for="neighborhood" class="label">Bairro</label>
                <input placeholder="Bairro" type='text' name='neighborhood' id='neighborhood' value="${address.neighborhood}"/>
                <label for="city" class="label">Cidade</label>
                <input placeholder="Cidade" type='text' name='city' id='city' value="${address.city}"/>
                <label for="state" class="label">Estado</label>
                <input placeholder="Estado" type='text' name='state' id='state' value="${address.state}"/>
                <label for="cep" class="label">CEP</label>
                <input placeholder="CEP" type='text' name='cep' id='cep' value="${address.cep}"/>
                <label for="additionalInfo" class="label">Informações adicionais</label>
                <input placeholder="Informações adicionais" type='text' name='additionalInfo' id='additionalInfo' value="${address.additionalInfo}"/>

                <label for="typeUser" class="label">Informações adicionais</label>
                <input placeholder="Tipo de Usuário" type='text' name='typeUser' id='typeUser' value="${user.typeUser}" readonly/>
                <label for="username" class="label">Nome de usuário</label>
                <input placeholder="Usuário" type='text' name='username' id='username' value="${user.username}"/>
                <label for="password" class="label">Senha</label>
                <input placeholder="Senha" type='password' name='password' id='password'/>

                <div>
                    <input type="submit" class="button" id="btnConfirm" value="Confirmar"/>
                    <g:link controller="generatorDashboard" action="accountConfig" class="button"> Cancelar </g:link>
                </div>
            </fieldset>
        </g:form>
    </div>
</div>

</body>
</html>