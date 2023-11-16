package ru.itmo.wp.model.repository;

import ru.itmo.wp.model.domain.Talk;

import java.util.List;

public interface TalkRepository extends BasicRepository<Talk> {
	void save(Talk talk);

	List<Talk> findAllById(String sourceId, String targetId);
}
