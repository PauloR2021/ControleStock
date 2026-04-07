package com.prsoftware.appcontrolstock.dto.user;

import java.util.List;

public class ListResponseApi {

    private boolean sucesso;
    private String mensagem;
    private List<UserData> data;
    private int status;

    private String timeStampo;

    public boolean isSucesso() {
        return sucesso;
    }

    public void setSucesso(boolean sucesso) {
        this.sucesso = sucesso;
    }

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }

    public List<UserData> getData() {
        return data;
    }

    public void setData(List<UserData> data) {
        this.data = data;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getTimeStampo() {
        return timeStampo;
    }

    public void setTimeStampo(String timeStampo) {
        this.timeStampo = timeStampo;
    }
}
