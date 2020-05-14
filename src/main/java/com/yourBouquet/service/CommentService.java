package com.yourBouquet.service;

import com.yourBouquet.entity.Comment;
import com.yourBouquet.entity.Subscriber;
import com.yourBouquet.repository.CommentRepo;
import com.yourBouquet.repository.SubscriberRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class CommentService {
    @Autowired
    CommentRepo commentRepo;

    public Comment addComment(Comment comment){
        Date date = new Date();

        comment.setDate(date);

        return commentRepo.save(comment);
    }
}
