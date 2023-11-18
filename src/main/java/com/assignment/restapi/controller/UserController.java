package com.assignment.restapi.controller;

import com.assignment.restapi.entity.User;
import com.assignment.restapi.restclient.RestClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/userService")
public class UserController {
    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    RestClient restClient;

    @RequestMapping(value = "/user", method = RequestMethod.GET, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<User> getUserList() throws Exception {
        List<User> userList;
        try {
            userList = restClient.processRestCall("users", User.class);
        } catch (Exception e) {
            logger.error("Exception in getUserList() method ", e);
            throw e;
        }
        return userList;
    }
}
