package com.viseo.fx.service;

import com.viseo.fx.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class UserService {

    private static final Logger logger = LoggerFactory.getLogger(RatesService.class);
    // Dummy database. Initialize with some dummy values.
    private static Map<String, User> users;

    {
        users = new HashMap<>();
        users.put("101", new User("101", "A", "abc@gmail.com"));
        users.put("102", new User("102", "B", "bca@gmail.com"));
        users.put("103", new User("103", "C", "cab@gmail.com"));
    }

    /**
     * Return user object for given userId from dummy database. If user is
     * not found for userId, returns null.
     *
     * @param userId
     * @return user object for given userId
     */
    public User getUserById(String userId) {
        logger.info("Requested User not found");
        return users.get(userId);
    }

}
