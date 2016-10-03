package pages

import geb.Page

/**
 * Created by msb5 on 03/10/2016.
 */
class SignUpPage extends Page{
    def titulo = "ResS - Sign Up"
    static url = "ResS/signup/createUser"

    static at = {
        title ==~ titulo
    }

}
