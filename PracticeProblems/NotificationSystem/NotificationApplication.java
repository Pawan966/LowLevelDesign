package NotificationSystem;

import NotificationSystem.Dto.Notification;
import NotificationSystem.service.NotificationService;

public class NotificationApplication {
    private final NotificationService notificationService;

    public NotificationApplication(NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    public void sendNotification(Notification notification) {
        notificationService.sendNotification(notification);
    }
}
