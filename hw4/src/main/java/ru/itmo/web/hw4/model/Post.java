package ru.itmo.web.hw4.model;

public class Post {
    private final long id;
    private final long user_id;
    private final String title;
    private final String text;
    public Post(long id, String title, String text, long user_id) {
        this.id = id;
        this.title = title;
        this.user_id = user_id;
        this.text = text;
    }

    public long getUser_id() {
        return user_id;
    }

    public long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getText() {
        return text;
    }

    public String getShortText() {
        if (text.length() <= 350) {
            return text;
        }
        return text.substring(0, 350) + "...";
    }
}
