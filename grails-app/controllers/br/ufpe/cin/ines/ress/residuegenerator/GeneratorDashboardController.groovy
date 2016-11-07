package br.ufpe.cin.ines.ress.residuegenerator

import br.ufpe.cin.ines.ress.Address
import br.ufpe.cin.ines.ress.PickupRequest
import br.ufpe.cin.ines.ress.User
import grails.plugins.springsecurity.Secured
import grails.validation.ValidationException
import mail.MailService

//@Secured(['ROLE_GENERATOR'])
class GeneratorDashboardController{

    def springSecurityService

    def index()
    {
        User currentUser = (User)springSecurityService.currentUser;
        def pickupHistory = PickupRequest.findAllByGeneratorAndStatus(currentUser, true)
        render(view: 'index', model: [history : pickupHistory])
    }

    def pickupRequest()
    {
        User currentUser = (User)springSecurityService.currentUser;
        PickupRequest pickupRequest = PickupRequest.findByGeneratorAndStatus(currentUser, false)
        render (view: "pickup", model:[pickup:pickupRequest])
    }

    def savePickup()
    {
        def pickupRequest = new PickupRequest(params)
        User currentUser = (User)springSecurityService.currentUser;
        pickupRequest.generator = currentUser
        pickupRequest.date = new Date()
        pickupRequest.status = false
        pickupRequest.collector = User.findByUsername('admin')
        pickupRequest.save()
        MailService.sendEmail("dfm2@cin.ufpe.br", pickupRequest.generator.name, pickupRequest.date, pickupRequest.residueAmount)
        redirect(action:'pickupRequest')
    }

    def createPickupRequestConfirmedKl(User collector, User generator){
        def pickupRequest = new PickupRequest()
        pickupRequest.generator = generator
        pickupRequest.date = new Date()
        pickupRequest.status = true
        pickupRequest.collector = collector
        pickupRequest.residueAmount = 1000
        pickupRequest.save()
    }

    def accountConfig(){
        User user = (User) springSecurityService.currentUser
        render(view: "accountConfig", model:[user:user])
    }

    def saveAccountChanges(){
        def user = new User(params)
        user.save();
    }

    def editAccountConfig(){
        User user = (User) springSecurityService.currentUser
        render(view:'editAccount', model: [user: user, address: user.address])
    }

    def saveUserChanges(){
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

            User user = User.findByCnpj(newUserInfo.cnpj)
            User userToChange = (User) springSecurityService.currentUser

            if (user.cnpj == userToChange.cnpj) {
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
            } else {
                throw ValidationException()
            }
        } catch (ValidationException) {
            def msg = message(code: 'default.cnpj.existing.message', args: [params.cnpj])
            flash.error = msg
            redirect(action: 'editAccountConfig')
        }
    }
}
