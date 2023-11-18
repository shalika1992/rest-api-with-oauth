package com.assignment.restapi.controller;

import com.assignment.restapi.entity.Post;
import com.assignment.restapi.restclient.RestClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/postService")
public class PostController {
    private static final Logger logger = LoggerFactory.getLogger(PostController.class);

    @Autowired
    RestClient restClient;

    @RequestMapping(value = "/post", method = RequestMethod.GET, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Post> getPostList() throws Exception {
        List<Post> postList;
        try {
            postList = restClient.processRestCall("posts", Post.class);
        } catch (Exception e) {
            logger.error("Exception in getPostList() method ", e);
            throw e;
        }
        return postList;
    }

    @RequestMapping(value = "/post/{userId}", method = RequestMethod.GET, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Post getPostByUserId(@PathVariable("userId") String userId) throws Exception {
        Post post;
        try {
            List<Post> postList = restClient.processRestCall("posts", Post.class);
            post = postList.stream().filter(p -> p.getUserId() == Integer.parseInt(userId)).findFirst().orElse(null);
        } catch (Exception e) {
            logger.error("Exception in getPostByUserId() method ", e);
            throw e;
        }
        return post;
    }
}
