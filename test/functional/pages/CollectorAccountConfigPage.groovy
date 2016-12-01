package pages

import br.ufpe.cin.ines.ress.User
import geb.Page

/**
 * Created by user on 27/10/2015.
 */
class CollectorAccountConfigPage extends Page {
    def titulo = "ResS - Dashboard"
    static url = "collectorDashboard/editAccountConfig"

    static at = {
        title ==~ titulo
    }

    def fillCollectorEmail(String email){
        $("input", id: "email").value(email)
    }

    def fillCollectorCnpj(String cnpj){
        $("input", id: "cnpj").value(cnpj)
    }

    def fillCollectorPassword(String password){
        $("input", id: "password").value(password)
    }

    def fillCollectorUsername(String username){
        $("input", id: "username").value(username)
    }

    def confirmEditUser(){
        $("input", id: "btnConfirm").click()
    }

    def verifyUpdate(){
        def msg = $(".login_message")
        return msg != null
    }
}
