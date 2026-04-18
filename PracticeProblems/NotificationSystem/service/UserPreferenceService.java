package NotificationSystem.service;

import NotificationSystem.Dto.ChannelType;
import NotificationSystem.Dto.UserPreference;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

public class UserPreferenceService {
    private final Map<String, UserPreference> userPreferences = new ConcurrentHashMap<>();

    public void savePreference(UserPreference userPreference) {
        userPreferences.put(userPreference.getUserId(), userPreference);
    }

    public UserPreference getPreference(String userId) {
        return userPreferences.getOrDefault(userId, new UserPreference(userId, Set.of(ChannelType.EMAIL)));
    }
}
