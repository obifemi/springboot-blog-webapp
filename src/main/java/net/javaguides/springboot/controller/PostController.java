package net.javaguides.springboot.controller;

import jakarta.validation.Valid;
import net.javaguides.springboot.dto.PostDto;
import net.javaguides.springboot.service.PostService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
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
    public String createPost(@Valid @ModelAttribute("post") PostDto postDto,
                             BindingResult result,
                             Model model){
        if (result.hasErrors()) {
            model.addAttribute("post", postDto);
            return "admin/create_post";
        }
        postDto.setUrl(createUrl(postDto.getTitle()));
        postService.createPost(postDto);
        return "redirect:/admin/posts";
    }

    private String createUrl( String postTitle){
        String title = postTitle.trim().toLowerCase();
        String url = title.replaceAll("\\s+", "-");
        url = url.replaceAll("[^A-Za-z0-9]","-");
        return url;
    }


    @GetMapping("/admin/posts/{postId}/edit")
    public String editPostForm(@PathVariable("postId") Long postId, Model model){
        PostDto postDto = postService.findPostById(postId);
        model.addAttribute("post", postDto);
        return "admin/edit_post";

    }

    @PostMapping("/admin/posts/{postId}")
    public String updatePost(@PathVariable("postId") Long postId, @Valid @ModelAttribute("post") PostDto post, BindingResult result, Model model){
        if(result.hasErrors()){
            model.addAttribute("post", post);
            return "/admin/edit_post";
        }
        post.setId(postId);
        postService.editPost(post);
        return "redirect:/admin/posts";
    }



    @GetMapping("admin/posts/{postId}/delete")
    public String deletePost(@PathVariable("postId")Long postId){
        postService.deletePost(postId);
        return "redirect:/admin/posts";
    }


    @GetMapping("/admin/posts/{postUrl}/view")
    public String viewPost(@PathVariable("postUrl") String postUrl, Model model){
        PostDto postDto = postService.findPostByUrl(postUrl);
        model.addAttribute("post", postDto);
        return "admin/view_post";

    }



}
