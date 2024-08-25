package com.post.dto;

public class PostLikeResponseDto {

    private Long id;
    private String username;

    public PostLikeResponseDto() {
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


}
