package cucumber.steps

import br.ufpe.cin.ines.ress.Address
import br.ufpe.cin.ines.ress.PickupRequest
import br.ufpe.cin.ines.ress.Role
import br.ufpe.cin.ines.ress.User
import br.ufpe.cin.ines.ress.UserRole
import br.ufpe.cin.ines.ress.residuecollector.CollectorDashboardController
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

    def generatorRole = Role.findByAuthority('ROLE_GENERATOR') ?: new Role(authority: 'ROLE_GENERATOR').save(failOnError: true)
    def generator = User.findByUsername(arg) ?: new User(username: arg,
            password: 'pass',
            name: 'Restaurante Universitário - RU',
            email: arg+ '@gmail.com',
            address: new Address(street: 'Av. Reitor Joaquim Amazonas', cep: '50740-540', city: 'Recife', state: 'Pernambuco', streetNumber: '22', neighborhood: 'Cidade Universitária'),
            enabled: true).save(failOnError: true)
    if(!generator.authorities.contains(generatorRole)){
        UserRole.create(generator, generatorRole, true)
    }

    def pickupRequest = new PickupRequest()

    pickupRequest.generator = User.findByUsername(arg)
    pickupRequest.date = new Date()
    pickupRequest.status = false
    pickupRequest.residueAmount = 40
    pickupRequest.collector = User.findByUsername('admin')
    pickupRequest.save()

    def coletas = PickupRequest.findAllByStatus(false);
    def generators = coletas.collect {it -> it.generator.username}
    def nome = generators.findAll{it -> it == arg}

    assert !nome.isEmpty()
}

def address
When(~/^eu solicito os enderecos dos locais com coletas pendentes$/) { ->
    def controlador = new CollectorDashboardController()
    address = controlador.collectionPointsAddress()
}

Then(~/^o sistema retorna os enderecos do locais de login "([^"]*)"$/) { String arg ->
    def usernames = address.collect{at -> at.user.username}
    assert usernames.contains(arg)
    PickupRequest.executeUpdate('delete from PickupRequest')
}

Given(~/^não existem coletas pendentes$/) { ->
    assert PickupRequest.findAllByStatus(false).empty
}

Then(~/^o sistema nada retorna$/) { ->
    assert address.empty
}


