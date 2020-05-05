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

    public Comment addComment(String fname, String lname, String text){
        Date date = new Date();

        Comment comment = new Comment();
        comment.setFname(fname);
        comment.setSname(lname);
        comment.setText(text);
        comment.setDate(date);

        Comment sameComment = commentRepo.getByCommentId(comment.getCommentId());

        if (sameComment != null) comment = sameComment;
        else comment = commentRepo.save(comment);

        return comment;
    }
}
