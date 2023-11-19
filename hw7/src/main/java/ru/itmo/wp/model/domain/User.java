package ru.itmo.wp.model.domain;

import java.io.Serializable;

public class User extends Entity {
    private String login;
    private String email;
    private Boolean admin;

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getEmail() { return email;}

    public void setEmail(String email) {
        this.email = email;
    }

    public Boolean getAdmin() {
        return admin;
    }

    public void setAdmin(Boolean admin) {
        this.admin = admin;
    }
}
