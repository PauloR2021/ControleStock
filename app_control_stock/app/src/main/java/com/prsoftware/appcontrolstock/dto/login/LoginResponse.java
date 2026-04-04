package com.prsoftware.appcontrolstock.dto.login;

//Retorna as informações da API quando tenta o Login
public class LoginResponse {

    private boolean sucesso;
    private String mensagem;
    private LoginData data;
    private int status;
    private String timeStamp;

    public boolean isSucesso() {
        return sucesso;
    }

    public String getMensagem() {
        return mensagem;
    }

    public LoginData getData() {
        return data;
    }

    public int getStatus() {
        return status;
    }

    public String getTimeStamp() {
        return timeStamp;
    }
}
