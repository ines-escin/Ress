package cucumber.steps


import br.ufpe.cin.ines.ress.PickupRequest
import br.ufpe.cin.ines.ress.SignUpController
import br.ufpe.cin.ines.ress.User
import br.ufpe.cin.ines.ress.residuecollector.CollectorDashboardController
import br.ufpe.cin.ines.ress.residuegenerator.GeneratorDashboardController
import pages.CollectionPointsPage
import pages.CollectorDashboardPage
import pages.HomePage
import pages.LoginAuthenticationPage
import pages.MapsPage

import static cucumber.api.groovy.EN.*;


/**
 * Created by Leonardo on 20/10/2016.
 */

Given(~/^Estou na página de visualização de mapa do ResS$/) { ->
    to HomePage
    at HomePage
    page.clickLogIn()
    at LoginAuthenticationPage
    page.fillUsername("admin")
    page.fillPassword("pass")
    page.logInButtonClick()
    at CollectorDashboardPage
    page.maps()
    at MapsPage
}
And(~/^os restaurantes de login "([^"]*)" e "([^"]*)" possuem coletas pendentes$/) { String arg1, String arg2 ->
    User user = User.findByUsername(arg1)
    assert user != null
    assert PickupRequest.findByGenerator(user)

    user = User.findByUsername(arg2)
    assert user != null
    assert PickupRequest.findByGenerator(user)
}

When(~/^eu solicito a visualização das coletas$/) { ->
    at MapsPage
    page.clickCollectionPoints()
}

Then(~/^eu vejo a localização dos restaurantes de login "([^"]*)" e "([^"]*)" no mapa da UFPE$/) { String arg1, String arg2 ->
    at CollectionPointsPage
    page.hasmap()
}

And(~/^não existem locais com coletas pendentes$/) { ->
    assert PickupRequest.findAllByStatus(false).empty
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


