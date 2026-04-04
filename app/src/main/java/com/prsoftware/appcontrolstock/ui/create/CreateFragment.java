package com.prsoftware.appcontrolstock.ui.create;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.prsoftware.appcontrolstock.databinding.FragmentCreateBinding;

//Classe responsável por gerenciar a Parte do Fragment de Criar Usuário

public class CreateFragment extends Fragment {



    private FragmentCreateBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = FragmentCreateBinding.inflate(inflater,container,false);
        return binding.getRoot();
    }
}