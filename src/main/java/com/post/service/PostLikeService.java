package com.post.service;

import com.post.base.BaseService;
import com.post.model.PostLike;
import com.post.repository.PostLikeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PostLikeService extends BaseService<PostLike,Long> {

    @Autowired
    PostLikeRepository postLikeRepository;

    public Optional<PostLike> findPostLikeByPostIdAndUserId(Long postId, Long userId){
        return postLikeRepository.findPostLikeByPostIdAndUserId(postId,userId);
    }

    public List<PostLike> findPostLikeByPostId(Long postId ){
        return postLikeRepository.findPostLikeByPostId(postId);
    }
}
