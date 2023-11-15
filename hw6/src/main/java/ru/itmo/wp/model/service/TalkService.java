package ru.itmo.wp.model.service;

import com.google.common.base.Strings;
import ru.itmo.wp.model.domain.Talk;
import ru.itmo.wp.model.exception.ValidationException;
import ru.itmo.wp.model.repository.TalkRepository;
import ru.itmo.wp.model.repository.UserRepository;
import ru.itmo.wp.model.repository.impl.TalkRepositoryImpl;
import ru.itmo.wp.model.repository.impl.UserRepositoryImpl;

import java.util.List;

public class TalkService {
	private final TalkRepository talkRepository = new TalkRepositoryImpl();
	private final UserRepository userRepository = new UserRepositoryImpl();

	public void send(Talk talk) {
		talkRepository.save(talk);
	}

	public List<Talk> findAllBySourceIdOrTargetId(String sourceId, String targetId) {
		List<Talk> talks = talkRepository.findAllById(sourceId, targetId);
		for (Talk talk : talks) {
			talk.setSourceUser(userRepository.find(Long.valueOf(talk.getSourceUserId())));
			talk.setTargetUser(userRepository.find(Long.valueOf(talk.getTargetUserId())));
		}
		return talks;
	}

	public void validateText(String text) throws ValidationException {
		if (Strings.isNullOrEmpty(text.trim())) {
			throw new ValidationException("Message can't be empty");
		}
		if (text.length() > 255) {
			throw new ValidationException("Message can't be longer than 255 letters");
		}
	}


	public TalkRepository getRepository() {
		return talkRepository;
	}

	public List<Talk> findAllById(String id) {
		return findAllBySourceIdOrTargetId(id, id);
	}
}
