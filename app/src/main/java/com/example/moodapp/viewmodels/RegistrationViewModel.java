package com.example.moodapp.viewmodels;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class RegistrationViewModel extends ViewModel {

    public enum RegistrationState {
        COLLECT_PROFILE_DATA,
        COLLECT_USER_PASSWORD,
        REGISTRATION_COMPLETED
    }

    private String authToken;

    private MutableLiveData<RegistrationState> registrationState;

    public MutableLiveData<RegistrationState> getRegistrationState() {
        if (registrationState == null) {
            registrationState = new MutableLiveData<>();
            registrationState.setValue(RegistrationState.COLLECT_PROFILE_DATA);
        }
        return registrationState;
    }

    // Simulation of real-world scenario, where an auth token may be provided as
    // an alternate authentication mechanism instead of passing the password
    // around. This is set at the end of the registration process.

    public String getAuthToken() {
        return authToken;
    }

    public void collectProfileData(String name, String bio) {
        // ... validate and store data

        // Change State to collecting username and password
        registrationState.setValue(RegistrationState.COLLECT_USER_PASSWORD);
    }

    public void createAccountAndLogin(String username, String password) {
        // ... create account
        // ... authenticate
        this.authToken = "authToken"; // TODO: create authTOken
        // Change State to registration completed
        registrationState.setValue(RegistrationState.REGISTRATION_COMPLETED);
    }

    public boolean userCancelledRegistration() {
        // Clear existing registration data
        registrationState.setValue(RegistrationState.COLLECT_PROFILE_DATA);
        authToken = "";
        return true;
    }

}
