package com.prsoftware.appcontrolstock.ui.list;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.prsoftware.appcontrolstock.api.ApiService;
import com.prsoftware.appcontrolstock.api.RetrofitClient;
import com.prsoftware.appcontrolstock.databinding.FragmentListUserBinding;
import com.prsoftware.appcontrolstock.dto.user.ListResponseApi;
import com.prsoftware.appcontrolstock.dto.user.UserData;
import com.prsoftware.appcontrolstock.dto.user.UserResponseApi;
import com.prsoftware.appcontrolstock.service.login.TokenManager;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class ListUserFragment extends Fragment {

    private FragmentListUserBinding binding;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentListUserBinding.inflate(inflater,container,false);
        return binding.getRoot();

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding.recyclerUsers.setLayoutManager(new LinearLayoutManager(requireContext()));
        carregarUsuarios();
    }

    private void carregarUsuarios(){
        String token = TokenManager.getToken(requireContext());
        Log.d("API", "TOKEN: "+token);

        if(token == null || token.isEmpty()){
            Toast.makeText(requireContext(),
                    "Token não encontrado",
                    Toast.LENGTH_SHORT).show();
            return;
        }

        ApiService api = RetrofitClient.getInstance().create(ApiService.class);

        api.getAllUser("Bearer "+token).enqueue(new Callback<ListResponseApi>() {
            @Override
            public void onResponse(Call<ListResponseApi> call, Response<ListResponseApi> response) {

                Log.d("API", "BodY:" +response.body());
                if(response.isSuccessful() && response.body() != null){
                    Log.d("API", "GETMENSAGEM:" +response.body().getMensagem());

                    List<UserData> listaUsuarios =response.body().getData();

                    Log.d("API","GETDATA: "+response.body().getData());

                    if(listaUsuarios != null && !listaUsuarios.isEmpty()){
                        UserAdapter adapter = new UserAdapter(listaUsuarios);
                        binding.recyclerUsers.setAdapter(adapter);
                    }else{
                        Toast.makeText(requireContext(),"Lista Vazia",Toast.LENGTH_SHORT).show();
                    }
                }else {
                    Toast.makeText(requireContext(),"Erro ao buscar Usuários",Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<ListResponseApi> call, Throwable t) {
                Toast.makeText(requireContext(),"Falha: "+t.getMessage(), Toast.LENGTH_LONG).show();

            }

        });
    }
}