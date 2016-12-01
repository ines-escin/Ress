package br.ufpe.cin.ines.ress

class HomeController {

    def index()
    {
        render(view: "index");
    }

    def login()
    {
        redirect(controller: "login", action: "index")
    }

    def list()
    {
        redirect(controller: "list", action: "index")
    }
}
