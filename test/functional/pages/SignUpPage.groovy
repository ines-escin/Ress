package pages

import geb.Page
import steps.InternationalizationHelper

/**
 * Created by msb5 on 03/10/2016.
 */
class SignUpPage extends Page{
    static url = "signUp/index"

    static at = {
        InternationalizationHelper helper = InternationalizationHelper.instance
        String signUp = "Sign Up"
        String signUpTitle = helper.getMessage("default.page.signup.title", signUp)

//        def x = $("h1").text()
//        assert x ==~ "teste"

        title ==~ signUpTitle
    }

    def fillUserDetails(nome, cnpj, rua, numero, infoAdicional, bairro, cidade, estado, cep, tipoUsuario, email, usuario, senha) {
        $("input", id: "name").value(nome)
        $("input", id: "cnpj").value(cnpj)
        $("input", id: "street").value(rua)
        $("input", id: "streetNumber").value(numero)
        $("input", id: "additionalInfo").value(infoAdicional)
        $("input", id: "neighborhood").value(bairro)
        $("input", id: "city").value(cidade)
        $("input", id: "state").value(estado)
        $("input", id: "cep").value(cep)
        $("input", id: "typeUser").value(tipoUsuario)
        $("input", id: "email").value(email)
        $("input", id: "username").value(usuario)
        $("input", id: "password").value(senha)
    }

    def createUser() {
        $("input", name: "ok").click()
    }

    def createDefaultUserCnpjTypeUserUsername(cnpj, tipoUsuario, usuario, email){
        fillUserDetails("Teste", cnpj, "Teste", "123", "Teste", "Teste", "Teste", "Teste", "Teste", tipoUsuario, email, usuario, "teste123")
    }

    boolean messageError(){
        def msg = $(".login_message")
        return msg != null
    }
}