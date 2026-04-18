package NotificationSystem.channel;

import NotificationSystem.Dto.Notification;

public class EmailNotificationChannel implements  NotificationChannel {
    @Override
    public void send(Notification notification) {
        System.out.println("Sending email to user: " + notification.getUserId() + " " + notification.getMessage());
    }
}
