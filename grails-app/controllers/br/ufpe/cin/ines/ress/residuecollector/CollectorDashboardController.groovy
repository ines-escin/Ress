package br.ufpe.cin.ines.ress.residuecollector

import br.ufpe.cin.ines.ress.Address
import br.ufpe.cin.ines.ress.PickupRequest
import br.ufpe.cin.ines.ress.Role
import br.ufpe.cin.ines.ress.User
import br.ufpe.cin.ines.ress.UserRole

//@Secured(['ROLE_COLLECTOR'])
class CollectorDashboardController {

    def springSecurityService

    def index() {
        def openPickups = PickupRequest.findAllByStatus(false)
        render (view:'index', model:[openPickupList : openPickups])
    }


    def collectionHistory(Integer max)
    {
        params.max = Math.min(max ?: 2, 100)
        def closedPickups = PickupRequest.findAllByStatus(true).sort{it.generator.name}

        render (view:'collectionHistory', model:[closedPickups : closedPickups])
    }

    def downloadExcelHistory(){
        def data = buildReport()
        response.setHeader("Content-disposition", "attachment; filename=" +
                "HistoricoColetas" + ".csv")
        def out = response.outputStream
        out << data
        out.flush()
        render {contentType: "text/csv"}
    }

    def buildReport(){
        def closedPickups = PickupRequest.findAllByStatus(true).sort{it.generator.name}
        def claims = closedPickups
        String csv = "";

        csv = "Nome,Data,Quantidade\n"

        claims.each {
            csv += it.generator.name + ","
            csv += it.date.toString() + ","
            csv += it.residueAmount + "\n"
        }

        return csv
    }

    def generatorList()
    {
        def generators = User.findAll()
        generators.removeAll {
            !it.getAuthorities().contains(Role.findByAuthority('ROLE_GENERATOR'))
        }
        render (view: 'generatorList', model: [userList: generators])
    }

    def collect(int id)
    {
        def pickup = PickupRequest.findById(id)
        pickup.status = true;
        pickup.save()
        redirect(action: 'index')
    }

    def accountConfig(){
        User user = (User) springSecurityService.currentUser

        if(!user){
            user = User.findByCnpj("25.296.876/0001-58")
        }

        render(view: "accountConfig", model:[user:user])
    }

    def saveAccountChanges(){
        def user = new User(params)
        user.save();
    }

    def editAccountConfig(){
        User user = (User) springSecurityService.currentUser

        if(!user){
            user = User.findByCnpj("25.296.876/0001-58")
        }

        render(view: "editAccount", model: [user: user, address: user.address])

//        render(view: "editAccount")
    }

    def saveUserChanges(){
        def userCnpj = User.findByCnpj(params.cnpj)
        def userUsername = User.findByUsername(params.username)
        User userToChange = (User) springSecurityService.currentUser

        if (!userToChange) {
            userToChange = User.findByCnpj("25.296.876/0001-58")
        }

        if (userCnpj && userCnpj.cnpj != userToChange.cnpj) {
            def msg = message(code: 'default.cnpj.existing.message', args: [userCnpj.cnpj])
            flash.error = msg
            redirect(action: 'editAccountConfig')
        } else if (userUsername && userUsername.username != userToChange.username) {
            def msg = message(code: 'default.username.existing.message', args: [userUsername.username])
            flash.error = msg
            redirect(action: 'editAccountConfig')
        } else {
            try {
                Address address = instanceAddress(params)

                updateChangesUser(userToChange, address, params)

                def msg = message(code: 'default.updated.message', args: [message(code: 'default.user.label'), userToChange.cnpj])
                flash.message = msg
                redirect(action: 'accountConfig')
            } catch (ValidationException) {
                def msg = message(code: 'default.empty.fields.message')
                flash.error = msg
                redirect(action: 'editAccountConfig')
            }
        }
    }

    private void updateChangesUser(User userToChange, Address address, params) {
        userToChange.username = params.username
        userToChange.email = params.email
        userToChange.password = params.password
        userToChange.name = params.name
        userToChange.cnpj = params.cnpj
        userToChange.typeUser = params.typeUser
        userToChange.enabled = true
        userToChange.address.street = address.street
        userToChange.address.streetNumber = address.streetNumber
        userToChange.address.neighborhood = address.neighborhood
        userToChange.address.city = address.city
        userToChange.address.cep = address.cep
        userToChange.address.state = address.state
        userToChange.address.additionalInfo = address.additionalInfo
        userToChange.save()
    }

    private Address instanceAddress(params) {
        Address address = new Address()
        address.street = params.street
        address.streetNumber = params.streetNumber
        address.cep = params.cep
        address.city = params.city
        address.state = params.state
        address.neighborhood = params.neighborhood
        address.additionalInfo = params.additionalInfo
        address
    }

    def deleteCollectorAndPickups(User collector){
        def lista = PickupRequest.findAllByCollectorAndStatus(collector, true)

        lista.each {it -> it.delete(flush: true)}

        UserRole role = UserRole.findByUser(collector)

        role.delete(flush: true)
        collector.delete(flush: true)
    }

    def deleteAllData() {
        PickupRequest.list().each {it.delete(flush: true)}
        UserRole.list().each {it.delete(flush: true)}
        User.list().each {it.delete(flush: true)}
    }
}