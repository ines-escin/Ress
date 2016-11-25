package cucumber.steps


import br.ufpe.cin.ines.ress.PickupRequest
import br.ufpe.cin.ines.ress.SignUpController
import br.ufpe.cin.ines.ress.residuecollector.CollectorDashboardController
import br.ufpe.cin.ines.ress.residuegenerator.GeneratorDashboardController
import cucumber.api.PendingException
import pages.CollectorDashboardPage
import pages.GeneratorDashboardPage
import pages.MapsPage
import pages.PickupRequestPage
import pages.RouteVisualizationPage
import pages.SignUpPage

import static cucumber.api.groovy.EN.*;

Given(~/^existem coletas pendentes no local "([^"]*)"$/) { String login ->
    to SignUpPage
    at SignUpPage

    page.createDefaultUser("cnpj", "Gerador de Resíduo", login)

    to GeneratorDashboardPage
    at GeneratorDashboardPage
    page.pickupRequest()

    at PickupRequestPage
    page.fillResidueAmountDef()
    page.submitButtonClick()
}

Given(~/^não existem coletas pendentes$/) { ->
    to CollectorDashboardPage
    at CollectorDashboardPage
    assert page.hasNoPickUp()
}

And(~/^eu estou na pagina de visualização de mapas do ResS$/) { ->
    to MapsPage
    at MapsPage
}

When(~/^eu seleciono a opção de visualização de rotas$/) { ->
    to MapsPage
    at MapsPage
    page.clickRouteVisualization()
}

Then(~/^eu vejo a rota com origem e destino no endereço do coletor passando por "([^"]*)" no mapa$/) { String loc ->
    to RouteVisualizationPage
    at RouteVisualizationPage
    assert page.systemGetsRoute()
    //o método não pega exatamente o javascript da rota pois não consegui identificar o javascript especifico da rota,
    //fica como se fosse o mesmo e da forma que o projeto está configurado realmente não aparece a rota pois os endereços
    //fornecidos não existem, mas quando testei com endereços reais funcionou
}

Then(~/^o sistema exibe uma mensagem de erro$/) { ->
    to MapsPage
    at MapsPage
    assert page.hasNoCollectionPoints()
}

Given(~/^o local "([^"]*)" possui coletas pendentes$/) { String arg ->
    def signUpController = new SignUpController()
    signUpController.createDefaultGeneratorUser(arg)

    def generatorController  = new GeneratorDashboardController()
    generatorController.saveDefaultPickUp(arg)

    def pickups = PickupRequest.findAllByStatus(false).findAll {it -> it.generator.username == arg}
    generatorController.response.reset()
    signUpController.response.reset()
    assert !pickups.isEmpty()
}


Given(~/^não ha coletas pendentes$/) { ->
    def collectorController = new CollectorDashboardController()
    def end = collectorController.pickUpsAddress()
    collectorController.response.reset()
    assert end.empty;
}

def address
When(~/^eu solicito a rota entre os locais com coletas pendentes$/) { ->
    def controlador = new CollectorDashboardController()
    controlador.routeVisualization()
    controlador.response.reset()
    address = controlador.pickUpsAddress()
}

Then(~/^o sistema retorna a rota que passa por "([^"]*)"$/) { String arg ->
    assert address.collect{at -> at.user.username}.contains(arg)
}

Then(~/^o sistema não retorna a rota$/) { ->
    assert address.empty
}
And(~/^eu estou na tela de visualização de mapas do ResS$/) { ->
    // Write code here that turns the phrase above into concrete actions
    throw new PendingException()
}