package com.post.dto;

import jakarta.validation.constraints.NotBlank;

public class PostRequestDto {

    private Long parentId;
    @NotBlank(message = "The text is required.")
    private String text;


    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
