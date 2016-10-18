import static cucumber.api.groovy.EN.*
/**
 * Created by Leonardo on 15/10/2016.
 */


Given(~'^Estou na página de visualização de mapa do ResS$') { ->
     to CollectionPointsPage
     at CollectionPointsPage
}

And(~'não existem coletas pendentes$'){


}

When(~'eu solicito a visualização das coletas$'){
     at CollectionPointsPage

}

