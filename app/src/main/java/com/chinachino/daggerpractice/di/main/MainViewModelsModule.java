package com.chinachino.daggerpractice.di.main;

import androidx.lifecycle.ViewModel;

import com.chinachino.daggerpractice.UI.main.profile.ProfileViewModel;
import com.chinachino.daggerpractice.di.ViewModelKey;

import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;

@Module
public abstract class MainViewModelsModule {

    @Binds
    @IntoMap
    @ViewModelKey(ProfileViewModel.class)
    public abstract ViewModel bindProfileViewModel(ProfileViewModel viewModel);
}
