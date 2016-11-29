package support

import br.ufpe.cin.ines.ress.PickupRequest
import br.ufpe.cin.ines.ress.User
import geb.Browser
import geb.binding.BindingUpdater
import org.codehaus.groovy.grails.test.support.GrailsTestRequestEnvironmentInterceptor

import static cucumber.api.groovy.Hooks.*

Before () {
    bindingUpdater = new BindingUpdater(binding, new Browser())
    bindingUpdater.initialize()
    scenarioInterceptor = new GrailsTestRequestEnvironmentInterceptor (appCtx)
    scenarioInterceptor.init ()
}

After () {
    scenarioInterceptor.destroy ()
    bindingUpdater.remove ()
    PickupRequest.executeUpdate('delete from PickupRequest')
}
