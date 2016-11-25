package pages

/**
 * Created by user on 27/10/2015.
 */
import geb.Page

class LoginAuthenticationPage extends Page {
    def titulo = "ResS - Login"
    static url = "/ResS/login/auth"

    static at = {
        title ==~ titulo
    }

    def fillUsername(String username){
        $("input", id: "username").value(username)
    }

    def fillPassword(String password){
        $("input", id: "password").value(password)
    }

    def logInButtonClick(){
        $("input", id: "submit").click()
    }

    def confirmSignUpUser(){
        def msg = $(".login_message")
        return msg != null
    }
}
