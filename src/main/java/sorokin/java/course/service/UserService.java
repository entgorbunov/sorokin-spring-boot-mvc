package sorokin.java.course.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import sorokin.java.course.entity.User;

import java.util.*;
@Slf4j
@org.springframework.stereotype.Service
public class UserService implements Service<User, Long> {

    private final Map<Long, User> users = new HashMap<>();


    @Override
    public void delete(Long id) {
        log.info("Deleting user with id = {}", id);
        users.remove(id);
    }

    @Override
    public Optional<User> findById(Long aLong) {
        log.info("Finding user with id = {}", aLong);
        return Optional.ofNullable(users.get(aLong));
    }

    @Override
    public List<User> findAll() {
        log.info("Fetching all users");
        return new ArrayList<>(users.values());
    }

    @Override
    public Optional<User> update(User user) {
        log.info("Updating user with id: {}", user.getId());
        if (users.containsKey(user.getId())) {
            validateUser(user);
            users.put(user.getId(), user);
            return Optional.of(user);
        }
        log.warn("User not found for update, id: {}", user.getId());
        return Optional.empty();
    }

    @Override
    public User create(User user) {
        if (user.getId() == null) {
            user.setId(generateId());
        }
        validateUser(user);
        log.info("Creating new user with id: {}", user.getId());
        users.put(user.getId(), user);
        return user;
    }

    private Long generateId() {
        return users.keySet()
                .stream()
                .max(Long::compare)
                .orElse(0L) + 1;
    }

    private void validateUser(User user) {
        if (user.getName() == null || user.getName().trim().isEmpty()) {
            throw new IllegalArgumentException("User name cannot be empty");
        }
        if (user.getEmail() == null || !user.getEmail().matches("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$")) {
            throw new IllegalArgumentException("Invalid email format");
        }
        if (user.getAge() == null || user.getAge() < 1 || user.getAge() > 100) {
            throw new IllegalArgumentException("Invalid age");
        }
    }
}
