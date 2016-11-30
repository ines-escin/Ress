package pages

import geb.Page
import steps.InternationalizationHelper

class RouteVisualizationPage extends Page{

    static url = "collectorDashboard/maps/route"

    static at = {

        InternationalizationHelper helper = InternationalizationHelper.instance
        String Maps = "Maps"
        String MapsTitle = helper.getMessage("default.page.title.map", Maps)
        title ==~ MapsTitle
    }


    def systemGetsRoute(){
        def div = $("div", class: "map_canvas")
        return div!=null
    }

}
