package pages

import geb.Page

/**
 * Created by user on 27/10/2015.
 */
class EditCollectorPage extends Page {
    def titulo = "ResS - Dashboard"
    static url = "ResS/collectorDashboard/accountConfig"

    static at = {
        title ==~ titulo
    }

    def fillCollectorUsername(String username){
        $("input", id: "username").value(username)
    }

    def fillCollectorPassword(String password){
        $("input", id: "password").value(password)
    }

    def confirmCollectorEdition(){
        $("input", value: "Confirmar").click()
    }
}
