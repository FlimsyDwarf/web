package ru.itmo.wp.form;

import javax.persistence.Entity;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;


public class PostData {

    @NotBlank
    @Size(min = 1, max = 1000)
    private String tags;


    @NotBlank
    @Size(min = 1, max = 60)
    private String title;

    @NotBlank
    @Size(min = 1, max = 65000)
    private String text;

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
