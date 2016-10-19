import br.ufpe.cin.ines.ress.PickupRequest
import pages.MapsPage

import static cucumber.api.groovy.EN.*
/**
 * Created by Leonardo on 15/10/2016.
 */


Given(~'^Estou na página de visualização de mapa do ResS$') { ->
     to MapsPage
     at MapsPage
}

And(~'não existem coletas pendentes$'){
     assert PickupRequest.findAllByStatus(false).empty
}

When(~'eu solicito a visualização das coletas$'){
     at MapsPage
     page.clickCollectionPoints()
}

Then (~'eu vejo uma mensagem sinalizando que não há restaurantes com coletas pendentes$'){
     at MapsPage
     assert page.hasNoCollectionPoints()
}




