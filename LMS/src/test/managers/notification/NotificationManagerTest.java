package test.managers.notification;

import managers.notification.Notification;
import managers.notification.NotificationManager;
import org.junit.Test;
import org.junit.Before;
import org.junit.After;

/**
* NotificationManager Tester.
*
* @author <Authors name>
* @since <pre>Sep 25, 2014</pre>
* @version 1.0
*/
public class NotificationManagerTest {

@Before
public void before() throws Exception {
}

@After
public void after() throws Exception {
}

/**
*
* Method: submitNotification(Notification notification)
*
*/
@Test
public void testSubmitNotification() throws Exception {
    for (int i = 0; i < 5; i++) {
        NotificationManager.submitNotification(new Notification("Hi", "testing right now", "bahus.vel@gmail.com"));
        System.out.println("Send the notification to queue");
    }
    NotificationManager.shutdown();
    while (!NotificationManager.isTerminated());
}

/**
*
* Method: sendEmail(String subject, String message, String... to)
*
*/
@Test
public void testSendEmail() throws Exception {
//TODO: Test goes here...
}

/**
*
* Method: main(String[] args)
*
*/
@Test
public void testMain() throws Exception {
//TODO: Test goes here...
}


/**
*
* Method: emailTest()
*
*/
@Test
public void testEmailTest() throws Exception {
//TODO: Test goes here...
/*
try {
   Method method = NotificationManager.getClass().getMethod("emailTest");
   method.setAccessible(true);
   method.invoke(<Object>, <Parameters>);
} catch(NoSuchMethodException e) {
} catch(IllegalAccessException e) {
} catch(InvocationTargetException e) {
}
*/
}

}
