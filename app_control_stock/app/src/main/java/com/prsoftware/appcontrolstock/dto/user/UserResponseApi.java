package com.prsoftware.appcontrolstock.dto.user;

//Retorna as Informações da API
public class UserResponseApi {
    private boolean sucesso;
    private String mensagem;
    private UserData data;
    private int status;

    private String timeStampo;

    public boolean isSucesso() {
        return sucesso;
    }

    public String getMensagem() {
        return mensagem;
    }

    public UserData getData() {
        return data;
    }

    public int getStatus() {
        return status;
    }

    public String getTimeStampo() {
        return timeStampo;
    }
}
