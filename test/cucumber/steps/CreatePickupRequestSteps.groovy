import br.ufpe.cin.ines.ress.PickupRequest

import static cucumber.api.groovy.EN.*;
import steps.ResidueGeneratorTestAuxilar;

Given(~'^there is no open pickup request for the residue generator with the username "([^"]*)"$') { String username ->
    ResidueGeneratorTestAuxilar.injectGenerator(username)
    generatorUsername = username
    ResidueGeneratorTestAuxilar.injectCollector();
    assert ResidueGeneratorTestAuxilar.findPickupByUsername(username) == false
}

When(~'^I make a pickup request for my "([^"]*)" liters residue container$'){ String amountString ->
    amount = Double.parseDouble(amountString);
    ResidueGeneratorTestAuxilar.createPickup(amount, generatorUsername);
}

Then(~'^my pickup request should be stored in the database$'){ ->
    assert ResidueGeneratorTestAuxilar.findPickupByUsername(generatorUsername)
}

And(~'^the collector should be warned by email$'){ ->
    assert ResidueGeneratorTestAuxilar.sendEmail(generatorUsername, amount)
}

When(~'I try to make a pickup request with the residue amount equals to "([^"]*)"$') { String amountString ->
    double amountValue = Double.parseDouble(amountString)
    ResidueGeneratorTestAuxilar.createPickup(amountValue, generatorUsername);
}

Then(~'^the system should not store the pickup request$'){ ->
    def teste = generatorUsername
    assert ResidueGeneratorTestAuxilar.findPickupByUsername(teste) == false
}