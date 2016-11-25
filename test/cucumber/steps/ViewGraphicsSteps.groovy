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
Given(~/^Nenhuma coleta foi realizada no último mes$/) { ->
    to CollectorDashboardPage
    at CollectorDashboardPage

    assert page.hasNoPickUp()
}

And(~/^Eu estou na tela de opções de gráficos$/) { ->
    to GraphicsPage
    at GraphicsPage
}


When(~/^Eu seleciono a opção “Último mês”$/) { ->
    page.clickViewLastMonth()
    at MonthPage
}

Then(~/^Eu posso ver uma tela com um gráfico vazio$/){->
    assert page.hasChart()
}

And(~/^Foram realizadas apenas 2 coletas no dia atual$/){ ->
    to SignUpPage
    at SignUpPage

    page.createDefaultUser("cnpj", "Gerador de Resíduo", "login")

    to GeneratorDashboardPage
    at GeneratorDashboardPage
    page.pickUpRequest()

    at PickupRequestPage
    page.fillResidueAmountDef()
    page.submitButtonClick()

    to GeneratorDashboardPage
    at GeneratorDashboardPage
    page.logOff()

    to CollectorDashboardPage
    at CollectorDashboardPage
    page.clickConfirmCollect()

    to LoginAuthenticationPage
    at LoginAuthenticationPage
    page.fillUsername("login")
    page.fillPassword("pass")

    to GeneratorDashboardPage
    at GeneratorDashboardPage
    page.pickUpRequest()

    at PickupRequestPage
    page.fillResidueAmountDef()
    page.submitButtonClick()

    to GeneratorDashboardPage
    at GeneratorDashboardPage
    page.logOff()

    to CollectorDashboardPage
    at CollectorDashboardPage
    page.clickConfirmCollect()
}

When(~/^Eu seleciono a opção “Último ano”$/) { ->
    page.clickViewLastYear()
    at YearPage
}


Then(~/^Eu posso ver um gráfico Coletas x Data que possui apenas a altura 2 quando x é a data atual$/){ ->
    assert page.hasChart()
}

//controller
List<PickupRequest> lista2;
Given(~/^Existe uma lista de coletas realizadas$/) { ->
    def genControl = new GeneratorDashboardController()
    genControl.saveDefaultPickUp('ru')
    CollectorDashboardController collectControl = new CollectorDashboardController()
    def coletor = User.findByUsername('admin')
    def coleta = PickupRequest.findAllByStatusAndCollector(false, coletor)
       coleta.each{it -> collectControl.collect(it.id)}
    lista2 = PickupRequest.findAllByStatusAndCollector(true, coletor)
    assert lista2 != null
    genControl.response.reset()
    collectControl.response.reset()
}

When(~/^Eu seleciono a opção “Último mes”$/) { ->
    def dashControl = new CollectorDashboardController()
    dashControl.viewLastMonth()
    dashControl.response.reset()
}

Then(~/^A lista de coletas realizadas não muda$/) { ->
    def us = User.findByUsername('admin')
    def lista3 =  PickupRequest.findAllByStatusAndCollector(true, us)
    lista2.each {it ->
        assert lista3.contains(it) != null
    }
}
