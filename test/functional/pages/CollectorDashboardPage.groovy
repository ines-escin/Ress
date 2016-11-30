package pages

import geb.Page
import steps.InternationalizationHelper

/**
 * Created by user on 27/10/2015.
 */
class CollectorDashboardPage extends Page {

    static url = "collectorDashboard/index"

    static at = {
        InternationalizationHelper helper = InternationalizationHelper.instance

        String indexTitle = helper.getMessage("default.page.title.dashboard", "Index")
        title ==~ indexTitle
    }

    def logOff(){
        $("li", id: "logout").click()
    }

    def hasNoPickUp() {
        if ($("td", text: "Nenhum pedido de coleta está aberto no momento")) {
            return true;
        } else {
            return false;
        }
    }
}
