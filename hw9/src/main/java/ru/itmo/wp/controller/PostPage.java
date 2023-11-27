package ru.itmo.wp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import ru.itmo.wp.domain.Comment;
import ru.itmo.wp.domain.Post;
import ru.itmo.wp.security.Guest;
import ru.itmo.wp.service.PostService;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Controller
public class PostPage extends Page {

    private final PostService postService;

    public PostPage(PostService postService) {
        this.postService = postService;
    }

    @Guest
    @GetMapping({"/post/{id}", "/post/"})
    public String postGet(Model model, @PathVariable(required = false) String id) {
        Post post = null;
        try {
            post = postService.findById(Long.parseLong(id));
            model.addAttribute("comments", post.getComments());
        } catch (NumberFormatException ignored) {
        }

        model.addAttribute("post", post);
        model.addAttribute("comment", new Comment());
        return "PostPage";
    }

    @PostMapping({"/post/{id}", "/post/", "/post"})
    public String postPost(@Valid @ModelAttribute("comment") Comment comment, BindingResult bindingResult,
                           @PathVariable(required = false) String id,
                            HttpSession session, Model model) {
        Post post = null;
        try {
            post = postService.findById(Long.parseLong(id));
        } catch (NumberFormatException ignored) {}

        if (bindingResult.hasErrors()) {
            model.addAttribute("post", post);
            return "PostPage";
        }
        if (post != null) {
            comment.setUser(getUser(session));
            postService.writeComment(post, comment);
            putMessage(session, "Comment is saved");
        }
        model.addAttribute("post", post);
        return "redirect:/post/{id}";
    }
}
