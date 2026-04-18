package NotificationSystem;

import NotificationSystem.Dto.ChannelType;
import NotificationSystem.Dto.Notification;
import NotificationSystem.Dto.UserPreference;
import NotificationSystem.service.NotificationService;
import NotificationSystem.service.NotificationDispatcher;
import NotificationSystem.service.UserPreferenceService;

import java.util.Set;

public class ClientApplication {
    public static void main(String[] args) {
        UserPreferenceService userPreferenceService = new UserPreferenceService();
        userPreferenceService.savePreference(new UserPreference("1", Set.of(ChannelType.EMAIL, ChannelType.SMS)));

        NotificationDispatcher notificationDispatcher = new NotificationDispatcher(userPreferenceService);

        NotificationService notificationService = new NotificationService(notificationDispatcher);

        NotificationApplication notificationApplication = new NotificationApplication(notificationService);

        Notification notification = new Notification("1", "Hello");
        notificationApplication.sendNotification(notification);
    }
}
