package pages

import geb.Page
import steps.InternationalizationHelper

/**
 * Created by Leonardo on 15/10/2016.
 */
class MapsPage extends Page {


    static url = "ResS/collectorDashboard/maps"

    static at = {

        InternationalizationHelper helper = InternationalizationHelper.instance
        String MapsTitle = helper.getMessage("default.page.title.map", "Maps")
        title ==~ MapsTitle
    }

    def clickCollectionPoints(){
        $("li", id: "collectionPoints").click()
    }

    boolean hasNoCollectionPoints(){
        def msg = $(".alert-error")
        msg != null
    }

}
