package pages

import geb.Page

/**
 * Created by Leonardo on 04/11/2016.
 */
class PickupRequestPage extends Page {
    def titulo = "ResS - Dashboard"
    static url = "ResS/generatorDashboard/pickupRequest"

    static at = {
        title ==~ titulo
    }

    def fillResidueAmountDef(){
        $("input", id: "residueAmount").value(20)
    }

    def submitButtonClick(){
        $("link", class: "button").click()
    }
}

