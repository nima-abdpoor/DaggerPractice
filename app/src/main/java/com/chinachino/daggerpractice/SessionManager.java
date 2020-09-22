package com.chinachino.daggerpractice;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;

import com.chinachino.daggerpractice.UI.Auth.AuthResource;
import com.chinachino.daggerpractice.model.User;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class SessionManager {
    private static final String TAG = "SessionManager";

    MediatorLiveData<AuthResource<User>> mediatorLiveData = new MediatorLiveData<>();

    @Inject
    public SessionManager() {
    }
    public void AuthenticateWithID(final LiveData<AuthResource<User>> source){
        if (source != null){
            mediatorLiveData.setValue(AuthResource.loading(null));
            mediatorLiveData.addSource(source, userAuthResource -> {
                mediatorLiveData.setValue(userAuthResource);
                mediatorLiveData.removeSource(source);
            });

        }
    }
    public void logOut(){
        Log.d(TAG, "logOut: " + "Logging Out... ");
        mediatorLiveData.setValue(AuthResource.logout());
    }

    public MediatorLiveData<AuthResource<User>> getMediatorLiveData() {

        return mediatorLiveData;
    }
}
