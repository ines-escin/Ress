package br.ufpe.cin.ines.ress.residuecollector

import br.ufpe.cin.ines.ress.PickupRequest
import br.ufpe.cin.ines.ress.Role
import br.ufpe.cin.ines.ress.User
import grails.plugins.springsecurity.Secured
import org.grails.plugins.csv.CSVWriter
import sun.util.resources.CalendarData_ar

import java.text.SimpleDateFormat

//import pl.touk.excel.export.WebXlsxExporter

import static br.ufpe.cin.ines.ress.User.*


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
    
    List<String> stringPickUps(String tempo){
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        def coletas = PickupRequest.findAllByStatus(true).sort{it.date}
        def datas = coletas.collect{it -> it.date}
        if(tempo == 'month'){
            Calendar c = Calendar.getInstance()
            Calendar current = Calendar.getInstance()
            for(int j = 0; j<datas.size(); j++){
                c.setTime(datas[j])
                if((c.get(c.MONTH) < current.get(current.MONTH)) ||
                        ((c.get(c.MONTH) == current.get(current.MONTH)) && (c.get(c.YEAR) < current.get(current.YEAR)))) {
                    datas.remove(datas[j])
                    println "eita"
                }
            }
        } else if(tempo == 'year') {
            Calendar c = Calendar.getInstance()
            Calendar current = Calendar.getInstance()
            for(int j = 0; j<datas.size(); j++){
                c.setTime(datas[j])
                if((c.get(c.YEAR) < current.get(current.YEAR))){
                    datas.remove(datas[j])
                }
            }
        }

        def strings = datas.collect{it -> formatter.format(it)}
        return strings
    }

    List<String> uniqueStringPickUps(String tempo){
        def strings = stringPickUps(tempo)
        def ustrings = strings.unique()
        return ustrings
    }

    List<Number> freqPickUps(String tempo){
        def strings = stringPickUps(tempo)
        def ustrings = uniqueStringPickUps(tempo)
        def frequencies = ustrings.collect{it -> strings.count(it)}
        return frequencies
    }

    def viewGraphics(){
        render(view:'viewGraphics')
    }

    def viewLastMonth(){
        def datas = uniqueStringPickUps('month')
        def freq = freqPickUps('month')
        render(view:'viewCharts', model: [datas: datas, freq: freq])
    }

    def viewLastYear(){
        def datas = uniqueStringPickUps('year')
        def freq = freqPickUps('year')
        render(view:'viewCharts', model: [datas: datas, freq: freq])
    }
}
