package drive.verification;

import java.util.Properties;

import javax.mail.*;
import javax.mail.internet.*;



public class Verification {
    private static final String username = "bsef19a532@pucit.edu.pk";
    private static final String password = "Masterrace1";

    private static final String host = "smtp.gmail.com";
    private static final String port = "587";

    private static Session session;

    static {
        Properties prop = new Properties();
        prop.put( "mail.smtp.host", host );
        prop.put( "mail.smtp.port", port );
        prop.put( "mail.smtp.auth", "true" );
        prop.put( "mail.smtp.starttls.enable", "true" );


        session = Session.getInstance( prop, 
            new javax.mail.Authenticator() {
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(username, password);
                }
            }
        );
    }

    public static boolean sendMail( String email ) {

        try
        {
            // construct message
            Message message = new MimeMessage( session );
            message.setFrom( new InternetAddress( username ) );
            message.setRecipient( Message.RecipientType.TO, InternetAddress.parse( email )[0] );
            message.setSubject( "Email Verification - Google Drive Clone" );
            message.setText( "Hello from Google Drive" );

            // send email
            Transport.send( message );

        } catch (MessagingException e)
        {
            e.printStackTrace();
        }

        return false;
    }

}
