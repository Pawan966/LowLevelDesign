package TaskManagementSystem.model;

import java.util.UUID;

public class User {
    private final String userId;
    private final String name;
    private final String email;

    public User(String email, String name) {
        this.userId = UUID.randomUUID().toString();
        this.name = name;
        this.email = email;
    }

    public String getUserId() {
        return userId;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }
}
