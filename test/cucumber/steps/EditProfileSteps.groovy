import br.ufpe.cin.ines.ress.User
import net.sf.ehcache.search.expression.And
import pages.*
import steps.ResidueGeneratorTestAuxilar

import static cucumber.api.groovy.EN.*


Given(~'^I am at the the edit profile page as a generator$'){ ->
    to EditGeneratorPage
    at EditGeneratorPage
}

Given(~'^I am at the the edit profile page as a collector$'){ ->
    to EditCollectorPage
    at EditCollectorPage
}

When(~'^Fill the collectors username field with "([^"]*)"$'){ String username ->
    page.fillCollectorUsername(username)
}

And(~'^the collectors password field with "([^"]*)"$'){String password ->
    page.fillCollectorPassword(password)
}

And(~'^the collectors email field with "([^"]*)"$'){String email ->
    page.fillCollectorEmail(email)
}

And(~'^I confirm the collectors edition$'){->
    page.confirmCollectorEdition()
}

When(~'^Fill the generators username field with "([^"]*)"$'){ String username ->
    page.fillGeneratorUsername(username)
}

And(~'^the generators password field with "([^"]*)"$'){String password ->
    page.fillGeneratorPassword(password)
}

And(~'^the generators email field with "([^"]*)"$'){String email ->
    page.fillGeneratorEmail(email)
}

And(~'^I confirm the generators edition$'){->
    page.confirmGeneratorEdition()
}

Then(~'^I see at the account settings page my new generator settings$'){->
    at GeneratorAccountConfigPage
}

Then(~'^I see at the account settings page my new collector settings$'){->
    at CollectorAccountConfigPage
}

Given(~'^Exists an user with the username "([^"]*)" in the system$'){String username ->
    ResidueGeneratorTestAuxilar.injectGenerator(username)
    oldUsername = username;
}

When(~'^I change the username to "([^"]*)"$'){String newUsername ->
    ResidueGeneratorTestAuxilar.updateUsername(newUsername, oldUsername)
    newUsername_ = newUsername
}

And(~'^the password to "([^"]*)"$'){String password ->
    ResidueGeneratorTestAuxilar.updatePassword(password, newUsername_)
}

And(~'^the email to "([^"]*)"$'){String email ->
    ResidueGeneratorTestAuxilar.updateEmail(email, newUsername_)
}

Then(~'^The user has new account settings$'){ ->
   assert ResidueGeneratorTestAuxilar.findGenerator(newUsername_) == null
   assert ResidueGeneratorTestAuxilar.findGenerator(oldUsername) != null
}