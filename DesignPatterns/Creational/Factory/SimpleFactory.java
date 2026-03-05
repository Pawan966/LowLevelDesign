package Creational.Factory;

interface Notification {
    void notifyUser(String message);
}
class EmailNotification implements Notification {
    @Override
    public void notifyUser(String message) {
        // SMTP configuration, authentication, etc. hidden here
        System.out.println("Sending EMAIL: " + message);
    }
}

class SmsNotification implements Notification {
    @Override
    public void notifyUser(String message) {
        // Twilio configuration hidden here
        System.out.println("Sending SMS: " + message);
    }
}

class PushNotification implements Notification {
    @Override
    public void notifyUser(String message) {
        // Firebase configuration hidden here
        System.out.println("Sending PUSH: " + message);
    }
}
class NotificationFactory {

    // Private constructor — no one should instantiate this utility
    private NotificationFactory() {}

    public static Notification createNotification(String type) {

        return switch (type.toUpperCase()) {
            case "EMAIL" -> new EmailNotification();
            case "SMS"   -> new SmsNotification();
            case "PUSH"  -> new PushNotification();
            default -> throw new IllegalArgumentException(
                    "Unknown notification type: " + type
            );
        };
    }
}

// clean client code
class NotificationServiceSimpleFactory {

    public void sendNotification(String type, String message) {
        Notification notification = NotificationFactory.createNotification(type);
        notification.notifyUser(message);
    }
}

public class SimpleFactory {
    public static void main(String[] args) {
        NotificationServiceSimpleFactory service = new NotificationServiceSimpleFactory();
        service.sendNotification("EMAIL", "Hello");
    }
}
