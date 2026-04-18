package NotificationSystem.channel;

import NotificationSystem.Dto.Notification;

public class SmsNotificationChannel implements  NotificationChannel {
    @Override
    public void send(Notification notification) {
        System.out.println("Sending sms to user: " + notification.getUserId() + " " + notification.getMessage());
    }
}
