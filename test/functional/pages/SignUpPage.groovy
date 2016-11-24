package pages
import br.ufpe.cin.ines.ress.Address
import br.ufpe.cin.ines.ress.Role
import br.ufpe.cin.ines.ress.User
import br.ufpe.cin.ines.ress.UserRole
import geb.Page
/**
 * Created by crp3 on 24/11/2016.
 */
class SignUpPage extends Page {

    //STUB

    def titulo = "Coopere UFPE"
    static url = "/ResS/"

    static at = {
        title ==~ titulo
    }


    def createDefaultUser(cnpj, tipoUsuario, usuario){
        def generatorRole = Role.findByAuthority('ROLE_GENERATOR') ?: new Role(authority: 'ROLE_GENERATOR').save(failOnError: true)
        def generator = User.findByUsername('ru') ?: new User(username: usuario,
                password: 'pass',
                name: 'Restaurante Universitário - RU',
                email: usuario+ '@gmail.com',
                address: new Address(street: 'Av. Reitor Joaquim Amazonas', cep: '50740-540', city: 'Recife', state: 'Pernambuco', streetNumber: '22', neighborhood: 'Cidade Universitária'),
                enabled: true).save(failOnError: true)
        if(!generator.authorities.contains(generatorRole)){
            UserRole.create(generator, generatorRole, true)
        }
    }





}