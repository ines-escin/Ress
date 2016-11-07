package pages

import geb.Page

class RouteVisualizationPage {

    def titulo = "ResS - Mapas"
    static url = "ResS/collectorDashboard/maps/route"

    def at = {
        title ==~ titulo
    }

    def hasRoute(){
        $("div", class: "map_canvas")
    }

}
