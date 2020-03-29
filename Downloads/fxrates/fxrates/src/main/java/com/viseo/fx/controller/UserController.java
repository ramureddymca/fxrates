package com.viseo.fx.controller;

import com.viseo.fx.exception.InvalidUserRequestException;
import com.viseo.fx.model.User;
import com.viseo.fx.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    // Handler method to produce JSON response
    @GetMapping(path = "/profile/{userId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getUserById(@PathVariable("userId") String userId) {

        /* validate userId parameter */
        if (userId.isEmpty() || !userId.matches("\\d+")) {
            throw new InvalidUserRequestException();
        }

        User user = userService.getUserById(userId);
        if (null == user) {
            // throw new UserNotFoundException();
            return new ResponseEntity<>("Requested User not found", HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<User>(user, HttpStatus.OK);
    }

}
