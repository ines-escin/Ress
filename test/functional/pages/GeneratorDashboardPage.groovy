package pages

import geb.Page
import steps.InternationalizationHelper

/**
 * Created by user on 27/10/2015.
 */
class GeneratorDashboardPage extends Page {
    static url = "generatorDashboard/"

    static at = {
        InternationalizationHelper helper = InternationalizationHelper.instance
        String GeneratorDashboard = "Dashboard"
        String  GeneratorTitle = helper.getMessage("default.page.title.dashboard", GeneratorDashboard)

        title ==~ GeneratorTitle
    }

    def logOff(){
        $("li", id: "logout").click()
    }
}
