package ru.itmo.wp.model.domain;

import java.util.Date;

public class Event extends Entity {
	public enum Type {
		ENTER,
		LOGOUT
	}
	private long userId;
	private Type type;

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userid) {
		this.userId = userid;
	}

	public String getType() {
		return type.toString();
	}

	public void setType(Type type) {
		this.type = type;
	}
}
