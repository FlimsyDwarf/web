package ru.itmo.wp.form;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class UserCredentialsRegister {
	@NotEmpty
	@Size(min = 3, max = 16)
	@Pattern(regexp = "[a-zA-Z]{3,16}", message = "Expected Latin letters")
	private String login;

	@NotEmpty
	@Size(min = 3, max = 16)
	private String name;

	@NotEmpty
	@Size(min = 1, max = 60)
	private String password;

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
