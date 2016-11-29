package pages

import geb.Page
import steps.InternationalizationHelper

/**
 * Created by Leonardo on 15/10/2016.
 */
class CollectionPointsPage extends Page{


    static url = "collectorDashboard/collectionPoints"

    static at = {

        InternationalizationHelper helper = InternationalizationHelper.instance
        String Maps = "Maps"
        String MapsTitle = helper.getMessage("default.page.title.map", Maps)
        title ==~ MapsTitle


    }

    def hasmap(){
       def div =  $("div", class: "gmnoprint")

       return div!=null
    }


}
