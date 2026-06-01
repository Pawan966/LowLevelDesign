package TaskManagementSystem.repository;

import TaskManagementSystem.model.User;

import java.util.Optional;

public interface UserRepository {
    void saveUser(User user);
    Optional<User> findById(String userId);
}
