package NotificationSystem.channel;

import NotificationSystem.Dto.Notification;

public class PushNotificationChannel implements  NotificationChannel {

    @Override
    public void send(Notification notification) {
        System.out.println("Sending push notification to user: " + notification.getUserId() + " " + notification.getMessage());
    }
}
