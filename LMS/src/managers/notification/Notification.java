package managers.notification;

/**
 * Created by denislavrov on 9/25/14.
 */

public class Notification implements Runnable{
    private String subject;
    private String message;
    private String[] to;

    public Notification(String subject, String message, String... to) {
        this.subject = subject;
        this.message = message;
        this.to = to;
    }

    @Override
    public void run() {
        NotificationManager.sendEmail(subject, message, to);
        System.out.println("Notification Method Finish");
    }
}