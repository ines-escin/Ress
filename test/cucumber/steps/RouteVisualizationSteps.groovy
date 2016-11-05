
import br.ufpe.cin.ines.ress.PickupRequest
import pages.CollectorDashboardPage
import pages.HomePage
import pages.LoginAuthenticationPage
import pages.MapsPage
import pages.RouteVisualizationPage


Given(~'^Eu estou na tela de visualização de mapas') { ->
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

And(~'^Existem coletas pendentes para a empresa "([^"]*)" nos pontos "([^"]*)" e "([^"]*)"$') { String arg1, String arg2 ->
    User user = User.findByUsername(arg1)
    assert user != null
    assert PickupRequest.findByGenerator(user)

    user = User.findByUsername(arg2)
    assert user != null
    assert PickupRequest.findByGenerator(user)
}

And(~'^Nao existem coletas pendentes') {
    assert PickupRequest.findAllByStatus(false).empty
}

Then(~'^O mapa mostra a rota "([^"]*)", passando pelos pontos "([^"]*)" e "([^"]*)"$') {
    at RouteVisualizationPage
    page.hasRoute()
}

Then(~'^O mapa exibe uma mensagem de erro$') {
    at MapsPage
}

Given(~'^Existem coletas pendentes$') {

}

Given(~'^Nao existem coletas pendentes$') {

}

When(~'^Eu solicito a rota para as coletas pendentes$') {
    at MapsPage
    page.ClickRouteVisualization()
}

Then(~'^O sistema retorna a rota para as coletas pendentes') {

}

Then(~'^O sistema exibe um codigo de erro') {

}