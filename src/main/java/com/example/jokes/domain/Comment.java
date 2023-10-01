package com.example.jokes.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Table(name="COMMENT")
public class Comment {

    @Id
    @GeneratedValue
    @Column(name = "COMMENT_ID")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "USER_ID")
    private User author;

    @Column(name = "COMMENT_CONTENT")
    private String commentContent;

    @Column(name = "CREATED")
    private LocalDateTime created;

    public Comment(User author, String commentContent) {
        this.author = author;
        this.commentContent = commentContent;
    }

    @PrePersist
    void setCreatedAt() {
        this.created = LocalDateTime.now();
    }
}