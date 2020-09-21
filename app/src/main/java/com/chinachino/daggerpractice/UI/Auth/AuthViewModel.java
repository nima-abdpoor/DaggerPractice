package com.chinachino.daggerpractice.UI.Auth;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.LiveDataReactiveStreams;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;

import com.chinachino.daggerpractice.model.User;
import com.chinachino.daggerpractice.network.auth.AuthAPI;

import javax.inject.Inject;

import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class AuthViewModel extends ViewModel {
    private static final String TAG = "AuthViewModel";
    private AuthAPI authAPI;
    private MediatorLiveData<User> authUser = new MediatorLiveData<>();

    @Inject
    public AuthViewModel(AuthAPI authAPI) {
        this.authAPI = authAPI;
        Log.d(TAG, "AuthViewModel: viewModel is working ...");
    }
    public void AuthenticateWithID(int userID){
        final LiveData<User> source = LiveDataReactiveStreams.fromPublisher(
              authAPI.getUsers(userID)
              .subscribeOn(Schedulers.io())
        );
        authUser.addSource(source, user -> {
            authUser.setValue(user);
            authUser.removeSource(source);
        });
    }
    public LiveData<User> ObserveUser(){
        return authUser;
    }
}
