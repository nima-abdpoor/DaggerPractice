package com.chinachino.daggerpractice;


import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.Nullable;

import com.chinachino.daggerpractice.UI.Auth.AuthActivity;

import javax.inject.Inject;

import dagger.android.support.DaggerAppCompatActivity;

public abstract class BaseActivity extends DaggerAppCompatActivity {

    @Inject
    SessionManager sessionManager;

    private static final String TAG = "BaseActivity";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    public void subscribeObservers(){
        sessionManager.getMediatorLiveData().observe(this, userAuthResource -> {
            switch (userAuthResource.status) {
                case LOADING: {
                    break;
                }
                case ERROR: {
                    Log.d(TAG, "subscribeObservers: " + userAuthResource.message);
                    break;
                }
                case AUTHENTICATED: {
                    Log.d(TAG, "onChanged: Login success" + userAuthResource.data.getEmail());
                    break;
                }
                case NOT_AUTHENTICATED: {
                    navLoginScreen();
                    break;
                }
            }
        });
    }

    protected void navLoginScreen(){
        Intent intent = new Intent(BaseActivity.this,AuthActivity.class);
        startActivity(intent);
        finish();
    }

}