package net.javaguides.springboot.service;

import net.javaguides.springboot.dto.PostDto;

import java.util.List;

public interface PostService {
    List<PostDto> findAll();
    void createPost(PostDto postDto);

    PostDto findPostById(Long postId);

    void editPost(PostDto postDto);

    void deletePost(Long postId);

  PostDto findPostByUrl(String postUrl);

}
