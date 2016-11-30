 package pages

/**
 * Created by Caio on 07/11/2016.
 */

import geb.Page
 import steps.InternationalizationHelper

 class MonthPage extends Page{
        def url = "collectorDashboard/viewLastMonth"

        static at = {
            InternationalizationHelper helper = InternationalizationHelper.instance
            String Month = "Dashboard"
            String MonthTitle = helper.getMessage("default.page.title.dashboard", Month)

            title ==~ MonthTitle
        }

        def hasChart(){
            def div =  $("div", id: "chart_div")
            return div != null
        }
    }

