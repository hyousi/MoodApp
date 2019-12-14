package com.example.moodapp;


import android.os.Bundle;

import androidx.activity.OnBackPressedCallback;
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

import com.example.moodapp.databinding.FragmentRegistrationBinding;
import com.example.moodapp.viewmodels.LoginViewModel;
import com.example.moodapp.viewmodels.RegistrationViewModel;

public class RegistrationFragment extends Fragment {

    private LoginViewModel loginViewModel;
    private RegistrationViewModel registrationViewModel;
    private FragmentRegistrationBinding binding;
    private NavController navController;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_registration, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

        ViewModelProvider provider = ViewModelProviders.of(requireActivity());
        registrationViewModel = provider.get(RegistrationViewModel.class);
        loginViewModel = provider.get(LoginViewModel.class);
        navController = Navigation.findNavController(view);


        // When the register button is clicked, collect the current values from
        // the two edit texts and pass to the ViewModel to complete registration.
        binding.signupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                registrationViewModel.createAccountAndLogin(
                        view.findViewById(R.id.username_edit_text).toString(),
                        view.findViewById(R.id.password_edit_text).toString()
                );
            }
        });

        binding.cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cancelRegister();
            }
        });

        // RegistrationViewModel updates the registrationState to
        // REGISTRATION_COMPLETED when ready, and for this example, the username
        // is accessed as a read-only property from RegistrationViewModel and is
        // used to directly authenticate with loginViewModel.
        registrationViewModel.getRegistrationState().observe(
                getViewLifecycleOwner(), new Observer<RegistrationViewModel.RegistrationState>() {
                    @Override
                    public void onChanged(RegistrationViewModel.RegistrationState registrationState) {
                        if (registrationState == RegistrationViewModel.RegistrationState.REGISTRATION_COMPLETED) {
                            // Here we authenticate with the token provided by the ViewModel
                            // then pop back to the profie_fragment, where the user authentication
                            // status will be tested and should be authenticated.
                            String authToken = registrationViewModel.getAuthToken();
                            loginViewModel.authenticate(authToken, authToken);
                            navController.popBackStack(R.id.homeFragment, false);
                        }
                    }
                }
        );

        // If the user presses back, cancel the user registration and pop back
        // to the login fragment. Since this ViewModel is shared at the activity
        // scope, its state must be reset so that it will be in the initial
        // state if the user comes back to register later.
        requireActivity().getOnBackPressedDispatcher().addCallback(getViewLifecycleOwner(),
                new OnBackPressedCallback(true) {
                    @Override
                    public void handleOnBackPressed() {
                        cancelRegister();
                    }
                });

    }

    private void cancelRegister() {
        registrationViewModel.userCancelledRegistration();
        navController.popBackStack();
    }
}
