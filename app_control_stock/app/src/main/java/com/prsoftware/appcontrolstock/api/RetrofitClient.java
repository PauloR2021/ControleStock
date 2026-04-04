package com.prsoftware.appcontrolstock.api;

import java.lang.annotation.Retention;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

//Classe com a interface do Retrofit - Faz a conexão com a API
public class RetrofitClient {

    //Criando a interface da API

    private static final String BASE_URL = "http://192.168.2.113:8085/";

    private static Retrofit retrofit;

    public static Retrofit getInstance(){
        if(retrofit == null){
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }

        return retrofit;
    }
}
