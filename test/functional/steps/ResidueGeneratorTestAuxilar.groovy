package steps

import br.ufpe.cin.ines.ress.LoginController

/**
 * Created by danielmaida on 26/10/15.
 */

import br.ufpe.cin.ines.ress.PickupRequest
import br.ufpe.cin.ines.ress.User
import br.ufpe.cin.ines.ress.Address
import br.ufpe.cin.ines.ress.residuegenerator.GeneratorDashboardController
import mail.MailService

class ResidueGeneratorTestAuxilar {
    static generators =
            [

                    [
                            username: "testdummy",
                            password: "testpass",
                            address : new Address(
                                    street: "Elm street",
                                    streetNumber: "13",
                                    neighborhood: "Devil's pit",
                                    city: "Charming",
                                    state: "Arkansas",
                                    cep: '65520020'
                            ), name : "Freddy",
                            email   : "freddy@gmail.com"
                    ],
                    [
                            username: "testdummy2",
                            password: "testpass2",
                            address : new Address(
                                    street: "Elm street",
                                    streetNumber: "13",
                                    neighborhood: "Devil's pit",
                                    city: "Charming",
                                    state: "Arkansas",
                                    cep: '65520020'
                            ), name : "Vlad",
                            email   : "vlad2@gmail.com"
                    ],

                    [
                            username: "ru",
                            password: "testpass",
                            address : new Address(
                                    street: "Elm street",
                                    streetNumber: "13",
                                    neighborhood: "Devil's pit",
                                    city: "Charming",
                                    state: "Arkansas",
                                    cep: '65520020'
                            ), name : "Freddy",
                            email   : "freddy@gmail.com"
                    ]

            ]

    public static def injectGenerator(String username) {
        User generator = findGenerator(username);
        generator.save(flush:true);
    }

    public static def injectCollector() {
        User collector = new User
                (
                        username: "testcoldummy",
                        password: "testcolpass",
                        address: new Address(
                                street: "Elm street",
                                streetNumber: "13",
                                neighborhood: "Devil's pit",
                                city: "Charming",
                                state: "Arkansas",
                                cep: '65520020'
                        ), name: "Dimmy",
                        email: "dimmy@gmail.com"
                );

        collector.save(flush:true);

    }


    public static def findGenerator(String username) {
        User generator = generators.find
                {
                    generator -> generator.username == username
                }
        return generator;
    }

    public static def findPickupByUsername(String username)
    {
        User generator = User.findByUsername(username);
        return PickupRequest.findByGeneratorAndStatus(generator, false);
    }

    public static def createPickup(double residueAmount, String username) {
        PickupRequest pickup = new PickupRequest
                (
                    id: 42,
                    date : new Date(),
                    residueAmount: residueAmount,
                    generator: User.findByUsername(username),
                    collector: User.findByUsername("testcoldummy"),
                    status: false
                )
        pickup.save()
    }

    public static def confirmPickups(){
        def pickups = PickupRequest.findAllByStatus(false)
        int i = 0
        pickups.each {
            pickups[i].status = true
            pickups[i].save()
            i++
        }

    }

    public static def buildCSV(){
        String csv;
        csv = "Nome,Data,Quantidade\n"
        def pickups = PickupRequest.findAllByStatus(true).sort{it.generator.name}
        pickups.each {
            csv += it.collector.name + ","
            csv += it.date.toString() + ","
            csv += it.residueAmount + "\n"
        }
        return csv
    }

    public static def updateUsername(String username, String oldUsername) {
        if (User.findByUsername(username) == null) {
            User user = User.findByUsername(oldUsername)
            user.username = username
            user.save()
        }
    }

    public static def updatePassword(String password, String newUsername) {
        User user = User.findByUsername(newUsername)
        user.password = password
        user.save()
    }

    public static def updateEmail(String email, String newUsername) {
        User user = User.findByUsername(newUsername)
        user.email = email
        user.save()
    }

    public static def sendEmail(String name, double residueAmount) {
        return MailService.sendEmail("dfm2@cin.ufpe.br", name, new Date(), residueAmount)
    }

}