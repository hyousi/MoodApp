package com.example.moodapp;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.moodapp.databinding.FragmentHomeBinding;
import com.example.moodapp.viewmodels.LoginViewModel;


/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {

    private LoginViewModel loginViewModel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        FragmentHomeBinding binding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        loginViewModel = ViewModelProviders.of(requireActivity()).get(LoginViewModel.class);

        final NavController navController = Navigation.findNavController(view);
        loginViewModel.getAuthenticationState().observe(getViewLifecycleOwner(), new Observer<LoginViewModel.AuthenticationState>() {
            @Override
            public void onChanged(LoginViewModel.AuthenticationState authenticationState) {
                switch (authenticationState) {
                    case AUTHENTICATED:
                        // TODO: set up app bar...
                        break;
                    case UNAUTHENTICATED:
                        navController.navigate(R.id.loginFragment);
                        // FIXME: should do as the `home` button on the device, not return tp loading page.
                        break;
                }
            }
        });
    }
}
