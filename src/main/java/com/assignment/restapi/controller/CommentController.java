package com.assignment.restapi.controller;

import com.assignment.restapi.entity.Comment;
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
@RequestMapping("/api/commentService")
public class CommentController {
    private static final Logger logger = LoggerFactory.getLogger(CommentController.class);

    @Autowired
    RestClient restClient;

    @RequestMapping(value = "/comment", method = RequestMethod.GET, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Comment> getCommentList() throws Exception {
        List<Comment> commentList;
        try {
            commentList = restClient.processRestCall("comments", Comment.class);
        } catch (Exception e) {
            logger.error("Exception in getCommentList() method ", e);
            throw e;
        }
        return commentList;
    }

    @RequestMapping(value = "/comment/{postId}", method = RequestMethod.GET, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Comment getCommentByPostId(@PathVariable("postId") String postId) throws Exception {
        Comment comment;
        try {
            List<Comment> commentList = restClient.processRestCall("comments", Comment.class);
            comment = commentList.stream().filter(c -> c.getPostId() == Integer.parseInt(postId)).findFirst().orElse(null);
        } catch (Exception e) {
            logger.error("Exception in getCommentByPostId() method ", e);
            throw e;
        }
        return comment;
    }
}
