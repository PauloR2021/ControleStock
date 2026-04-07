package com.prsoftware.appcontrolstock.ui.homeUser;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.prsoftware.appcontrolstock.R;
import com.prsoftware.appcontrolstock.databinding.FragmentHomeUserBinding;


public class HomeUserFragment extends Fragment {

    private FragmentHomeUserBinding binding;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = FragmentHomeUserBinding.inflate(inflater,container,false);
        return binding.getRoot();
    }
}