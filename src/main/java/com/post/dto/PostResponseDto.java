package com.post.dto;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.post.model.Post;
import com.post.model.User;
import jakarta.persistence.*;

import javax.xml.stream.events.Comment;
import java.time.LocalDateTime;
import java.util.*;

public class PostResponseDto {


    private Long id;
    private String text;

    private String username;

    private LocalDateTime createdDate;

    public PostResponseDto() {
    }




    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }




    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDateTime createdDate) {
        this.createdDate = createdDate;
    }
}
