package pages

import geb.Page

/**
 * Created by user on 27/10/2015.
 */
class CollectorDashboardPage extends Page {
    def titulo = "ResS - Dashboard"
    static url = "collectorDashboard/index"

    static at = {
        title ==~ titulo
    }

    def logOff(){
        $("li", id: "logout").click()
    }

    def configAccount(){
        $("li", id: "configConta").has("a").click()
    }
}
