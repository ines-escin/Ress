package pages

import geb.Page

class MapsPage extends Page {

    def titulo = "ResS - Mapas"
    static url = "ResS/collectorDashboard/maps"

    static at = {
        title ==~ titulo
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
