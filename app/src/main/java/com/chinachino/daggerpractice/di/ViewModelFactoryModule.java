package com.chinachino.daggerpractice.di;

import androidx.lifecycle.ViewModelProvider;

import com.chinachino.daggerpractice.ViewModels.ViewModelProviderFactory;

import dagger.Binds;
import dagger.Module;

@Module
public abstract class ViewModelFactoryModule {
    @Binds
    public abstract ViewModelProvider.Factory bindViewModelFactory(ViewModelProviderFactory providerFactory);
}
