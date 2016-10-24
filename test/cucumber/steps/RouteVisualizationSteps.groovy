import
import pages.MapsPage

...

Given(~'^Eu estou na tela de visualização de mapas') { ->
    to MapsPage
    at MapsPage
}

And(~'^Existem coletas pendentes para a empresa "([^"]*)" nos pontos "([^"]*)" e "([^"]*)"$') {
    at MapsPage
}

And(~'^Nao existem coletas pendentes') {
    at MapsPage
}

Then(~'^O mapa mostra a rota "([^"]*)", passando pelos pontos "([^"]*)" e "([^"]*)"$') {

}

Then(~'^O mapa exibe uma mensagem de erro$') {

}

Given(~'^Existem coletas pendentes$') {

}

Given(~'^Nao existem coletas pendentes$') {

}

When(~'^Eu solicito a rota para as coletas pendentes$') {

}

Then(~'^O sistema retorna a rota para as coletas pendentes') {

}

Then(~'^O sistema exibe um codigo de erro') {

}