package br.ufpe.cin.ines.ress

import org.codehaus.groovy.grails.plugins.springsecurity.SpringSecurityUtils

import javax.xml.bind.ValidationException

class SignUpController {

    /**
     * Index action. Redirects to the Spring security logout uri.
     */
    def index() {
        // TODO put any pre-logout code here
        //redirect uri: SpringSecurityUtils.securityConfig.logout.filterProcessesUrl // '/j_spring_security_logout'


        render(view: "createUser");
    }

    def saveUser() {
        try {
            User user = fillUserInfo()

            Address e = fillAddressInfo()

            user.address = e

            Role role = params.j_typeUser == "Empresa Coletora" ? (Role.findByAuthority('ROLE_COLLECTOR') ?: new Role(authority: 'ROLE_COLLECTOR').save(failOnError: true)) : (Role.findByAuthority('ROLE_GENERATOR') ?: new Role(authority: 'ROLE_GENERATOR').save(failOnError: true))
            user.save(failOnError: true)
            UserRole.create(user, role, true)

            flash.message = "Sucesso!"
            redirect(controller: "login", action: "signUpSuccessful")
        } catch (ValidationException) {
            flash.message = "Certifique os campos informados!"
            redirect(action: "index")
        }
    }

    private Address fillAddressInfo() {
        Address e = new Address()
        e.street = params.j_street
        e.streetNumber = params.j_streetNumber
        e.cep = params.j_cep
        e.city = params.j_city
        e.state = params.j_state
        e.neighborhood = params.j_neighborhood
        e.additionalInfo = params.j_additionalInfo
        e
    }

    private User fillUserInfo() {
        User user = new User()
        user.name = params.j_name
        user.username = params.j_username
        //user.cnpj = params.j_cnpj
        //user.typeUser = params.j_typeUser
        user.email = params.j_email
        user.password = params.j_password
        user.enabled = true;
        user
    }


    def createDefaultGeneratorUser(String arg){
        def generatorRole = Role.findByAuthority('ROLE_GENERATOR') ?: new Role(authority: 'ROLE_GENERATOR').save(failOnError: true)
        def generator = User.findByUsername(arg) ?: new User(username: arg,
                password: 'pass',
                name: 'Restaurante Universitário - RU',
                email: arg+'@gmail.com',
                address: new Address(street: 'Av. Reitor Joaquim Amazonas', cep: '50740-540', city: 'Recife', state: 'Pernambuco', streetNumber: '22', neighborhood: 'Cidade Universitária'),
                enabled: true).save(failOnError: true)
        if(!generator.authorities.contains(generatorRole)){
            UserRole.create(generator, generatorRole, true)
        }
    }
}