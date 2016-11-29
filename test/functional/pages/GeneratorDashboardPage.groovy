package pages

import geb.Page
import steps.InternationalizationHelper

/**
 * Created by user on 27/10/2015.
 */
class GeneratorDashboardPage extends Page {

    static url = "ResS/generatorDashboard/index"

    static at = {
        InternationalizationHelper helper = InternationalizationHelper.instance

        String indexTitle = helper.getMessage("default.page.title.dashboard", "Index")
        print($("h1").text())
        title ==~ indexTitle

    }

    def logOff(){
        $("li", id: "logout").click()
    }

    def pickupRequest(){
        $("li", id: "pickupRequest").click()
    }
}
