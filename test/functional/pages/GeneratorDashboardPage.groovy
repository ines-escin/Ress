package pages

import geb.Page
import steps.InternationalizationHelper

/**
 * Created by user on 27/10/2015.
 */
class GeneratorDashboardPage extends Page {
    def titulo = "ResS - Dashboard"
    static url = "ResS/generatorDashboard/"

    static at = {
        InternationalizationHelper helper = InternationalizationHelper.instance

        String indexTitle = helper.getMessage("default.page.title.dashboard", "Index")
        title ==~ indexTitle
    }


    def logOff(){
        $("li", id: "logout").click()
    }

    def pickupRequest() {
        $("li", id:"pickupRequest").click()
    }
}
