package com.paulo.controlstock.api;

import org.springframework.cglib.core.Local;

import java.time.LocalDateTime;

public class ResponseApiControl <T>{

    private boolean sucesso;
    private String mensagem;
    private T data;
    private int status;
    private LocalDateTime timeStamp;

    public ResponseApiControl(boolean sucesso, String mensagem, T data, int status) {
        this.sucesso = sucesso;
        this.mensagem = mensagem;
        this.data = data;
        this.status = status;
        this.timeStamp = LocalDateTime.now();
    }

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

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public LocalDateTime getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(LocalDateTime timeStamp) {
        this.timeStamp = timeStamp;
    }
}
