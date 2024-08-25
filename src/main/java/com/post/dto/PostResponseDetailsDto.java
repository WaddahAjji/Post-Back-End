package com.post.dto;

import java.util.HashSet;
import java.util.Set;

public class PostResponseDetailsDto {
    private Long id;
    private String text;

    private String username;



    public PostResponseDetailsDto() {
    }

    private Set<PostResponseDetailsDto> comment = new HashSet<PostResponseDetailsDto>();


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



    public Set<PostResponseDetailsDto> getComment() {
        return comment;
    }

    public void setComment(Set<PostResponseDetailsDto> comment) {
        this.comment = comment;
    }


}
