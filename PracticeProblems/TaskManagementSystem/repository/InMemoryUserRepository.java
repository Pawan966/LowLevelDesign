package TaskManagementSystem.repository;

import TaskManagementSystem.model.User;

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

public class InMemoryUserRepository implements  UserRepository {
    private final Map<String, User> users = new ConcurrentHashMap<>();

    @Override
    public void saveUser(User user) {
        users.put(user.getUserId(), user);
    }

    @Override
    public Optional<User> findById(String userId) {
        return Optional.ofNullable(users.get(userId));
    }
}
