package com.post.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.post.base.BaseEntity;
import jakarta.persistence.*;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.function.Supplier;
import java.util.stream.Collectors;

@Entity
@Table(name = "post")
public class Post extends BaseEntity<Long> {

    private String text;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy="post", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval=true)
    private Set<PostLike> postLike = new HashSet<PostLike>();;

    @ManyToOne(fetch = FetchType.LAZY, optional=true)
    @JoinColumn(name="parent_id")
    @JsonBackReference
    private Post parent;

    @OneToMany(mappedBy="parent", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval=true)

    private Set<Post> comment = new HashSet<Post>();



    public Post() {
    }
    public Post(String text, User user, Post parent) {
        this.text = text;
        this.user = user;
        this.parent = parent;
    }
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Set<Post> getComment() {
        return comment;
    }

    public void setComment(Set<Post> comment) {
        this.comment = comment;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Post getParent() {
        return parent;
    }

    public void setParent(Post parent) {
        this.parent = parent;
    }

    public Set<PostLike> getPostLike() {
        return postLike;
    }

    public void setPostLike(Set<PostLike> postLike) {
        this.postLike = postLike;
    }

    public String getUserName(){
        return this.user.getUsername();
    }
/*
public Set<String> getPostLikeAsStringSet(){
return   this.postLike.stream().map(like->like.getUser().getUsername()).collect(Collectors.toSet());
}

*/

}
