package com.yourBouquet.entity;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

import java.util.Date;

@Entity
@Table(name = "COMMENT")
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class Comment implements Serializable{
    @Id
    @SequenceGenerator(name = "commentCommentIdSeq", sequenceName = "comment_comment_id_seq", allocationSize = 1, initialValue = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "commentCommentIdSeq")
    @Column(name = "comment_id", nullable = false)
    @Getter
    @Setter
    private Integer commentId;

    @Column(name = "text", nullable = false)
    @Getter
    @Setter
    private String text;

    @Column(name = "fname", nullable = false)
    @Getter
    @Setter
    private String fname;

    @Column(name = "sname", nullable = false)
    @Getter
    @Setter
    private String sname;

    @Column(name = "date", nullable = false)
    @Getter
    @Setter
    private Date date;

    @Override
    public String toString() {
         return "{" +
            "\"text\" : \"" + text + '\"' + ", " +
            "\"fname\" : \"" + fname + '\"' + ", " +
            "\"sname\" : \"" + sname + '\"' + ", " +
            "\"date\" : \"" + date + '\"' +
            '}';
    }

}

