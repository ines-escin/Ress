package br.ufpe.cin.ines.ress.residuecollector

import br.ufpe.cin.ines.ress.Address
import br.ufpe.cin.ines.ress.PickupRequest
import br.ufpe.cin.ines.ress.Role
import br.ufpe.cin.ines.ress.User
import br.ufpe.cin.ines.ress.UserRole
import grails.plugins.springsecurity.Secured
import grails.validation.ValidationException

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
                User newUserInfo = new User()
                newUserInfo.name = params.name
                newUserInfo.username = params.username
                newUserInfo.cnpj = params.cnpj
                newUserInfo.typeUser = params.typeUser
                newUserInfo.email = params.email
                newUserInfo.password = params.password
                newUserInfo.enabled = true;

                Address e = new Address()
                e.street = params.street
                e.streetNumber = params.streetNumber
                e.cep = params.cep
                e.city = params.city
                e.state = params.state
                e.neighborhood = params.neighborhood
                e.additionalInfo = params.additionalInfo

                newUserInfo.address = e

                //def newUserInfo = new User(params);
                userToChange.username = newUserInfo.username
                userToChange.email = newUserInfo.email
                userToChange.password = newUserInfo.password
                userToChange.name = newUserInfo.name
                userToChange.cnpj = newUserInfo.cnpj
                userToChange.typeUser = newUserInfo.typeUser
                userToChange.address.street = newUserInfo.address.street
                userToChange.address.streetNumber = newUserInfo.address.streetNumber
                userToChange.address.neighborhood = newUserInfo.address.neighborhood
                userToChange.address.city = newUserInfo.address.city
                userToChange.address.cep = newUserInfo.address.cep
                userToChange.address.state = newUserInfo.address.state
                userToChange.address.additionalInfo = newUserInfo.address.additionalInfo
                userToChange.save()

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

    def deleteCollectorAndPickups(User collector){
        def lista = PickupRequest.findAllByCollectorAndStatus(collector, true)

        lista.each {it -> it.delete(flush: true)}

        UserRole role = UserRole.findByUser(collector)

        role.delete(flush: true)
        collector.delete(flush: true)
    }
}