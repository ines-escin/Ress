import cucumber.api.PendingException
import pages.SignUpPage

Given(~/^Estou na pagina de cadastro do ResS\.$/) { ->
    to SignUpPage
    at SignUpPage
}

And(~/^o usuário com o cnpj "([^"]*)" não está cadastrado\.$/){ String cnpj ->
    
}