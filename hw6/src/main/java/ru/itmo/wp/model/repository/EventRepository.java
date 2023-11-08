package ru.itmo.wp.model.repository;

import ru.itmo.wp.model.domain.Event;

public interface EventRepository extends BasicRepository<Event> {

	Event findByUserId(long userId);

	void save(Event event);
}
