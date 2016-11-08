import br.ufpe.cin.ines.ress.PickupRequest
import br.ufpe.cin.ines.ress.User
import br.ufpe.cin.ines.ress.residuecollector.CollectorDashboardController
import br.ufpe.cin.ines.ress.residuegenerator.GeneratorDashboardController
import cucumber.api.PendingException
import pages.CollectorDashboardPage
import pages.GeneratorDashboardPage
import pages.GraphicsPage
import pages.LoginAuthenticationPage
import pages.MonthPage
import pages.PickupRequestPage
import pages.SignUpPage
import pages.YearPage

import static cucumber.api.groovy.EN.*;

/**
 * Created by Caio on 07/11/2016.
 */

//Por causa do Spring Security, não consegui realizar meus testes de GUI, porém, implementei todos.
Given(~/^Nenhuma coleta foi realizada no último mês$/) { ->
    to CollectorDashboardPage
    at CollectorDashboardPage

    assert page.hasNoCollectionPoints()
}

And(~/^Eu estou na tela de opções de gráficos$/) { ->
    to GraphicsPage
    at GraphicsPage
}


When(~/^Eu seleciono a opção “Último mês”$/) { ->
    at GraphicsPage
    to GraphicsPage

    page.ClickLastMonth()
}

Then(~/^Eu posso ver uma tela com um gráfico vazio$/){
    to MonthPage
    at MonthPage

    assert page.HasChartMonth()
}

And(~/^Foram realizadas apenas 2 coletas no dia “07\/11\/2016”$/){
    to SignUpPage
    at SignUpPage

    page.createDefaultUser("cnpj", "Gerador de Resíduo", 'login')

    to GeneratorDashboardPage
    at GeneratorDashboardPage
    page.pickUpRequest()

    at PickupRequestPage
    page.fillResidueAmountDef()
    page.submitButtonClick()

    to GeneratorDashboardPage
    at GeneratorDashboardPage
    page.logOff()

    to SignUpPage
    at SignUpPage

    page.createDefaultUser("cnpj", "Coletor de Resíduo", 'caio')

    to GeneratorDashboardPage
    at GeneratorDashboardPage
    page.clickConfirmCollect()

    to LoginAuthenticationPage
    at LoginAuthenticationPage
    page.fillUserName('login')
    page.fillPassword('pass')

    to GeneratorDashboardPage
    at GeneratorDashboardPage
    page.pickUpRequest()

    at PickupRequestPage
    page.fillResidueAmountDef()
    page.submitButtonClick()

    to GeneratorDashboardPage
    at GeneratorDashboardPage
    page.logOff()

    to LoginAuthenticationPage
    at LoginAuthenticationPage
    page.fillUserName('caio')
    page.fillPassword('pass')

    to GeneratorDashboardPage
    at GeneratorDashboardPage
    page.clickConfirmCollect()
}

When(~/^Eu seleciono a opção “Último ano”$/) { ->
    to GraphicsPage
    at GraphicsPage

    page.clickViewLastYear()
}


Then(~/^Eu posso ver um gráfico Coletas x Data que possui apenas a altura 2 quando x = “07\/11\/2016”$/){
    to YearPage
    at YearPage

    assert page.HasChartYear()
}

//controller
List<PickupRequest> lista2;
Given(~/^Existe uma lista de coletas realizadas$/) { ->
    def genControl = new GeneratorDashboardController()
    genControl.saveDefaultPickUp('ru')
    CollectorDashboardController collectControl = new CollectorDashboardController()
    def col = User.findByUsername('admin')
    def coleta = PickupRequest.findAllByStatusAndCollector(false, col)
       coleta.each{it -> collectControl.collect(it.id)}
    lista2 = PickupRequest.findAllByStatusAndCollector(true, col)
    assert lista2 != null
    genControl.response.reset()
    collectControl.response.reset()
}

When(~/^Eu seleciono a opção “Último mês”$/) { ->
    def cControl = new CollectorDashboardController()
    cControl.viewLastMonth()
    cControl.response.reset()
}

Then(~/^A lista de coletas realizadas não muda$/) { ->
    def us = User.findByUsername('admin')
    def lista3 =  PickupRequest.findByStatusAndCollector(true, us)
    lista3.each {it ->
        assert lista2.contains(it) != null
    }
}
