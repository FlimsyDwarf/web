package ru.itmo.wp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.itmo.wp.domain.Comment;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {
	Comment findById(long id);

	List<Comment> findAll();
}
