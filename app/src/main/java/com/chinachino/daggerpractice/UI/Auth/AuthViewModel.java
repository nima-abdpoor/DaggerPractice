package com.chinachino.daggerpractice.UI.Auth;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.LiveDataReactiveStreams;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.ViewModel;

import com.chinachino.daggerpractice.model.User;
import com.chinachino.daggerpractice.network.auth.AuthAPI;

import javax.inject.Inject;

import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

public class AuthViewModel extends ViewModel {
    private static final String TAG = "AuthViewModel";
    private AuthAPI authAPI;
    private MediatorLiveData<AuthResource<User>> authUser = new MediatorLiveData<>();

    @Inject
    public AuthViewModel(AuthAPI authAPI) {
        this.authAPI = authAPI;
        Log.d(TAG, "AuthViewModel: viewModel is working ...");
    }

    public void AuthenticateWithID(int userID) {
        authUser.setValue(AuthResource.loading(null));
        final LiveData<AuthResource<User>> source = LiveDataReactiveStreams.fromPublisher(
                authAPI.getUsers(userID)
                        .onErrorReturn(throwable -> {
                            User user = new User();
                            user.setId(-1);
                            return user;
                        })
                        .map((Function<User, AuthResource<User>>) user -> {
                                    if (user.getId() == -1) {
                                        return AuthResource.error("could not authenticate", null);
                                    }
                                    return AuthResource.authenticated(user);
                                }
                        )
                        .subscribeOn(Schedulers.io())
        );
        authUser.addSource(source, userAuthResource -> {
            authUser.setValue(userAuthResource);
            authUser.removeSource(source);
        });
    }

    public LiveData<AuthResource<User>> ObserveUser() {
        return authUser;
    }
}
