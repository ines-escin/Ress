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


//Scenario: Cadastro de mesmo cnpj para diferentes tipos de usuário
Given(~'^o sistema tem armazenado um usuário do tipo "([^"]*)" com o cnpj "([^"]*)"$') { String tipoUsuario, String cnpj ->
    to ListCollectorPage
    at ListCollectorPage

    assert page.hasCnpjAndTypeUser(cnpj, tipoUsuario)
}

And(~'^estou na página de cadastro do ResS$') { ->
    to SignUpPage
    at SignUpPage
}

When(~'^eu tento cadastrar um usuário do tipo "([^"]*)" com o cnpj "([^"]*)" e login "([^"]*)"$') {
    String tipoUsuario, String cnpj, String login ->

        at SignUpPage

        page.createDefaultUser(cnpj, tipoUsuario, login)
        page.createUser()
}

Then(~'^eu vejo a mesma página de cadastro do ResS$') { ->
    at SignUpPage
}
And(~'^eu posso ver uma mensagem avisando que existe uma empresa coletora com o cnpj "([^"]*)"$') { String cnpj ->
    at SignUpPage

    assert page.messageError(cnpj)
}
