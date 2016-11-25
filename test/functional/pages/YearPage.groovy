package pages

/**
 * Created by Caio on 07/11/2016.
 */

import geb.Page

class YearPage extends Page{
    def titulo = "ResS - Dashboard"
    def url = "ResS/collectorDashboard/viewLastYear"

    static at = {
        title ==~ titulo
    }

    def hasChart(){
        def div =  $("div", id: "chart_div")
        return div!=null
    }
}

