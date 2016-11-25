package pages

import geb.Page

class RouteVisualizationPage extends Page{

    def titulo = "ResS - Mapas"
    static url = "ResS/collectorDashboard/maps/route"

    static at = {
        title ==~ titulo
    }

    def systemGetsRoute(){
        def div = $("div", class: "map_canvas")
        return div!=null
    }

}
