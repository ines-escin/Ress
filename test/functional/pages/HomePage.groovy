package pages

/**
 * Created by user on 27/10/2015.
 */
import geb.Page
class HomePage extends Page{
   def titulo = "ResS - Gerenciamento de residuos"
   static url = "/ResS/"

    static at = {
        title ==~ titulo
    }

    def clickLogIn(){
        $("li", id: "login").click()
    }

}
