package mail;

import br.ufpe.cin.ines.ress.PickupRequest;

import java.util.Date;
import java.util.Properties;
import javax.mail.Address;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 * Created by danielmaida on 22/10/15.
 */
public class MailService {

    public MailService()
    {

    }

    public static boolean sendEmail(String addressToSend,  String name, Date date, double amount)
    {
        try {

            Properties props = new Properties();

            props.put("mail.smtp.host", "smtp.gmail.com");
            props.put("mail.smtp.socketFactory.port", "465");
            props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
            props.put("mail.smtp.auth", "true");
            props.put("mail.smtp.port", "465");

            Session session = Session.getDefaultInstance(props,
                    new javax.mail.Authenticator() {
                        protected PasswordAuthentication getPasswordAuthentication()
                        {
                            return new PasswordAuthentication("alertadecoleta@gmail.com", "coletorressines");
                        }
                    });


            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("alertadecoleta@gmail.com")); //Remetente

            Address[] toUser = InternetAddress //Destinatário(s)
                    .parse(addressToSend);

            message.setRecipients(Message.RecipientType.TO, toUser);
            message.setSubject("Solicitação de coleta de " + name);
            message.setText("Olá, uma nova coleta de " + amount + " litros solicitada por " + name + " foi aberta agora");

            Transport.send(message);

            return true;

        } catch (MessagingException e) {
            e.printStackTrace();
            return false;
        }

    }


}
