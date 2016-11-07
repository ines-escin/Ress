package pages

import geb.Page

class RouteVisualizationPage extends Page{

    def titulo = "ResS - Mapas"
    static url = "ResS/collectorDashboard/maps/route"

    static at = {
        title ==~ titulo
    }

    def hasRoute(){
        $("div", class: "map_canvas")
    }

}
