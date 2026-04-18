package NotificationSystem.channel;

import NotificationSystem.Dto.Notification;

public interface NotificationChannel {
    void send(Notification notification);
}
