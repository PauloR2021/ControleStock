package com.prsoftware.appcontrolstock;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.prsoftware.appcontrolstock.service.login.TokenManager;

public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        String token = TokenManager.getToken(this);

        if(token == null || token.isEmpty()){
            startActivity(new Intent(this,MainActivity.class));
            finish();
            return;
        }

        setContentView(R.layout.home_activity);

        Button buttonLogin = findViewById(R.id.buttonLogout);
        Button buttonUsuario = findViewById(R.id.buttonUsuarios);

        buttonLogin.setOnClickListener(v ->logout());
        buttonUsuario.setOnClickListener(v -> telaUsuario());

    }

    //Chama as Telas do Usuário
    private void telaUsuario(){
        Intent intent = new Intent(HomeActivity.this, UsuarioActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
        finish();//Fecha a janela Atual
    }

    private void logout(){
        //Limpa o Token
        TokenManager.clearToken(this);

        //Volta para o Login
        Intent intent = new Intent(this,MainActivity.class);
        startActivity(intent);
        finish();
    }
}