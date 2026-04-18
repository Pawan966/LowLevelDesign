package NotificationSystem.service;

import NotificationSystem.Dto.Notification;

public class NotificationService {
    private final NotificationDispatcher notificationDispatcher;

    public  NotificationService(NotificationDispatcher notificationDispatcher) {
        this.notificationDispatcher = notificationDispatcher;
    }

    public void sendNotification(Notification notification) {
        notificationDispatcher.dispatch(notification);
    }
}
