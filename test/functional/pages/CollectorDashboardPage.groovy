package pages

import geb.Page

/**
 * Created by user on 27/10/2015.
 */
class CollectorDashboardPage extends Page {
    def titulo = "ResS - Dashboard"
    static url = "ResS/collectorDashboard/index"

    static at = {
        title ==~ titulo
    }

    def logOff(){
        $("li", id: "logout").click()
    }
}
