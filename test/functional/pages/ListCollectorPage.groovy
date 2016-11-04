package pages

import geb.Page

/**
 * Created by Marcos on 31/10/2016.
 */
class ListCollectorPage extends Page {
    def titulo = "ResS - Coletores"
    static url = "/ResS/list/index"
    static at = {
        title ==~ titulo
    }

    def hasCnpjAndTypeUser(String cnpj, String typeUser){
//        $("td", text: cnpj)
        $("td", text: cnpj) && $("td", text: typeUser)
    }

    def hasCnpjOrUsername(String cnpj, String username){
        $("td", text: cnpj) || $("td", text: username)
    }
}
