package com.post.repository;

import com.post.base.BaseRepository;
import com.post.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PostRepository extends BaseRepository<Post,Long> {

   Optional<Post> findByParentId(Long parentId);
}
