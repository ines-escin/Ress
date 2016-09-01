/**
 * Created by user on 04/12/2015.
 */
import steps.ResidueGeneratorTestAuxilar

import static cucumber.api.groovy.EN.*

Given(~'^the history collection is in the system$'){ ->
    ResidueGeneratorTestAuxilar.createPickup(21, "RU")
    ResidueGeneratorTestAuxilar.createPickup(98, "Cantina")
    ResidueGeneratorTestAuxilar.confirmPickups()
}

When(~'^I export the history collection in an excel file$'){ ->
    csv = ResidueGeneratorTestAuxilar.buildCSV()

}

Then(~'^A csv file is generated with the history collection$'){ ->
    assert csv != null
}