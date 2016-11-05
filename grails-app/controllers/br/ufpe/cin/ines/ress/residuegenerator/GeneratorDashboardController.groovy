package br.ufpe.cin.ines.ress.residuegenerator

import br.ufpe.cin.ines.ress.DashboardController
import br.ufpe.cin.ines.ress.PickupRequest
import br.ufpe.cin.ines.ress.User
import grails.plugins.springsecurity.Secured
import mail.MailService

@Secured(['ROLE_GENERATOR'])
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

    def accountConfig(){
        User user = (User) springSecurityService.currentUser
        render(view: "accountConfig", model:[user:user])
    }

    def saveAccountChanges(){
        def user = new User(params)
        user.save();
    }

    def editAccountConfig(){
        def user = new User()
        render(view:'editAccount', model: [user: user])
    }

    def saveUserChanges(){
        def newUserInfo = new User(params);
        User userToChange = (User) springSecurityService.currentUser
        userToChange.username = newUserInfo.username
        userToChange.email = newUserInfo.email
        userToChange.password = newUserInfo.password
        userToChange.save()
        redirect (action: 'accountConfig')
    }
}
