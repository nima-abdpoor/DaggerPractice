package com.chinachino.daggerpractice.UI.Auth;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.lifecycle.ViewModelProviders;

import com.bumptech.glide.RequestManager;
import com.chinachino.daggerpractice.R;
import com.chinachino.daggerpractice.UI.main.MainActivity;
import com.chinachino.daggerpractice.ViewModels.ViewModelProviderFactory;


import javax.inject.Inject;

import dagger.android.support.DaggerAppCompatActivity;

public class AuthActivity extends DaggerAppCompatActivity implements View.OnClickListener {

    private static final String  TAG = "AuthActivity";

    private EditText UserInput;
    private ProgressBar progressBar;

    private AuthViewModel viewModel;

    @Inject
    ViewModelProviderFactory providerFactory;

    @Inject
    RequestManager requestManager;

    @Inject
    Drawable logo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auth);

        UserInput = findViewById(R.id.user_id_input);
        progressBar = findViewById(R.id.progress_bar);

        findViewById(R.id.login_button).setOnClickListener(this);
        viewModel = ViewModelProviders.of(this, providerFactory).get(AuthViewModel.class);
        setLogo();
        subscribeObservers();
    }

    private void setLogo() {
        requestManager.load(logo)
                .into((ImageView) findViewById(R.id.login_logo));
    }

    public void subscribeObservers() {
        viewModel.ObserveAuthUser()
                .observe(this, userAuthResource -> {
                    switch (userAuthResource.status) {
                        case LOADING: {
                            showProgressBar(true);
                            break;
                        }
                        case ERROR: {
                            Toast.makeText(AuthActivity.this, userAuthResource.message +
                                    "\nDid you enter number between 1 and 10?", Toast.LENGTH_SHORT).show();
                            break;
                        }
                        case AUTHENTICATED: {
                            Log.d(TAG, "onChanged: " + userAuthResource.data.getEmail());
                            showProgressBar(false);
                            loginScreen();
                            break;
                        }
                        case NOT_AUTHENTICATED: {
                            showProgressBar(false);
                            break;
                        }
                    }
                });
    }

    private void loginScreen() {
        Intent intent = new Intent(AuthActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    private void showProgressBar(boolean isVisible) {
        if (isVisible)
            progressBar.setVisibility(View.VISIBLE);
        else
            progressBar.setVisibility(View.GONE);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.login_button:
                attemptLogin();
                break;
        }
    }

    private void attemptLogin() {
        if (TextUtils.isEmpty(UserInput.getText().toString()))
            return;
        viewModel.AuthenticateWithID(Integer.parseInt(UserInput.getText().toString()));
    }
}