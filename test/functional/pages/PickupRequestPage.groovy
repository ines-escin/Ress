package pages

import geb.Page
import steps.InternationalizationHelper

/**
 * Created by Leonardo on 04/11/2016.
 */
class PickupRequestPage extends Page {
    static url = "ResS/generatorDashboard/pickupRequest"

    static at = {
        InternationalizationHelper helper = InternationalizationHelper.instance

        String indexTitle = helper.getMessage("default.page.title.dashboard", "Index")
        title ==~ indexTitle
    }

    def FillResidueAmount(quantidade){
        $("input", id: "residueAmount").value(quantidade)
    }

    def submitButtonClick(){
        $("link", class: "button").click()
    }
}

