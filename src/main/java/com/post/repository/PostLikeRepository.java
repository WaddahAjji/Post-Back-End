package com.post.repository;

import com.post.base.BaseRepository;
import com.post.model.Post;
import com.post.model.PostLike;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PostLikeRepository extends BaseRepository<PostLike,Long> {

    public Optional<PostLike> findPostLikeByPostIdAndUserId(Long postId, Long userId);

    List<PostLike> findPostLikeByPostId(Long postId);
}
