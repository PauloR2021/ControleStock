package com.prsoftware.appcontrolstock.ui.user;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.prsoftware.appcontrolstock.api.ApiService;
import com.prsoftware.appcontrolstock.api.RetrofitClient;
import com.prsoftware.appcontrolstock.databinding.FragmentMyUserBinding;
import com.prsoftware.appcontrolstock.dto.user.UserData;
import com.prsoftware.appcontrolstock.dto.user.UserResponseApi;
import com.prsoftware.appcontrolstock.service.login.TokenManager;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

//Classe responsável por gerenciar a Parte do Fragment de Pegar o Meu Usuário
public class MyUserFragment extends Fragment {

    private FragmentMyUserBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentMyUserBinding.inflate(inflater,container,false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view,savedInstanceState);
        myUserDisplay();

    }

    private void myUserDisplay(){
        //Pega o Token Válidado
        String token = TokenManager.getToken(requireContext());

        //Verifica se tem Token validado
        if(token == null || token.isEmpty()){
            Toast.makeText(requireContext(), "Token não encontrado. Faça login novamente.", Toast.LENGTH_LONG).show();
            return;
        }

        //Instancia a API com o RetroFit
        ApiService api = RetrofitClient.getInstance().create(ApiService.class);

        //Envia para a Rota de GetUsuário com o Token de Validação e passa uma classe de Retorno
        api.getUser("Bearer " + token).enqueue(new Callback<UserResponseApi>() {

            @Override
            public void onResponse(Call<UserResponseApi> call, Response<UserResponseApi> response) {

                //Verifica se tem retorno da API
                if(response.isSuccessful() && response.body() != null){

                    //Pega o Retorno da API e joga para a Classe de Retorno, passando o corpo do response
                    UserResponseApi userResponseApi = response.body();

                    //Valida novamente o Classe da API e se tem informação
                    if(userResponseApi.isSucesso() && userResponseApi.getData() != null){
                        //Salva o Retorno da API dentro de uma nova classe para salvar os retornos
                        UserData user = userResponseApi.getData();

                        binding.txtId.setText(String.valueOf(user.getId()));
                        binding.txtNome.setText(user.getName());
                        binding.txtUsuario.setText(user.getUsername());
                        binding.txtAcesso.setText(user.getRole());
                    }else{
                        //Mostra se retorna erro a API
                        Toast.makeText(requireContext(),
                                userResponseApi.getMensagem(),
                                Toast.LENGTH_LONG).show();
                    }
                } else {

                    //Mostra erro da API na Busca
                    Toast.makeText(requireContext(),
                            "Erro ao buscar usuário: " + response.code(),
                            Toast.LENGTH_LONG).show();
                }

            }

            @Override
            public void onFailure(Call<UserResponseApi> call, Throwable t) {
                Toast.makeText(requireContext(),
                        "Erro de conexão: " + t.getMessage(),
                        Toast.LENGTH_LONG).show();
            }
        });

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
