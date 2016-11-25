 package pages

/**
 * Created by Caio on 07/11/2016.
 */

import geb.Page

 class MonthPage extends Page{
        def titulo = "ResS - Dashboard"
        def url = "ResS/collectorDashboard/viewLastMonth"

        static at = {
            title ==~ titulo
        }

        def hasChart(){
            def div =  $("div", id: "chart_div")
            return div != null
        }
    }

