package NotificationSystem.service;

import NotificationSystem.Dto.ChannelType;
import NotificationSystem.Dto.Notification;
import NotificationSystem.Dto.UserPreference;
import NotificationSystem.channel.NotificationChannel;
import NotificationSystem.factory.NotificationChannelFactory;

public class NotificationDispatcher {
    private final UserPreferenceService userPreferenceService;

    public NotificationDispatcher(UserPreferenceService userPreferenceService) {
        this.userPreferenceService = userPreferenceService;
    }

    public void dispatch(Notification notification) {
        UserPreference preference = userPreferenceService.getPreference(notification.getUserId());

        for (ChannelType channelType : preference.getPreferredChannels()) {
            NotificationChannel channel = NotificationChannelFactory.getChannel(channelType);
            channel.send(notification);
        }
    }
}
