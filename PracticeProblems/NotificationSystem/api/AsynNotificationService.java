package NotificationSystem.api;

import NotificationSystem.Dto.Notification;
import NotificationSystem.service.NotificationDispatcher;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class AsynNotificationService {
    private final NotificationDispatcher notificationDispatcher;
    private final ExecutorService executorService;

    public  AsynNotificationService(NotificationDispatcher notificationDispatcher) {
        this.notificationDispatcher = notificationDispatcher;
        this.executorService = Executors.newFixedThreadPool(10);
    }

    public void sendNotification(Notification notification) {
        executorService.submit(() -> notificationDispatcher.dispatch(notification));
    }
}
