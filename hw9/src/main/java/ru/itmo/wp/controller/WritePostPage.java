package ru.itmo.wp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import ru.itmo.wp.domain.Post;
import ru.itmo.wp.domain.Role;
import ru.itmo.wp.form.PostData;
import ru.itmo.wp.form.validator.PostDataValidator;
import ru.itmo.wp.security.AnyRole;
import ru.itmo.wp.service.UserService;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Controller
public class WritePostPage extends Page {
    private final UserService userService;

    private final PostDataValidator postDataValidator;

    public WritePostPage(UserService userService, PostDataValidator postDataValidator) {
        this.userService = userService;
        this.postDataValidator = postDataValidator;
    }

    @AnyRole({Role.Name.WRITER, Role.Name.ADMIN})
    @GetMapping("/writePost")
    public String writePostGet(Model model) {
        model.addAttribute("postData", new PostData());
        return "WritePostPage";
    }

    @AnyRole({Role.Name.WRITER, Role.Name.ADMIN})
    @PostMapping("/writePost")
    public String writePostPost(@Valid @ModelAttribute("postData") PostData postData,
                                BindingResult bindingResult,
                                HttpSession httpSession) {
        if (bindingResult.hasErrors()) {
            return "WritePostPage";
        }
        Post post = new Post();
        post.setTitle(postData.getTitle());
        post.setText(postData.getText());
        userService.writePost(getUser(httpSession), post, postData.getTags().split("\\s+"));
        putMessage(httpSession, "You published new post");

        return "redirect:/myPosts";
    }
}
