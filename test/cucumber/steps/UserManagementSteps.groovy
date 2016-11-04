package cucumber.steps

import pages.ListCollectorPage
import pages.LoginAuthenticationPage
import pages.SignUpPage

import static cucumber.api.groovy.EN.*

/**
 * Created by Marcos on 19/10/2016.
 */

this.metaClass.mixin(cucumber.api.groovy.Hooks)
this.metaClass.mixin(cucumber.api.groovy.EN)

//Scenario: Realizar cadastro no sistema
Given(~/^Estou na pagina de cadastro do ResS$/) { ->
    to SignUpPage
    at SignUpPage
}

And(~/^o usuário com o cnpj "([^"]*)" ou com o usuário "([^"]*)" não estão cadastrados$/) { String cnpj, String username ->
    to ListCollectorPage
    at ListCollectorPage

    assert page.hasCnpjOrUsername(cnpj, username) == false
}

When(~/^eu informo o nome "([^"]*)" com o seu cnpj "([^"]*)", o seu endereço "([^"]*)" "([^"]*)" "([^"]*)" "([^"]*)" "([^"]*)" "([^"]*)" "([^"]*)", o tipo de usuário como "([^"]*)", e-mail "([^"]*)", usuário "([^"]*)" e senha "([^"]*)"$/) {
    String nome, cnpj, rua, numero, infoAdicional, bairro, cidade, estado, cep, tipoUsuario, email, usuario, senha ->
        to SignUpPage
        at SignUpPage

        page.fillUserDetails(nome, cnpj, rua, numero, infoAdicional, bairro, cidade,
                estado, cep, tipoUsuario, email, usuario, senha)
}

And(~/^tento cadastrar esse usuário$/) { ->
    at SignUpPage

    page.createUser()
}

Then(~/^eu posso ver a tela de login$/) { ->
    at LoginAuthenticationPage
}