package pages

/**
 * Created by Caio on 07/11/2016.
 */

import geb.Page
import steps.InternationalizationHelper

class YearPage extends Page{
    def url = "collectorDashboard/viewLastYear"

    static at = {
        InternationalizationHelper helper = InternationalizationHelper.instance
        String Year = "Dashboard"
        String YearTitle = helper.getMessage("default.page.title.dashboard", Year)

        title ==~ YearTitle
    }

    def hasChart(){
        def div =  $("div", id: "chart_div")
        return div!=null
    }
}

