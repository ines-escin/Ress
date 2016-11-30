package pages

/**
 * Created by user on 27/10/2015.
 */
import geb.Page
import steps.InternationalizationHelper

class LoginAuthenticationPage extends Page {
    static url = "/ResS/login/auth"

    static at = {
        InternationalizationHelper helper = InternationalizationHelper.instance
        String LoginAuthentication = "Login"
        String LoginTitle = helper.getMessage("default.page.title.dashboard", LoginAuthentication)

        title ==~ LoginTitle
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
}
