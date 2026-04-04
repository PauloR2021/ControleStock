package com.prsoftware.appcontrolstock;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import com.prsoftware.appcontrolstock.api.ApiService;
import com.prsoftware.appcontrolstock.api.RetrofitClient;
import com.prsoftware.appcontrolstock.dto.login.LoginRequest;
import com.prsoftware.appcontrolstock.dto.login.LoginResponse;
import com.prsoftware.appcontrolstock.service.login.TokenManager;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import android.content.Intent;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText txtUser,txtPass;
    private Button buttonLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        //Verifica Token
        String token = TokenManager.getToken(this);

        if(token != null && !token.isEmpty()){
            //Não tem token -> Volta para o Login
            Intent intent = new Intent(this,HomeActivity.class);
            startActivity(intent);
            finish();
            return;
        }
        setContentView(R.layout.activity_main);

        txtUser = findViewById(R.id.textUsuario);
        txtPass = findViewById(R.id.txtSenha);
        buttonLogin = findViewById(R.id.buttonLogin);

        buttonLogin.setOnClickListener(v -> fazerLogin());


    }

    private void fazerLogin(){

        String user = txtUser.getText().toString();
        String password = txtPass.getText().toString();

        //Validação
        if(user.isEmpty()){
            txtUser.setError("Digite o usuário");
            txtUser.requestFocus();
            return;
        }

        if(password.isEmpty()){
            txtPass.setError("Digite a senha");
            txtPass.requestFocus();
            return;
        }

        ApiService api = RetrofitClient.getInstance().create(ApiService.class);

        LoginRequest request = new LoginRequest(user,password);

        api.login(request).enqueue(new Callback<LoginResponse>() {

            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                Log.d("LOGIN", "HTTP Code: " + response.code());
                Log.d("LOGIN", "HTTP Message: " + response.message());

                if (response.body() != null) {
                    Log.d("LOGIN", "Body sucesso: " + response.body().isSucesso());
                    Log.d("LOGIN", "Body mensagem: " + response.body().getMensagem());

                    if (response.body().getData() != null) {
                        Log.d("LOGIN", "Token recebido: " + response.body().getData().getToken());
                    } else {
                        Log.d("LOGIN", "Data veio NULL");
                    }
                } else {
                    Log.d("LOGIN", "Body veio NULL");
                }

                if(response.isSuccessful() && response.body() != null){
                    if(response.body().isSucesso() && response.body().getData() != null){
                        String token = response.body().getData().getToken();

                        if(token != null && !token.isEmpty()){
                            TokenManager.saveToken(MainActivity.this, token);
                            Intent intent = new Intent(MainActivity.this, HomeActivity.class);
                            startActivity(intent);
                            finish();
                        } else {
                            Toast.makeText(MainActivity.this, "Token inválido", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(MainActivity.this,
                                response.body().getMensagem(),
                                Toast.LENGTH_SHORT).show();
                    }

                } else {
                    Toast.makeText(MainActivity.this,
                            "Erro HTTP: " + response.code(),
                            Toast.LENGTH_SHORT).show();
                }
            }


            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                Log.e("LOGIN", "Erro na requisição: " + t.getMessage(), t);
                Toast.makeText(MainActivity.this,
                        "Erro de conexão com o servidor",
                        Toast.LENGTH_SHORT).show();
            }
        });
    }
}