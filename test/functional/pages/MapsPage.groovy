package pages

import geb.Page
import steps.InternationalizationHelper

class MapsPage extends Page {

    static url = "collectorDashboard/maps"

    static at = {
        InternationalizationHelper helper = InternationalizationHelper.instance
        String MapsTitle = helper.getMessage("default.page.title.map", "Maps")
        title ==~ MapsTitle
    }


    def clickRouteVisualization(){
        $("li", id: "routeVisualization").click()
    }

    boolean hasNoCollectionPoints(){
        def msg = $(".alert-error")
        if(msg != null) {
            return true
        } else {
            return false
        }
    }

}
