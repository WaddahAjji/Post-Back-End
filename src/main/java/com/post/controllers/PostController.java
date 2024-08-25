package com.post.controllers;

import com.post.dto.*;
import com.post.exception.NotFoundException;
import com.post.model.Post;
import com.post.model.PostLike;
import com.post.model.User;
import com.post.repository.PostRepository;
import com.post.security.jwt.JwtUtils;
import com.post.service.PostLikeService;
import com.post.service.PostService;
import com.post.service.UserService;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import java.util.*;


@RestController
@RequestMapping("/api/post")
public class PostController {
    @Autowired
    JwtUtils jwtUtils;
    @Autowired
    PostService postService;

    @Autowired
    UserService userService;
    @Autowired
    PostLikeService postLikeService;

    @PostMapping()
    public ResponseEntity<?> posts(@RequestBody PostRequestDto postRequestDto){
        Post createdPost;

            String username  = jwtUtils.getUserFromSecurityContext().getUsername();
             User user= userService.findByUsername(username)
                     .orElseThrow( ()-> new NotFoundException(" User With username  "+ username + " Not found "));
        if(postRequestDto.getParentId()!=null)
        {
            Post parent = postService.findById(postRequestDto.getParentId())
                    .orElseThrow(() -> new NotFoundException(" Post With Id "+ postRequestDto.getParentId()+ " Not found "));
            createdPost=    postService.insert(new Post(postRequestDto.getText(),user,parent));
        }
        else{
            createdPost=  postService.insert(new Post(postRequestDto.getText(),user,null));
        }
        ModelMapper modelMapper =new ModelMapper();
        PostResponseDto postResponseDto = modelMapper.map(createdPost, PostResponseDto.class);
        return ResponseEntity.ok().body(new MessageResponseDto(
                "Success",
                HttpStatus.OK.value(),postResponseDto));
    }


    @GetMapping()
    public ResponseEntity<?> posts(){
       ModelMapper modelMapper =new ModelMapper();
        TypeMap<Post, PostResponseDto> propertyMapper = modelMapper.createTypeMap(Post.class, PostResponseDto.class);
        List<PostResponseDto> postResponseDtoList = Arrays.asList(modelMapper.map(postService.findAll(), PostResponseDto[].class));
        return  ResponseEntity.ok()
               .body(new MessageResponseDto(
                       "Success",
                       HttpStatus.OK.value(), postResponseDtoList));
        }


    @GetMapping("/{id}")
    public ResponseEntity<?> posts(@PathVariable("id") Long postCommentId){
        ModelMapper modelMapper =new ModelMapper();
        TypeMap<Post, PostResponseDto> propertyMapper = modelMapper.createTypeMap(Post.class, PostResponseDto.class);
        propertyMapper.addMapping(Post::getUserName, PostResponseDto::setUsername);
        PostResponseDto postResponseDto = modelMapper.map(postService.findByIdCache(postCommentId), PostResponseDto.class);
        return  ResponseEntity.ok()
                .body( new MessageResponseDto(
                        "Success",
                        HttpStatus.OK.value(),postResponseDto));

    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updatePost(@PathVariable("id") Long id,@RequestBody PostRequestDto postRequestDto){
        Post post=postService.findById(id)
                .orElseThrow(() -> new NotFoundException(" Post With Id "+ id + " Not found "));
        post.setText(postRequestDto.getText());

        ModelMapper modelMapper =new ModelMapper();
        TypeMap<Post, PostResponseDto> propertyMapper = modelMapper.createTypeMap(Post.class, PostResponseDto.class);
        propertyMapper.addMapping(Post::getUserName, PostResponseDto::setUsername);
        PostResponseDto postResponseDto = modelMapper.map(postService.updateCache(post), PostResponseDto.class);
        return  ResponseEntity.ok()
                .body( new MessageResponseDto(
                        "Success",
                        HttpStatus.OK.value(),postResponseDto));

    }


    @PostMapping("/{id}/like-dislike")
    public ResponseEntity<?> like(@PathVariable("id") Long postCommentId){
        Post post=postService.findById(postCommentId)
                .orElseThrow(() -> new NotFoundException(" Post With Id "+ postCommentId + " Not found "));

        String username  = jwtUtils.getUserFromSecurityContext().getUsername();
        User user= userService.findByUsername(username)
                .orElseThrow( ()-> new NotFoundException(" User With username  "+ username + " Not found "));

        Optional<PostLike> postLike= postLikeService.findPostLikeByPostIdAndUserId(postCommentId,user.getId());
        if(postLike.isPresent())
        {
            postLikeService.delete(postLike.get().getId());
            return  ResponseEntity.ok()
                    .body( new MessageResponseDto(
                            "Success",
                            HttpStatus.OK.value(),"Success dislike on post id " + postCommentId));
        }
        else{
            PostLike like= new PostLike();
            like.setPost(post);
            like.setUser(user);
            postLikeService.insert(like);
            return  ResponseEntity.ok()
                    .body( new MessageResponseDto(
                            "Success",
                            HttpStatus.OK.value(),"Success like on post id " + postCommentId));

        }
    }


    @GetMapping("/with-comments")
    public ResponseEntity<?> postsWithComments(){
        ModelMapper modelMapper =new ModelMapper();
        TypeMap<Post, PostResponseDetailsDto> propertyMapper = modelMapper.createTypeMap(Post.class, PostResponseDetailsDto.class);
        propertyMapper.addMapping(Post::getUserName, PostResponseDetailsDto::setUsername);
        List<PostResponseDetailsDto> postResponseDtoList = Arrays.asList(modelMapper.map(postService.findAll(), PostResponseDetailsDto[].class));
        return  ResponseEntity.ok()
                .body(new MessageResponseDto(
                        "Success",
                        HttpStatus.OK.value(), postResponseDtoList));

    }

    @GetMapping("/{id}/comments")
    public ResponseEntity<?> commentOnpost(@PathVariable("id") Long parentId){
        Post post=postService.findById(parentId)
                .orElseThrow(() -> new NotFoundException(" Post With Id "+ parentId + " Not found "));


        ModelMapper modelMapper =new ModelMapper();
        TypeMap<Post, PostResponseDto> propertyMapper = modelMapper.createTypeMap(Post.class, PostResponseDto.class);
        propertyMapper.addMapping(Post::getUserName, PostResponseDto::setUsername);

        PostResponseDto postResponseDto = modelMapper.map(postService.findByParentId(parentId), PostResponseDto.class);
        return  ResponseEntity.ok()
                .body( new MessageResponseDto(
                        "Success",
                        HttpStatus.OK.value(),postResponseDto));


    }

    @GetMapping("/{id}/likes")
    public ResponseEntity<?> likesOnpost(@PathVariable("id") Long postId){
        Post post=postService.findById(postId)
                .orElseThrow(() -> new NotFoundException(" Post With Id "+ postId + " Not found "));
        ModelMapper modelMapper =new ModelMapper();
        TypeMap<PostLike, PostLikeResponseDto> propertyMapper = modelMapper.createTypeMap(PostLike.class, PostLikeResponseDto.class);
        propertyMapper.addMapping(PostLike::getUserName, PostLikeResponseDto::setUsername);
        List<PostLikeResponseDto> postLikeResponseDto = Arrays.asList(modelMapper.map(post.getPostLike(), PostLikeResponseDto[].class));

        return  ResponseEntity.ok()
                .body( new MessageResponseDto(
                        "Success",
                        HttpStatus.OK.value(),postLikeResponseDto));


    }

}
