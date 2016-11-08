package pages

/**
 * Created by Caio on 07/11/2016.
 */

import geb.Page

class YearPage extends Page{
    def titulo = "Ress - Charts"
    def url = "ResS/collectorDashboard/viewLastYear"

    static at = {
        title ==~ titulo
    }

    def hasChartYear(){
        def div =  $("div", class: "chart_div")
        return div!=null

    }
}

