package pages

import geb.Page

/**
 * Created by user on 27/10/2015.
 */
class CollectorAccountConfigPage extends Page {
    def titulo = "ResS - Dashboard"
    static url = "ResS/collectorDashboard/editAccountConfig"

    static at = {
        title ==~ titulo
    }
}
