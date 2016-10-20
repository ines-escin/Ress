package cucumber.steps

import br.ufpe.cin.ines.ress.User
import pages.LoginAuthenticationPage
import pages.SignUpPage

import static cucumber.api.groovy.EN.*

/**
 * Created by Marcos on 19/10/2016.
 */

this.metaClass.mixin(cucumber.api.groovy.Hooks)
this.metaClass.mixin(cucumber.api.groovy.EN)

Given(~/^Estou na pagina de cadastro do ResS$/) { ->
    to SignUpPage
    at SignUpPage
}

And(~/^o usuário com o cnpj "([^"]*)" e com o usuário "([^"]*)" não estão cadastrados$/) { String cnpj, String username ->
    assert User.findByCnpj(cnpj) == null;
    assert User.findByUsername(username) == null;
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
Then(~/^eu posso ver uma mensagem de confirmação de cadastro e a tela de login$/) { ->
    //mensagem de confirmacao

    to LoginAuthenticationPage
    at LoginAuthenticationPage
}