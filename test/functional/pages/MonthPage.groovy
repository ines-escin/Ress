 package pages

/**
 * Created by Caio on 07/11/2016.
 */

import geb.Page

 class MonthPage extends Page{
        def titulo = "Ress - Charts"
        def url = "ResS/collectorDashboard/viewLastMonth"

        static at = {
            title ==~ titulo
        }

        def hasChartMonth(){
            def div =  $("div", class: "chart_div")
            return div!=null

        }
    }

