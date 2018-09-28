package com.claurier.modules.user.lightbeans;

import java.util.Set;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

public class UserLightbean {
    
    private long id;
    @NotEmpty
    private String username;
    @Email
    @NotEmpty
    private String email;
    @NotEmpty
    private String login;
    private String phoneNumber;
    private String company;
    private boolean disabled;
    private String passwordRepeated;
    private String password;

    public long getId() {
        return this.id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getLogin() {
        return this.login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPhoneNumber() {
        return this.phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getCompany() {
        return this.company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public boolean isDisabled() {
        return this.disabled;
    }

    public void setDisabled(boolean disabled) {
        this.disabled = disabled;
    }

    public String getPasswordRepeated() {
        return this.passwordRepeated;
    }

    public void setPasswordRepeated(String passwordRepeated) {
        this.passwordRepeated = passwordRepeated;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}

