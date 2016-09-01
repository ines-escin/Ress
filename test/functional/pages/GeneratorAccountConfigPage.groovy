package pages

import geb.Page

/**
 * Created by user on 27/10/2015.
 */
class GeneratorAccountConfigPage extends Page {
    def titulo = "ResS - Dashboard"
    static url = "ResS/generatorDashboard/editAccountConfig"

    static at = {
        title ==~ titulo
    }
}
