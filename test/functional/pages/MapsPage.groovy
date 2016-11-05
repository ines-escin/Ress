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

    def clickCollectionPoints(){
        $("li", id: "collectionPoints").click()
    }

    boolean hasNoCollectionPoints(){
        def msg = $(".alert-error")
        if(msg != null)
        {
            return true
        }
        else
        {
            return false
        }
    }






}
