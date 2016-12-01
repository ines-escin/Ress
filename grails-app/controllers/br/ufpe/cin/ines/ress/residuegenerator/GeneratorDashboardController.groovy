package br.ufpe.cin.ines.ress.residuegenerator

import br.ufpe.cin.ines.ress.Address
import br.ufpe.cin.ines.ress.PickupRequest
import br.ufpe.cin.ines.ress.User
import br.ufpe.cin.ines.ress.residuecollector.CollectorDashboardController
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
        pickupRequest.save(flush: true)
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
        pickupRequest.save(flush: true)
    }

    def accountConfig(){
        User user = (User) springSecurityService.currentUser
        render(view: "accountConfig", model:[user:user])
    }

    def saveAccountChanges(){
        def user = new User(params)
        user.save(flush: true);
    }

    def editAccountConfig(){
        User user = (User) springSecurityService.currentUser
        render(view:'editAccount', model: [user: user, address: user.address])
    }

    def saveUserChanges(){
        redirect(controller: "collectorDashboard", action: "saveUserChanges", params: params)
    }
}
