package com.post.service;

import com.post.base.BaseService;
import com.post.dto.PostRequestDto;
import com.post.model.Post;
import com.post.repository.PostRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PostService extends BaseService<Post,Long> {

    @Autowired
    private PostRepository postRepository;


    @Cacheable(value = "findByIdCache",key = "#id")
    public
    Optional<Post> findByIdCache(Long id) {
        return postRepository.findById(id);
    }
    @Caching(evict = {@CacheEvict(value = {"findByIdCache"},key = "#post.id" , allEntries = true)})
    public Post updateCache(Post post) {
        return postRepository.save(post);
    }


   public Optional<Post> findByParentId(Long parentId)
    {
        return postRepository.findByParentId(parentId);
    }
}
