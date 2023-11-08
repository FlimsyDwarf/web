package ru.itmo.wp.model.domain;

import java.util.Date;

public class Talk extends Entity {
	private String sourceUserId;
	private String targetUserId;
	private String text;
	private Date creationTime;
	private User sourceUser;
	private User targetUser;

	public String getSourceUserId() {
		return sourceUserId;
	}

	public void setSourceUserId(String sourceUserId) {
		this.sourceUserId = sourceUserId;
	}

	public String getTargetUserId() {
		return targetUserId;
	}

	public void setTargetUserId(String targetUserId) {
		this.targetUserId = targetUserId;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public void setSourceUser(User sourceUser) {
		this.sourceUser = sourceUser;
	}

	public User getSourceUser() {
		return sourceUser;
	}

	public void setTargetUser(User targetUser) {
		this.targetUser = targetUser;
	}

	public User getTargetUser() {
		return targetUser;
	}
}
