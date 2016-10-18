package pages

import geb.Page

/**
 * Created by Leonardo on 15/10/2016.
 */
class MapsPage extends Page {

    def titulo = "ResS - Mapas"
    static url = "ResS/collectorDashboard/maps"

    static at = {
        title ==~ titulo
    }

    def ClickCollectionPoints(){
        $("li", id: "collectionPoints").click()
    }






}
