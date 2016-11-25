package steps

import br.ufpe.cin.ines.ress.LoginController

/**
 * Created by danielmaida on 26/10/15.
 */

import br.ufpe.cin.ines.ress.PickupRequest
import br.ufpe.cin.ines.ress.User
import br.ufpe.cin.ines.ress.Address
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
                            ), name : "Olaf",
                            email   : "olaf@gmail.com",
                            cnpj: "63.581.978/0001-04",
                            typeUser: "Gerador de Resíduo"
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
                            email   : "vlad2@gmail.com",
                            cnpj: "28.488.426/0001-55",
                            typeUser: "Gerador de Resíduo"
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
                            email   : "freddy@gmail.com",
                            cnpj: "02.814.403/0001-08",
                            typeUser: "Gerador de Resíduo"
                    ],

                    [
                            username: "ruteste",
                            password: "123",
                            address : new Address(
                                    street: "Elm street",
                                    streetNumber: "13",
                                    neighborhood: "Devil's pit",
                                    city: "Charming",
                                    state: "Arkansas",
                                    cep: '65520020'
                            ), name : "Roger",
                            email   : "roger@gmail.com",
                            cnpj: "56.896.678/0001-59",
                            typeUser: "Gerador de Resíduo",
                            enabled: true
                    ]

            ]

    static User collector = new User
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
                    email: "dimmy@gmail.com",
                    cnpj: "24.831.494/0001-14",
                    typeUser: "Empresa Coletora"
            );

    static User collector2 = new User
            (
                    username: "logintesteusername",
                    password: "testcolpass",
                    address: new Address(
                            street: "rua",
                            streetNumber: "13",
                            neighborhood: "varzea",
                            city: "toronto",
                            state: "londres",
                            cep: '44900000'
                    ), name: "Fausto",
                    email: "fsilva@gmail.com",
                    cnpj: "46.687.864/0001-24",
                    typeUser: "Empresa Coletora",
                    enabled: true
            );

    public static def injectGenerator(String username) {
        User generator = findGenerator(username);
        generator.save(flush:true);
    }

    public static def injectCollector() {
//        User collector = new User
//                (
//                        username: "testcoldummy",
//                        password: "testcolpass",
//                        address: new Address(
//                                street: "Elm street",
//                                streetNumber: "13",
//                                neighborhood: "Devil's pit",
//                                city: "Charming",
//                                state: "Arkansas",
//                                cep: '65520020'
//                        ), name: "Dimmy",
//                        email: "dimmy@gmail.com",
//                        cnpj: "24.831.494/0001-14",
//                        typeUser: "Empresa Coletora"
//                );

        collector.save(flush:true);

    }

    public static def findGenerator(String username) {
        User generator = generators.find
                {
                    generator -> generator.username == username
                }
        return generator;
    }

    public static  def findCollector2(){
        return collector2
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
        pickup.save(flush: true)
    }

    public static def confirmPickups(){
        def pickups = PickupRequest.findAllByStatus(false)
        int i = 0
        pickups.each {
            pickups[i].status = true
            pickups[i].save(flush: true)
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
            user.save(flush: true)
        }
    }

    public static def updatePassword(String password, String newUsername) {
        User user = User.findByUsername(newUsername)
        user.password = password
        user.save(flush: true)
    }

    public static def updateEmail(String email, String newUsername) {
        User user = User.findByUsername(newUsername)
        user.email = email
        user.save(flush: true)
    }

    public static def sendEmail(String name, double residueAmount) {
        return MailService.sendEmail("dfm2@cin.ufpe.br", name, new Date(), residueAmount)
    }
}