package Splitwise.service;

import Splitwise.models.User;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class UserService {
    private final Map<String, User> users;

    public UserService() {
        this.users = new ConcurrentHashMap<>();
    }

    public void addUser(User user) {
        users.put(user.getId(), user);
    }

    public User getUser(String id) {
        return users.get(id);
    }

    public boolean userExists(String id) {
        return users.containsKey(id);
    }
}
