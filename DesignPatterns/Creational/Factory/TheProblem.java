package Creational.Factory;

/*
* The Factory Pattern is a Creational Design Pattern that provides an interface/method for creating objects without exposing the instantiation logic to the client.
  Instead of using new directly, you delegate object creation to a factory.

  Simple Factory	A single class with a method that returns objects based on input
  Factory Method	Subclasses decide which class to instantiate (uses inheritance)
  Abstract Factory	Factory of factories — creates families of related objects

* */

// client code is deciding which object to create, which should not be the case
class NotificationService {

    public void sendNotification(String type, String message) {

        if (type.equals("EMAIL")) {
//            EmailNotification email = new EmailNotification();
//            email.configure();     // SMTP setup
//            email.authenticate();  // credentials
//            email.send(message);

        } else if (type.equals("SMS")) {
//            SmsNotification sms = new SmsNotification();
//            sms.configure();       // Twilio setup
//            sms.authenticate();    // API key
//            sms.send(message);

        } else if (type.equals("PUSH")) {
//            PushNotification push = new PushNotification();
//            push.configure();      // Firebase setup
//            push.authenticate();   // token
//            push.send(message);
        }
    }
}
public class TheProblem {
}
