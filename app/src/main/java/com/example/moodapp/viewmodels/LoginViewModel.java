package com.example.moodapp.viewmodels;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class LoginViewModel extends ViewModel {

    public enum AuthenticationState {
        UNAUTHENTICATED,        // Initial state, the user needs to authenticate
        AUTHENTICATED,          // The user has authenticated successfully
        INVALID_AUTHENTICATION  // Authentication failed
    }

    private String username;

    final MutableLiveData<AuthenticationState> authenticationState = new MutableLiveData<>();

    public LoginViewModel() {
        // In this example, the user is always unauthenticated when MainActivity is launched
        authenticationState.setValue(AuthenticationState.UNAUTHENTICATED);
        username = "";
    }

    public MutableLiveData<AuthenticationState> getAuthenticationState() {
        return authenticationState;
    }

    public void authenticate(String username, String password) {
        Log.d("Dang Ze", username + "  " + password);
        if (passwordIsValidForUsername(username, password)) {
            this.username = username;
            authenticationState.setValue(AuthenticationState.AUTHENTICATED);
        } else {
            authenticationState.setValue(AuthenticationState.INVALID_AUTHENTICATION);
        }
    }

    public void refuseAuthentication() {
        authenticationState.setValue(AuthenticationState.UNAUTHENTICATED);
    }

    private boolean passwordIsValidForUsername(String username, String password) {
        // TODO: implement this method.
        Log.d("Dang Ze", username.equals(password) ? "true" : "false");
        return username.equals(password);
    }
}
