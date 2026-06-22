package Creational.FactoryMethod;

public class EmailNotification implements  Notification {
    @Override
    public void sendNotification(String message) {
        System.out.println("Sending email notification: " + message);
    }
}
