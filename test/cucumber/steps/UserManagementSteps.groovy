package cucumber.steps

import br.ufpe.cin.ines.ress.PickupRequest
import br.ufpe.cin.ines.ress.SignUpController
import br.ufpe.cin.ines.ress.User
import br.ufpe.cin.ines.ress.UserRole
import br.ufpe.cin.ines.ress.residuecollector.CollectorDashboardController
import br.ufpe.cin.ines.ress.residuegenerator.GeneratorDashboardController
import pages.CollectorAccountConfigPage
import pages.CollectorDashboardPage
import pages.EditCollectorPage
import pages.ListUsersPage
import pages.LoginAuthenticationPage
import pages.SignUpPage
import steps.ResidueGeneratorTestAuxilar

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
    at SignUpPage

    page.createDefaultUserCnpjTypeUserUsername("13.654.033/0001-05", "Empresa Coletora", "username", "teste@teste.com")
    page.createUser()

    at LoginAuthenticationPage

    to ListUsersPage
    at ListUsersPage

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
    cleanUserUsername()
    cleanUserRuteste()
}

def cleanUserUsername(){
    User u = User.findByUsername("username")
    UserRole ur = UserRole.findByUser(u)
    ur.delete(flush: true)
    u.delete(flush: true)
}

def cleanUserRuteste(){
    User u = User.findByUsername("ruteste")
    UserRole ur = UserRole.findByUser(u)
    ur.delete(flush: true)
    u.delete(flush: true)
}


//Scenario: Cadastro de mesmo cnpj para diferentes tipos de usuário
Given(~'^o sistema tem armazenado um usuário do tipo "([^"]*)" com o cnpj "([^"]*)"$') { String tipoUsuario, String cnpj ->
    to SignUpPage
    at SignUpPage

    page.createDefaultUserCnpjTypeUserUsername(cnpj, tipoUsuario, "username", "teste@teste.com")
    page.createUser()

    at LoginAuthenticationPage

    to ListUsersPage
    at ListUsersPage

    assert page.hasCnpjAndTypeUser(cnpj, tipoUsuario)
}

And(~'^estou na página de cadastro do ResS$') { ->
    to SignUpPage
    at SignUpPage
}

When(~'^eu tento cadastrar um usuário do tipo "([^"]*)" com o cnpj "([^"]*)" e login "([^"]*)"$') {
    String tipoUsuario, String cnpj, String login ->

        at SignUpPage

        page.createDefaultUserCnpjTypeUserUsername(cnpj, tipoUsuario, login, "teste2@teste.com")//ruteste
        page.createUser()
}

Then(~'^eu vejo a mesma página de cadastro do ResS$') { ->
    at SignUpPage
}
And(~'^eu posso ver uma mensagem avisando que existe uma empresa coletora com o cnpj "([^"]*)"$') { String cnpj ->
    at SignUpPage

    assert page.messageError()
    cleanUserUsername()
}


//Scenario: Alterar informações de usuário e informar um cnpj já cadastrado
Given(~'^estou logado no sistema como o usuário de tipo "([^"]*)" com cnpj "([^"]*)"$') { String tipoUsuario, String cnpj ->
    to SignUpPage
    at SignUpPage

    page.createDefaultUserCnpjTypeUserUsername(cnpj, tipoUsuario, "username", "teste@gmail.com")
    page.createUser()

    at LoginAuthenticationPage
}
And(~'^o usuário com cnpj "([^"]*)" já está cadastrado$') { String cnpj ->
    to SignUpPage
    at SignUpPage

    page.createDefaultUserCnpjTypeUserUsername(cnpj, "Empresa Coletora", "ruteste", "teste2@teste.com")
    page.createUser()

    at LoginAuthenticationPage

    to ListUsersPage
    at ListUsersPage

    assert page.hasCnpj(cnpj)
}
And(~'^estou na tela de alterar informações$') { ->
    to CollectorDashboardPage
    at CollectorDashboardPage
    page.configAccount()

    at EditCollectorPage
    page.editUser()
}
When(~'^eu altero o cnpj "([^"]*)" para "([^"]*)"$') { String cnpj1, String cnpj2 ->
    at CollectorAccountConfigPage

    page.fillCollectorCnpj(cnpj2)
    page.fillCollectorPassword("teste")
    page.editUser()
}
Then(~'^eu posso ver os mesmos dados que eu via antes da alteração do usuário com cnpj "([^"]*)"$') { String cnpj ->
    at CollectorAccountConfigPage

    assert page.cnpjVerify(cnpj)

    cleanUserUsername()
    cleanUserRuteste()
}



////CONTROLADOR
def createPickupRequest(collector, generator) {
    GeneratorDashboardController control = new GeneratorDashboardController()

    control.createPickupRequestConfirmedKl(collector, generator)
    control.response.reset()

    PickupRequest test = PickupRequest.findByCollector(collector)
    assert test != null
    assert test.status
}

//Scenario: Remover um usuário empresa coletora com solicitações de coleta confirmadas
Given(~/^a empresa coletora cadastrada com o cnpj "([^"]*)" tem uma única solicitação de coleta do gerador de resíduo de cnpj "([^"]*)" confirmada\.$/) { String cnpj1, String cnpj2 ->
    ResidueGeneratorTestAuxilar aux = new ResidueGeneratorTestAuxilar()
    SignUpController control = new SignUpController()

    User collector = aux.findCollector2()
    collector.setCnpj(cnpj1)
    control.save(collector)
    control.response.reset()

    User generator = aux.findGenerator("ruteste")
    generator.setCnpj(cnpj2)
    control.save(generator)
    control.response.reset()

    createPickupRequest(collector, generator)
}
When(~/^o cadastro da empresa coletora com o cnpj "([^"]*)" é deletado do sistema$/) { String cnpj ->
    CollectorDashboardController control = new CollectorDashboardController()

    User collector = User.findByCnpj(cnpj)
    assert collector != null

    control.deleteCollectorAndPickups(collector)
    control.response.reset()
}
Then(~/^a solicitação de coleta do gerador de resíduo de cnpj "([^"]*)" que fora confirmada também é deletada do sistema\.$/) { String cnpj ->
    User generator = User.findByCnpj(cnpj)
    assert generator != null

    def test = PickupRequest.findAllByGeneratorAndStatus(generator, true)
    assert test.size() == 0

    cleanUserRuteste()
}
