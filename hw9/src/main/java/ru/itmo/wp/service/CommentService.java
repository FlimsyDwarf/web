package ru.itmo.wp.service;

import ru.itmo.wp.domain.Comment;
import ru.itmo.wp.repository.CommentRepository;

public class CommentService {

    private final CommentRepository commentRepository;

    public CommentService(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    public Comment findById(long id) {
        return commentRepository.findById(id);
    }
}
