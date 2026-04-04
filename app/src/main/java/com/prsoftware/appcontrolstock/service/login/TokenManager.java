package com.prsoftware.appcontrolstock.service.login;

import android.content.Context;
import android.content.SharedPreferences;

//Classe com as Funções de Savar , Pegar e Limpar o Token do APP
public class TokenManager {
    private static final String PREF_NAME = "app_prefs";
    private static final String KEY_TOKEN = "token";


    public static void saveToken(Context context,String token){
        SharedPreferences preferences = context.getSharedPreferences(PREF_NAME,Context.MODE_PRIVATE);
        preferences.edit().putString(KEY_TOKEN, token).apply();
    }

    public static String getToken(Context context){
        SharedPreferences preferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        return preferences.getString(KEY_TOKEN,null);
    }

    public static void clearToken(Context context){
        SharedPreferences prefs = context.getSharedPreferences("app_prefs", Context.MODE_PRIVATE);
        prefs.edit().remove("token").apply();
    }


}
