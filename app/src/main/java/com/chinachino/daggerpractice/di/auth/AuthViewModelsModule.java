package com.chinachino.daggerpractice.di.auth;

import androidx.lifecycle.ViewModel;

import com.chinachino.daggerpractice.UI.Auth.AuthViewModel;
import com.chinachino.daggerpractice.di.ViewModelKey;

import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;

@Module
public abstract class AuthViewModelsModule {
    @Binds
    @IntoMap
    @ViewModelKey(AuthViewModel.class)
    public abstract ViewModel bindAuthViewModel(AuthViewModel viewModel);
}
