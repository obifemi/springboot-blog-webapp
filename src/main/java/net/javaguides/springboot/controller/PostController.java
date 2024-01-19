package net.javaguides.springboot.controller;

import net.javaguides.springboot.dto.PostDto;
import net.javaguides.springboot.service.PostService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

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

}
