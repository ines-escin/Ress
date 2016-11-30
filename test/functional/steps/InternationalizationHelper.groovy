package steps

/**
 * Created by Leonardo on 23/11/2016.
 */



import grails.util.Holders
import org.springframework.context.i18n.LocaleContextHolder

@Singleton
public class InternationalizationHelper {
    def messageSource = Holders.grailsApplication.mainContext.getBean('messageSource');

    public String getMessage(String key, Object[] args = null)
    {
        String message = messageSource.getMessage(key, args, LocaleContextHolder.getLocale());

        return message
    }
}