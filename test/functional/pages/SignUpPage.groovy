package pages

import geb.Page

/**
 * Created by msb5 on 03/10/2016.
 */
class SignUpPage extends Page{
    def titulo = "ResS - Cadastro"
    static url = "/ResS/signUp/index"
    static at = {
        title ==~ titulo
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

    def createDefaultUser(cnpj, tipoUsuario, usuario){
        $("input", id: "name").value("Teste")
        $("input", id: "cnpj").value(cnpj)
        $("input", id: "street").value("Teste")
        $("input", id: "streetNumber").value("123")
        $("input", id: "additionalInfo").value("Teste")
        $("input", id: "neighborhood").value("Teste")
        $("input", id: "city").value("Teste")
        $("input", id: "state").value("Teste")
        $("input", id: "cep").value("Teste")
        $("input", id: "typeUser").value(tipoUsuario)
        $("input", id: "email").value("teste@gmail.com")
        $("input", id: "username").value(usuario)
        $("input", id: "password").value("teste123")
    }

    def resetFields(){
        $("input", id: "name").value("")
        $("input", id: "cnpj").value("")
        $("input", id: "street").value("")
        $("input", id: "streetNumber").value("")
        $("input", id: "additionalInfo").value("")
        $("input", id: "neighborhood").value("")
        $("input", id: "city").value("")
        $("input", id: "state").value("")
        $("input", id: "cep").value("")
        $("input", id: "typeUser").value("")
        $("input", id: "email").value("")
        $("input", id: "username").value("")
        $("input", id: "password").value("")
    }

    String messageError(){
        $("div.login_message").text()
    }
}