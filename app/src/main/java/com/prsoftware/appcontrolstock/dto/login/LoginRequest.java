package com.prsoftware.appcontrolstock.dto.login;

//Envia as informações de Usuário para API de Login
public class LoginRequest {
    private String username;
    private String password;

    public LoginRequest(String username, String password){
        this.username = username;
        this.password = password;
    }
}
