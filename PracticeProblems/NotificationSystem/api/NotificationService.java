package NotificationSystem.api;

import NotificationSystem.Dto.Notification;
import NotificationSystem.service.NotificationDispatcher;

public class NotificationService {
    private final NotificationDispatcher notificationDispatcher;

    public  NotificationService(NotificationDispatcher notificationDispatcher) {
        this.notificationDispatcher = notificationDispatcher;
    }

    public void sendNotification(Notification notification) {
        notificationDispatcher.dispatch(notification);
    }
}
