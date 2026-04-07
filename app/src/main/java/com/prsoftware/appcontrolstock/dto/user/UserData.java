package com.prsoftware.appcontrolstock.dto.user;

import com.google.gson.annotations.SerializedName;

import kotlinx.serialization.Serializable;

//Classe para Salvar as informações do Usuário
public class UserData {
    @SerializedName("id")
    private Integer id;

    @SerializedName("name")
    private String name;
    @SerializedName("username")
    private String username;
    @SerializedName("role")
    private String role;

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getUsername() {
        return username;
    }

    public String getRole() {
        return role;
    }
}
