package pages

import geb.Page
import gherkin.lexer.Pa
import steps.InternationalizationHelper

/**
 * Created by Pedro on 05/11/2016.
 */
class PickupRequestPage extends Page{
    def titulo = "ResS - Dashboard"
    static url = "ResS/generatorDashboard/pickupRequest"

    static at = {
        InternationalizationHelper helper = InternationalizationHelper.instance

        String indexTitle = helper.getMessage("default.page.title.dashboard", "Index")
        title ==~ indexTitle
    }


    def fillResidueAmountDef(quantidade) {
        $("input", id: "residueAmount").value(quantidade)
    }

    def submitButtonClick() {
        $("link", class: "button").click()
    }

}
