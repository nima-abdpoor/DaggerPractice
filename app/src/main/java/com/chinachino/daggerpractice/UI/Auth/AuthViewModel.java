package com.chinachino.daggerpractice.UI.Auth;

import android.util.Log;

import androidx.lifecycle.ViewModel;

import com.chinachino.daggerpractice.network.auth.AuthAPI;

import javax.inject.Inject;

public class AuthViewModel extends ViewModel {
    private static final String TAG = "AuthViewModel";
    private AuthAPI authAPI;

    @Inject
    public AuthViewModel(AuthAPI authAPI) {
        this.authAPI = authAPI;
        Log.d(TAG, "AuthViewModel: viewModel is working ...");
        if (this.authAPI == null)
            Log.d(TAG, "AuthViewModel: authAPI is null");
        else Log.d(TAG, "AuthViewModel: auth api in not null");
    }
}
