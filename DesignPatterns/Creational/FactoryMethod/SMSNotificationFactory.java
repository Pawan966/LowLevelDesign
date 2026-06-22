package Creational.FactoryMethod;

public class SMSNotificationFactory implements  NotificationFactory {
    @Override
    public Notification createNotification() {
        return new SMSNotification();
    }
}
