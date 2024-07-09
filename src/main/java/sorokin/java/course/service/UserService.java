package sorokin.java.course.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sorokin.java.course.entity.User;
import sorokin.java.course.exceptions.EntityNotFoundException;
import sorokin.java.course.exceptions.InvalidEntityException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

@org.springframework.stereotype.Service
public class UserService {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserService.class);
    private static final Map<Long, User> USER_MAP = new HashMap<>();
    private static final AtomicLong ID_COUNTER = new AtomicLong(1);


    public void delete(Long id) {
        LOGGER.info("Deleting user with id = {}", id);
        if (!USER_MAP.containsKey(id)) {
            LOGGER.error("User not found for delete with id: {}", id);
            throw new EntityNotFoundException("User not found for delete with id: " + id);
        }
        USER_MAP.remove(id);
    }

    public User findById(Long aLong) {
        LOGGER.info("Finding user with id = {}", aLong);
        User user = USER_MAP.get(aLong);
        if (user == null) {
            LOGGER.error("User not found with id: {}", aLong);
            throw new EntityNotFoundException("User not found with id: " + aLong);
        }
        return user;
    }

    public List<User> findAll() {
        LOGGER.info("Fetching all users");
        return new ArrayList<>(USER_MAP.values());
    }

    public User update(User user) {
        LOGGER.info("Updating user with id: {}", user.getId());
        if (USER_MAP.containsKey(user.getId())) {
            validateUser(user);
            USER_MAP.put(user.getId(), user);

        } else {
            LOGGER.error("User not found for update, id: {}", user.getId());
            throw new EntityNotFoundException("User not found for update with id" + user.getId());
        }
        return user;
    }

    public User create(User user) {
        if (user.getId() == null) {
            user.setId(generateId());
        }
        validateUser(user);
        LOGGER.info("Creating new user with id: {}", user.getId());
        USER_MAP.put(user.getId(), user);
        return user;
    }

    private Long generateId() {
        return ID_COUNTER.getAndIncrement();
    }

    private void validateUser(User user) {
        if (user.getName() == null || user.getName().trim().isEmpty()) {
            throw new InvalidEntityException("User name cannot be empty");
        }
        if (user.getEmail() == null || !user.getEmail().matches("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$")) {
            throw new InvalidEntityException("Invalid email format");
        }
        if (user.getAge() == null || user.getAge() < 1 || user.getAge() > 100) {
            throw new InvalidEntityException("Invalid age");
        }
    }
}
