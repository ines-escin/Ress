package pages

import geb.Page
import steps.InternationalizationHelper

/**
 * Created by Caio on 07/11/2016.
 */
class GraphicsPage extends Page {

        static url = "ResS/collectorDashboard/viewGraphics"

        static at = {
            InternationalizationHelper helper = InternationalizationHelper.instance
            String Graphics = "Dashboard"
            String GraphicsTitle = helper.getMessage("default.page.title.dashboard", Graphics)

            title ==~ GraphicsTitle
        }

    def clickViewLastMonth(){
        $("button", id: "viewLastMonth").click()
    }

    def clickViewLastYear(){
        $("button", id: "viewLastYear").click()
    }




}
