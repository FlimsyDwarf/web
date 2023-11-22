package ru.itmo.wp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import ru.itmo.wp.domain.Notice;
import ru.itmo.wp.service.NoticeService;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@Controller
public class NoticePage extends Page {

    private final NoticeService noticeService;

    public NoticePage(NoticeService noticeService) {
        this.noticeService = noticeService;
    }

    @GetMapping("/notice")
    public String noticeGet(Model model) {
        model.addAttribute("notice", new Notice());
        return "NoticePage";
    }

    @PostMapping("/notice")
    public String add(@Valid Notice notice, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "NoticePage";
        }
        noticeService.save(notice);
        return "NoticePage";
    }
}
