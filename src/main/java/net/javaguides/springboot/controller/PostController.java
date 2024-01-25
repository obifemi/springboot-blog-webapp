package net.javaguides.springboot.controller;

import net.javaguides.springboot.dto.PostDto;
import net.javaguides.springboot.service.PostService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class PostController {
    private PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    //create handler method, GET request, model, view
    @GetMapping("/admin/posts")
    public String posts(Model model){
        List<PostDto> posts= postService.findAll();
        model.addAttribute("posts",posts);
    return "/admin/posts";

}

    @GetMapping("/admin/posts/newpost")
    public String newPost(Model model){
        PostDto postDto = new PostDto();
        model.addAttribute("post", postDto);

        return "/admin/create_post";

    }

    @PostMapping("/admin/posts")
    public String createPost(@ModelAttribute PostDto postDto){
        postDto.setUrl(createUrl(postDto.getTitle()));
        postService.createPost(postDto);
        return "redirect:/admin/posts";
    }

    private String createUrl(String postTitle){
        String title = postTitle.trim().toLowerCase();
        String url = title.replaceAll("\\s+", "-");
        url = url.replaceAll("[^A-Za-z0-9]","-");
        return url;
    }



}
