package ru.itmo.web.hw4.model;

public class User {


    public static enum COLOR {
        GREEN,
        RED,
        BLUE
    }
    private final long id;
    private final String handle;
    private final String name;
    private final COLOR color;

    public User(long id, String handle, String name, COLOR color) {
        this.id = id;
        this.handle = handle;
        this.name = name;
        this.color = color;
    }

    public long getId() {
        return id;
    }

    public String getHandle() {
        return handle;
    }

    public String getName() {
        return name;
    }
    public COLOR getColor() {
        return color;
    }
}
