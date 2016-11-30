package pages

import geb.Page
import steps.InternationalizationHelper

/**
 * Created by user on 27/10/2015.
 */
class CollectorDashboardPage extends Page {
    def titulo = "ResS - Dashboard"
    static url = "ResS/collectorDashboard/index"

    static at = {
        InternationalizationHelper helper = InternationalizationHelper.instance
        String CollectorDashboard = "Dashboard"
        String CollectorTitle = helper.getMessage("default.page.title.dashboard", CollectorDashboard)

        title ==~ CollectorTitle
    }

    def logOff(){
        $("li", id: "logout").click()
    }

    def hasNoPickUp() {
        return $("td", text: "Nenhum pedido de coleta está aberto no momento")
    }
}
