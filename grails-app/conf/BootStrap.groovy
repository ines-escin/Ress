import br.ufpe.cin.ines.ress.Address
import br.ufpe.cin.ines.ress.User
import br.ufpe.cin.ines.ress.Role
import br.ufpe.cin.ines.ress.UserRole
class BootStrap {

    def init = { servletContext ->
        def collectorRole = Role.findByAuthority('ROLE_COLLECTOR') ?: new Role(authority: 'ROLE_COLLECTOR').save(failOnError: true)
        def collector = User.findByUsername('admin') ?: new User(username: 'admin',
                password: 'pass',
                name: 'Prefeitura',
                email: 'admin@gmail.com',
                address: new Address(street: 'Rua um', cep: '52912-123', city: 'Recife', state: 'Pernambuco', streetNumber: '0', neighborhood: 'Cidade Universitária'),
                enabled: true).save(failOnError: true)
        if(!collector.authorities.contains(collectorRole)){
            UserRole.create(collector, collectorRole, true)
        }

        def generatorRole = Role.findByAuthority('ROLE_GENERATOR') ?: new Role(authority: 'ROLE_GENERATOR').save(failOnError: true)
        def generator = User.findByUsername('ru') ?: new User(username: 'ru',
                password: 'pass',
                name: 'Restaurante Universitário',
                email: 'ru@gmail.com',
                address: new Address(street: 'Rua dois', cep: '52232-123', city: 'Recife', state: 'Pernambuco', streetNumber: '22', neighborhood: 'Cidade Universitária'),
                enabled: true).save(failOnError: true)
        if(!generator.authorities.contains(generatorRole)){
            UserRole.create(generator, generatorRole, true)
        }

        def generatorRole2 = Role.findByAuthority('ROLE_GENERATOR') ?: new Role(authority: 'ROLE_GENERATOR').save(failOnError: true)
        def generator2 = User.findByUsername('cantina') ?: new User(username: 'cantina',
                password: 'pass',
                name: 'Cantina da área 2',
                email: 'cantina@gmail.com',
                address: new Address(street: 'Rua tres', cep: '52223-123', city: 'Recife', state: 'Pernambuco', streetNumber: '99', neighborhood: 'Cidade Universitária'),
                enabled: true).save(failOnError: true)
        if(!generator2.authorities.contains(generatorRole2)){
            UserRole.create(generator2, generatorRole2, true)
        }
    }
    def destroy = {
    }
}
