package managers.notification;

import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.Email;
import org.apache.commons.mail.SimpleEmail;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * Created by denislavrov on 9/25/14.
 */
public class NotificationManager {
    private static final String SERVER = "smtp.gmail.com";
    private static final int PORT = 465;
    private static String username = "USERNAME";
    private static String password = "PASSWORD";
    private static final String PROPERTIES = "email.properties";
    private static final String FROM = "LibraryManagementSystem@gmail.com";
    private static final int THREADS = 4;

    static{
        Properties prop = new Properties();
        InputStream propSteam = NotificationManager.class.getClassLoader().getResourceAsStream(PROPERTIES);
        assert  propSteam != null;
        try {
            prop.load(propSteam);
        } catch (IOException e) {
            e.printStackTrace();
        }
        username = prop.getProperty("username");
        password = prop.getProperty("password");
    }

    public int notificationsLeft(){
        return ((ThreadPoolExecutor) service).getQueue().size();
    }

    private static ExecutorService service = Executors.newFixedThreadPool(THREADS);

    public static void submitNotification(Notification notification){
        service.execute(notification);
    }

    public static void shutdown(){
        service.shutdown();
    }

    public static boolean isTerminated(){
        return service.isTerminated();
    }

    public static String sendEmail(String subject, String message, String... to){
        Email email = new SimpleEmail();
        email.setHostName(SERVER);
        email.setSmtpPort(PORT);
        email.setAuthenticator(new DefaultAuthenticator(username, password));
        email.setSSLOnConnect(true);
        try {
            email.setFrom(FROM);
            email.setSubject(subject);
            email.setMsg(message);
            email.addTo(to);
            email.send();
        } catch (Exception e){
            e.printStackTrace();
            return "Email send failed";
        }
        return "Email has been sent";
    }

}
