package com.yourBouquet.repository;

import com.yourBouquet.entity.Comment;
import com.yourBouquet.entity.Subscriber;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface CommentRepo extends JpaRepository<Comment, Integer> {

    List<Comment> findAll();
    Comment getByCommentId(Integer commentId);

    Comment save(Comment comment);
    @Transactional
    void deleteByCommentId(Integer commentId);
}
