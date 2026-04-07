package com.prsoftware.appcontrolstock;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        new Handler().postDelayed( () -> {
            boolean usuarioLogado= false;

            Intent intent;

            if(usuarioLogado){
                intent = new Intent(this,HomeActivity.class);
            }else {
                intent = new Intent(this,MainActivity.class);
            }

            setContentView(R.layout.activity_splash);

            startActivity(intent);
            finish();

        }, 2000); //2 Segundos
    }
}