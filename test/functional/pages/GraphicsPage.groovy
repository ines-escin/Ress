package pages

import geb.Page
/**
 * Created by Caio on 07/11/2016.
 */
class GraphicsPage extends Page {

        def titulo = "ResS - Dashboard"
        static url = "ResS/collectorDashboard/viewGraphics"

        static at = {
            title ==~ titulo
        }

    def clickViewLastMonth(){
        $("button", id: "viewLastMonth").click()
    }

    def clickViewLastYear(){
        $("button", id: "viewLastYear").click()
    }




}
