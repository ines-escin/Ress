package br.ufpe.cin.ines.ress

import org.springframework.security.core.Authentication
import org.springframework.security.core.context.SecurityContextHolder

class DashboardController {

    def springSecurityService;

    def index() {
         User user = (User)springSecurityService.currentUser;
            if(user.getAuthorities().contains(Role.findByAuthority('ROLE_GENERATOR')))
                redirect(controller: "generatorDashboard", action: "index")
            if(user.getAuthorities().contains(Role.findByAuthority('ROLE_COLLECTOR')))
                redirect(controller: "collectorDashboard", action: "index")
    }

}
