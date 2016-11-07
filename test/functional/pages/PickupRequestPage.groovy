package pages

import geb.Page
import gherkin.lexer.Pa

/**
 * Created by Pedro on 05/11/2016.
 */
class PickupRequestPage extends Page{
    def titulo = "ResS - Dashboard"
    static url = "ResS/generatorDashboard/pickupRequest"

    static at = {
        title ==~ titulo
    }

    def fillResidueAmountDef() {
        $("input", id: "residueAmount").value(2)
    }

    def submitButtonClick() {
        $("link", class: "button").click()
    }

}
