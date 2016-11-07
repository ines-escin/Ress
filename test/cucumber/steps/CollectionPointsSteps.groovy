package cucumber.steps


import br.ufpe.cin.ines.ress.PickupRequest
import br.ufpe.cin.ines.ress.SignUpController
import br.ufpe.cin.ines.ress.residuecollector.CollectorDashboardController
import br.ufpe.cin.ines.ress.residuegenerator.GeneratorDashboardController
import pages.CollectionPointsPage
import pages.CollectorDashboardPage
import pages.GeneratorDashboardPage
import pages.MapsPage
import pages.PickupRequestPage

import static cucumber.api.groovy.EN.*;


/**
 * Created by Leonardo on 20/10/2016.
 */


Given(~/^o restaurante de login "([^"]*)" possui uma coleta pendente$/) { String login ->

    at SignUpPage

    page.createDefaultUser("cnpj", "Gerador de Resíduo", login)

    to GeneratorDashboardPage
    at GeneratorDashboardPage
    page.pickupRequest()

    at PickupRequestPage
    page.fillResidueAmountDef()
    page.submitButtonClick()
}

And(~/^Estou na página de visualização de mapa do ResS$/) { ->
    to MapsPage
    at MapsPage
}

Given(~/^Estou na página de visualização de mapas do ResS$/) { ->
    to MapsPage
    at MapsPage
}

When(~/^eu solicito a visualização das coletas$/) { ->
    to MapsPage
    at MapsPage
    page.clickCollectionPoints()
}

Then(~/^eu vejo a localização do restaurante de login "r1" em um mapa$/) { String arg1 ->
    at CollectionPointsPage
    page.hasmap()
}

And(~/^não existem locais com coletas pendentes$/) { ->
    to CollectorDashboardPage
    at CollectorDashboardPage
    page.hasNoPickUp()
}

Then(~/^eu vejo uma mensagem sinalizando que não há restaurantes com coletas pendentes$/) { ->
    at MapsPage
    assert page.hasNoCollectionPoints()
}

Given(~/^o local de login "([^"]*)" possui coletas pendentes$/) { String arg ->

    def signUpController = new SignUpController()
    signUpController.createDefaultGeneratorUser(arg)

    def generatorController  = new GeneratorDashboardController()
    generatorController.saveDefaultPickUp(arg)

    def pickups = PickupRequest.findAllByStatus(false).findAll {it -> it.generator.username == arg}

    assert !pickups.isEmpty()
}

def address
When(~/^eu solicito os enderecos dos locais com coletas pendentes$/) { ->
    def controlador = new CollectorDashboardController()
    address = controlador.collectionPointsAddress()
}

Then(~/^o sistema retorna os enderecos do locais de login "([^"]*)"$/) { String arg ->

    assert address.collect{at -> at.user.username}.contains(arg)
    PickupRequest.executeUpdate('delete from PickupRequest')
}

Given(~/^não existem coletas pendentes$/) { ->
    assert PickupRequest.findAllByStatus(false).empty
}

Then(~/^o sistema nada retorna$/) { ->
    assert address.empty
}


