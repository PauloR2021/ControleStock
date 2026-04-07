package com.prsoftware.appcontrolstock.api;

import com.prsoftware.appcontrolstock.dto.login.LoginRequest;
import com.prsoftware.appcontrolstock.dto.login.LoginResponse;
import com.prsoftware.appcontrolstock.dto.user.ListResponseApi;
import com.prsoftware.appcontrolstock.dto.user.UserRequest;
import com.prsoftware.appcontrolstock.dto.user.UserResponseApi;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;

//Classe aonde salva os Endpoint para a API
public interface ApiService {

    //Endpoint de login
    @POST("auth/login")
    Call<LoginResponse> login (@Body LoginRequest request);

    //Endpoint de GetMyUser
    @GET("user/admin/me")
    Call<UserResponseApi> getUser(@Header("Authorization") String token);


    @POST("user/admin")
    Call<UserResponseApi> postCreateUser(@Body UserRequest request, @Header("Authorization") String token);

    @GET("user/admin")
    Call<ListResponseApi> getAllUser (@Header("Authorization") String token);
}
