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

    def signup() {
        render(view: "signup")
    }

    def saveUser() {
        def userCnpj = User.findByCnpj(params.j_cnpj)
        def userUsername = User.findByUsername(params.j_username)

        if (userCnpj) {
            def msg = message(code: 'default.cnpj.existing.message', args: [userCnpj.cnpj])
            flash.message = msg
            render(view: "/home/signup", model: [message: msg])
        } else if (userUsername) {
            def msg = message(code: 'default.username.existing.message', args: [userUsername.username])
            flash.message = msg
            render(view: "/home/signup", model: [message: msg])
        } else {
            try {
                User user = instanceUser(params)

                save(user)

                def msg = message(code: 'default.user.created.message')
                flash.message = msg
                redirect(controller: "login", action: "index")
            } catch (ValidationException) {
                def msg = message(code: 'default.empty.fields.message')
                flash.message = msg
                redirect(action: "signup")
            }
        }
    }

    private User instanceUser(params) {
        User user = new User()
        user.name = params.j_name
        user.username = params.j_username
        user.cnpj = params.j_cnpj
        user.typeUser = params.j_typeUser
        user.email = params.j_email
        user.password = params.j_password
        user.enabled = true;

        Address address = new Address()
        address.street = params.j_street
        address.streetNumber = params.j_streetNumber
        address.cep = params.j_cep
        address.city = params.j_city
        address.state = params.j_state
        address.neighborhood = params.j_neighborhood
        address.additionalInfo = params.j_additionalInfo

        user.address = address
        user
    }

    def save(User user){
        Role role = user.typeUser == "Empresa Coletora" ? (Role.findByAuthority('ROLE_COLLECTOR') ?: new Role(authority: 'ROLE_COLLECTOR').save(failOnError: true)) : (Role.findByAuthority('ROLE_GENERATOR') ?: new Role(authority: 'ROLE_GENERATOR').save(failOnError: true))
        user.save(failOnError: true)
        UserRole.create(user, role, true)
    }
}
