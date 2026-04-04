package com.prsoftware.appcontrolstock.dto.user;

public class UserRequest {

    private String name;
    private String username;
    private String password;
    private String role;

    public void setName(String name) {
        this.name = name;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
