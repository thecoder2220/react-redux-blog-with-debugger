package com.claurier.domain;

import javax.validation.constraints.NotNull;

import com.claurier.model.AuthorityName;
import org.hibernate.validator.constraints.NotEmpty;

public class UserForm {

    @NotEmpty
    private String email = "";
    @NotEmpty
    private String userName = "";
    @NotEmpty
    private String password = "";
    @NotEmpty
    private String confirmPassword = "";
    @NotNull
    private AuthorityName authorityName = AuthorityName.ROLE_USER;

    public UserForm(String email, String userName, String password, String confirmPassword, AuthorityName authorityName) {
        this.email = email;
        this.userName = userName;
        this.password = password;
        this.confirmPassword = confirmPassword;
        this.authorityName = authorityName;
    }

    public UserForm() {
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUserName() {
        return this.userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    public AuthorityName getAuthorityName() {
        return authorityName;
    }

    public void setAuthorityName(AuthorityName authorityName) {
        this.authorityName = authorityName;
    }

}

