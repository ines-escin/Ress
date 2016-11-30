package br.ufpe.cin.ines.ress

class SignUpController {


    //STUB
    def save(User user){
        def generatorRole = Role.findByAuthority('ROLE_GENERATOR') ?: new Role(authority: 'ROLE_GENERATOR').save(failOnError: true)
        user.save(failOnError: true)
        UserRole.create(user, generatorRole, true)
    }


    Address getAddressByLogin(login){

       def  address =   Address.findAll();

       address =  address.findAll{it -> it.user.username == login}

       return address[0]
    }
}