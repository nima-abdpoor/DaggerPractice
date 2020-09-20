package com.chinachino.daggerpractice.di;

import com.chinachino.daggerpractice.UI.Auth.AuthActivity;
import com.chinachino.daggerpractice.di.auth.AuthViewModelsModule;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class ActivityBuildersModule {
    @ContributesAndroidInjector(
            modules = {AuthViewModelsModule.class}
    )
    abstract AuthActivity ContributeAuthActivity();
}
