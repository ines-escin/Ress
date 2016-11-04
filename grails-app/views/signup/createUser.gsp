<html>
<head>
    <meta name='layout' content='main'/>
    <title>ResS - Cadastro</title>
    <link rel="stylesheet" type="text/css" href="../css/signup.css"/>
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
                    <g:link controller="home" action="list">Empresas Coletoras</g:link>
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
    <g:if test='${message}'>
        <div class='login_message'>${message}</div>
    </g:if>

    <g:form controller="signUp" action="saveUser" method="POST">
    %{--<form action='${postUrl}' method='POST' id='loginForm' class='cssform' autocomplete='off'>--}%
        <input placeholder="Nome" type='text' class='text_' name='j_name' id='name'/>
        <input placeholder="E-mail" type='email' class='text_' name='j_email' id='email'/>
        <input placeholder="CNPJ" type='text' class='text_' name='j_cnpj' id='cnpj'/>
        <p>Endereço:</p>
        <input placeholder="Rua" type='text' class='text_' name='j_street' id='street'/>
        <input placeholder="Número" type='text' class='text_' name='j_streetNumber' id='streetNumber'/>
        <input placeholder="Bairro" type='text' class='text_' name='j_neighborhood' id='neighborhood'/>
        <input placeholder="Cidade" type='text' class='text_' name='j_city' id='city'/>
        <input placeholder="Estado" type='text' class='text_' name='j_state' id='state'/>
        <input placeholder="CEP" type='text' class='text_' name='j_cep' id='cep'/>
        <input placeholder="Informações Adicionais" type='text' class='text_' name='j_additionalInfo' id='additionalInfo'/>
        <p>Usuário:</p>
        <input placeholder="Tipo de Usuário" type='text' class='text_' name='j_typeUser' id='typeUser'/>
        <input placeholder="Usuário" type='text' class='text_' name='j_username' id='username'/>
        <input placeholder="Senha" type='password' class='text_' name='j_password' id='password'/>
        %{--<input type='submit' id="criar" value='Salvar'/>--}%
        <g:submitButton name="ok" value="Salvar"/>
    %{--</form>--}%
    </g:form>
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
