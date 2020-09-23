package com.chinachino.daggerpractice.UI.main.profile;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.chinachino.daggerpractice.SessionManager;
import com.chinachino.daggerpractice.UI.Auth.AuthResource;
import com.chinachino.daggerpractice.model.User;

import java.net.UnknownServiceException;

import javax.inject.Inject;

public class ProfileViewModel extends ViewModel {
    private static final String TAG = "ProfileViewModel";

    private final SessionManager sessionManager;
    @Inject
    public ProfileViewModel(SessionManager sessionManager) {
        this.sessionManager = sessionManager;
        Log.d(TAG, "ProfileViewModel: view model is ready ... ");
    }
    public LiveData<AuthResource<User>> getAuthenticatedUser(){
        return sessionManager.getMediatorLiveData();
    }
}
