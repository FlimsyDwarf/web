package ru.itmo.wp.controller;

import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;
import ru.itmo.wp.domain.Comment;
import ru.itmo.wp.domain.Post;
import ru.itmo.wp.domain.User;
import ru.itmo.wp.exception.ValidationException;
import ru.itmo.wp.service.JwtService;
import ru.itmo.wp.service.PostService;
import ru.itmo.wp.service.UserService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/1")
public class PostController {
    private final PostService postService;

    private final UserService userService;

    private final JwtService jwtService;

    public PostController(PostService postService, UserService userService, JwtService jwtService) {
        this.postService = postService;
        this.userService = userService;
        this.jwtService = jwtService;
    }

    @GetMapping("posts")
    public List<Post> findPosts() {
        return postService.findAll();
    }

    @GetMapping("post")
    public Post findById(@RequestParam long postId) {
        return postService.findById(postId);
    }

    @PostMapping("posts/writePost")
    public void writePost(@RequestParam String jwt, @Valid @RequestBody Post post,
                              BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new ValidationException(bindingResult);
        }
        User user = jwtService.find(jwt);
        if (user == null) {
            bindingResult.addError(new ObjectError("no-user", "access denied"));
            throw new ValidationException(bindingResult);
        }
        userService.writePost(post, user);
    }

    @PostMapping("posts/writeComment")
    public void writeComment(@RequestParam String jwt, @Valid @RequestBody Comment comment,
                           @RequestParam long postId,
                           BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new ValidationException(bindingResult);
        }
        User user = jwtService.find(jwt);
        if (user == null) {
            bindingResult.addError(new ObjectError("no-user", "access denied"));
            throw new ValidationException(bindingResult);
        }
        Post post = null;
        try {
            post = postService.findById(postId);
        } catch (NumberFormatException ignored) {}
        if (post == null) {
            bindingResult.addError(new ObjectError("no-post", "post doesn't exist"));
            throw new ValidationException(bindingResult);
        }
        comment.setUser(user);
        comment.setPost(post);
        post.addComment(comment);
        postService.writeComment(post, comment);
//        userService.writePost(post, user);
    }
}
