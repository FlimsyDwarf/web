package ru.itmo.wp.repository;

import ru.itmo.wp.domain.Comment;

public interface CommentRepository {
    Comment findById(long id);
}
