package pages

import geb.Page

/**
 * Created by Leonardo on 15/10/2016.
 */
class CollectionPointsPage extends Page{

    def titulo = "ResS - Mapas"
    static url = "ResS/collectorDashboard/collectionPoints"

    static at = {
        title ==~ titulo
    }



    def hasmap(){
        $("div", class: "gmnoprint")

    }


}
