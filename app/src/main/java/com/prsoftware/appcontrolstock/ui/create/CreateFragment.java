package com.prsoftware.appcontrolstock.ui.create;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import com.prsoftware.appcontrolstock.api.ApiService;
import com.prsoftware.appcontrolstock.api.RetrofitClient;
import com.prsoftware.appcontrolstock.databinding.FragmentCreateBinding;
import com.prsoftware.appcontrolstock.dto.user.UserRequest;
import com.prsoftware.appcontrolstock.dto.user.UserResponseApi;
import com.prsoftware.appcontrolstock.service.login.TokenManager;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

//Classe responsável por gerenciar a Parte do Fragment de Criar Usuário

public class CreateFragment extends Fragment {
    private FragmentCreateBinding binding;

    private String edtNome,edtUsername,edtPassword,edtRole;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentCreateBinding.inflate(inflater,container,false);

        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.btnCadastrar.setOnClickListener( v -> cadastarUser());
    }


    private void cadastarUser(){
        edtNome = binding.editNome.getText().toString().trim();
        edtUsername = binding.editUsername.getText().toString().trim();
        edtPassword = binding.editPassword.getText().toString().trim();

        if(edtNome.isEmpty()){
            binding.editNome.setError("Digite o Nome");
            return;
        }
        if(edtUsername.isEmpty()){
            binding.editUsername.setError("Digite o Usuário");
            return;
        }
        if(edtPassword.isEmpty()){
            binding.editPassword.setError("Digite a Senha");
            return;
        }

        if(binding.radioUser.isChecked()){
            edtRole = "USER";
        } else if (binding.radioAdmin.isChecked()) {
            edtRole = "ADMIN";
        }else{
            Toast.makeText(requireContext(),"Selecione o tipo de acesso", Toast.LENGTH_SHORT).show();
        }


        Log.d("CREATE","NOME: [" +edtNome + "]");
        Log.d("CREATE","USERNAME: [" +edtUsername + "]");
        Log.d("CREATE","PASSWORD: [" +edtPassword + "]");
        Log.d("CREATE","ROLE: [" +edtRole+ "]");

        UserRequest request = new UserRequest();
        request.setName(edtNome);
        request.setUsername(edtUsername);
        request.setPassword(edtPassword);
        request.setRole(edtRole);

        String token = TokenManager.getToken(requireContext());

        //Verifica se o Token Validado
        if(token == null || token.isEmpty()){
            Toast.makeText(requireContext(),"Token não encontrado. Faça o login novamente",Toast.LENGTH_LONG).show();
            return;
        }

        //Instancia a API com o Retrofit
        ApiService api = RetrofitClient.getInstance().create(ApiService.class);

        api.postCreateUser(request,"Bearer " + token).enqueue(new Callback<UserResponseApi>() {

            @Override
            public void onResponse(Call<UserResponseApi> call, Response<UserResponseApi> response) {

                if(response.isSuccessful() && response.body() != null){
                    String mensagem = response.body().getMensagem();

                    if(response.body().isSucesso()){
                        Toast.makeText(getContext(), mensagem, Toast.LENGTH_SHORT).show();

                        //Limpa o Formulário
                        limparCampos();
                    }else{
                        //Erro vindo da API
                        Toast.makeText(getContext(),mensagem,Toast.LENGTH_SHORT).show();
                    }
                }else{

                    try{
                        String erroBody = response.errorBody().string();
                        Log.e("API_ERROR", erroBody);

                        Toast.makeText(getContext(),"Erro: " +erroBody,Toast.LENGTH_SHORT).show();

                    }catch (Exception e){
                        Toast.makeText(getContext(),"Erro inesperado",Toast.LENGTH_SHORT).show();
                    }

                }

            }

            @Override
            public void onFailure(Call<UserResponseApi> call, Throwable t) {
                Toast.makeText(requireContext(), "Erro ao Cadastrar",Toast.LENGTH_SHORT).show();
                limparCampos();
            }
        });

    }

    private void limparCampos(){
        binding.editNome.setText("");
        binding.editUsername.setText("");
        binding.editPassword.setText("");
        binding.radioGroupRole.clearCheck();
    }
}