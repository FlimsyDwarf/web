package ru.itmo.wp.service;

import org.springframework.stereotype.Service;
import ru.itmo.wp.domain.Comment;
import ru.itmo.wp.repository.CommentRepository;

import java.util.List;

@Service
public class CommentService {

	private final CommentRepository commentRepository;

	public CommentService(CommentRepository commentRepository) {
		this.commentRepository = commentRepository;
	}

	public Comment findById(long id) {
		return commentRepository.findById(id);
	}

	public List<Comment> findAll() {
		return commentRepository.findAll();
	}
}