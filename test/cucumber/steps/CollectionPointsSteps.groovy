package cucumber.steps

import br.ufpe.cin.ines.ress.Address
import br.ufpe.cin.ines.ress.PickupRequest
import br.ufpe.cin.ines.ress.SignUpController
import br.ufpe.cin.ines.ress.User
import br.ufpe.cin.ines.ress.residuecollector.CollectorDashboardController
import br.ufpe.cin.ines.ress.residuegenerator.GeneratorDashboardController
import pages.CollectionPointsPage
import pages.CollectorDashboardPage
import pages.GeneratorDashboardPage
import pages.MapsPage
import pages.PickupRequestPage
import pages.SignUpPage

import static cucumber.api.groovy.EN.*;


/**
 * Created by Leonardo on 20/10/2016.
 */


Given(~/^o restaurante de login "([^"]*)" possui uma coleta pendente de "([^"]*)" litros$/) { String login, String quantidade ->

    to SignUpPage
    at SignUpPage

    page.createDefaultUserCnpjTypeUserUsername("cnpj", "Gerador de Resíduo", login, "default@Mail.com")

    to GeneratorDashboardPage
    at GeneratorDashboardPage
    page.pickupRequest()

    at PickupRequestPage
    page.FillResidueAmount(quantidade)
    page.submitButtonClick()
}


And(~/^Estou na página de visualização de mapa do ResS$/) { ->
    to MapsPage
    at MapsPage
}

When(~/^eu solicito a visualização das coletas$/) { ->

    page.clickCollectionPoints()
}

Then(~/^eu vejo a localização do restaurante de login "([^"]*)" em um mapa$/) { String login ->
    at CollectionPointsPage
    assert page.hasmap()
}

Given(~/^não existem locais com coletas pendentes$/) { ->
    to CollectorDashboardPage
    at CollectorDashboardPage
    assert page.hasNoPickUp()
}

Then(~/^eu vejo uma mensagem sinalizando que não há restaurantes com coletas pendentes$/) { ->
    at MapsPage
    assert page.hasNoCollectionPoints()
}

Given(~/^o local de login "([^"]*)" e endereço "([^"]*)" "([^"]*)" "([^"]*)" "([^"]*)" "([^"]*)" "([^"]*)" "([^"]*)" possui uma coleta pendente$/) { String login, String rua, String numero, String infoAdicional, String bairro, String cidade, String estado, String cep ->

    def signUpController = new SignUpController()

    def local =  new User(username: login, password: "pass",  name: login, email: login+'@gmail.com',
            address: new Address(street :rua, cep: cep, city: cidade, state: estado, streetNumber: numero, neighborhood: bairro, additionalInfo: infoAdicional), enabled: true)

    //Quando integrar definir valores de cnpj e tipo de usuario
    signUpController.save(local)

    def generatorController  = new GeneratorDashboardController()
    generatorController.saveDefaultPickUp(login)

    def pickups = PickupRequest.findAllByStatus(false).findAll {it -> it.generator.username == login}

    assert !pickups.isEmpty()

    signUpController.response.reset()
    generatorController.response.reset()
}

def address
When(~/^eu solicito os enderecos dos locais com coletas pendentes$/) { ->
    def controlador = new CollectorDashboardController()
    address = controlador.collectionPointsAddress()

    controlador.response.reset()
}


Then(~/^o sistema retorna o endereco  "([^"]*)" "([^"]*)" "([^"]*)" "([^"]*)" "([^"]*)" "([^"]*)" "([^"]*)" do local de login "([^"]*)"$/) {  String rua, String numero, String infoAdicional, String bairro, String cidade, String estado, String cep, String login ->

    address =  address.findAll{it -> it.user.username == login}
    assert address[0].street == rua
    assert address[0].streetNumber == numero
    assert address[0].additionalInfo == infoAdicional
    assert address[0].neighborhood == bairro
    assert address[0].city == cidade
    assert address[0].state == estado
    assert address[0].cep == cep
}

Given(~/^não existem coletas pendentes$/) { ->
    assert hasNoPickupRequests()
}

Then(~/^nada muda na lista de coletas pendentes$/) { ->
    assert hasNoPickupRequests()
}

def hasNoPickupRequests(){
    PickupRequest.findAllByStatus(false).empty
}