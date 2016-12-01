package br.ufpe.cin.ines.ress

class ListController {

    def index() {
        def lista = User.findAll()
        render(view: "listUsers", model: [collectors: lista])
    }
}
