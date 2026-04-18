package NotificationSystem.factory;

import NotificationSystem.Dto.ChannelType;
import NotificationSystem.channel.EmailNotificationChannel;
import NotificationSystem.channel.NotificationChannel;
import NotificationSystem.channel.PushNotificationChannel;
import NotificationSystem.channel.SmsNotificationChannel;

public class NotificationChannelFactory {
    public static NotificationChannel getChannel(ChannelType type) {
        return switch (type) {
            case EMAIL -> new EmailNotificationChannel();
            case SMS -> new SmsNotificationChannel();
            case PUSH -> new PushNotificationChannel();
        };
    }
}
