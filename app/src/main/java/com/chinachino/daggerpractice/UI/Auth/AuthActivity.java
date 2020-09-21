package com.chinachino.daggerpractice.UI.Auth;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.bumptech.glide.RequestManager;
import com.chinachino.daggerpractice.R;
import com.chinachino.daggerpractice.ViewModels.ViewModelProviderFactory;
import com.chinachino.daggerpractice.model.User;

import javax.inject.Inject;

import dagger.android.support.DaggerAppCompatActivity;

public class AuthActivity extends DaggerAppCompatActivity implements View.OnClickListener {

    private static final String TAG = "AuthActivity";

    private EditText UserInput;

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
        findViewById(R.id.login_button).setOnClickListener(this);
        viewModel = ViewModelProviders.of(this,providerFactory).get(AuthViewModel.class);
        setLogo();
        subscribeObservers();
    }

    private void setLogo() {
        requestManager.load(logo)
                .into((ImageView) findViewById(R.id.login_logo));
    }

    public void subscribeObservers(){
        viewModel.ObserveUser().observe(this, new Observer<User>() {
            @Override
            public void onChanged(User user) {
                if (user !=null)
                Log.d(TAG, "onChanged: "+user.getEmail());
            }
        });
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case  R.id.login_button :
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