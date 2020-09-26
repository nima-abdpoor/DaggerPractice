package com.chinachino.daggerpractice.di;

import com.chinachino.daggerpractice.UI.Auth.AuthActivity;
import com.chinachino.daggerpractice.UI.main.MainActivity;
import com.chinachino.daggerpractice.di.auth.AuthModule;
import com.chinachino.daggerpractice.di.auth.AuthScope;
import com.chinachino.daggerpractice.di.auth.AuthViewModelsModule;
import com.chinachino.daggerpractice.di.main.MainFragmentBuildersModule;
import com.chinachino.daggerpractice.di.main.MainModule;
import com.chinachino.daggerpractice.di.main.MainScope;
import com.chinachino.daggerpractice.di.main.MainViewModelsModule;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class ActivityBuildersModule {

    @AuthScope
    @ContributesAndroidInjector(
            modules = {AuthViewModelsModule.class
                    , AuthModule.class}
    )
    abstract AuthActivity ContributeAuthActivity();

    @MainScope
    @ContributesAndroidInjector(
            modules = {MainFragmentBuildersModule.class
                    , MainViewModelsModule.class
                    , MainModule.class}
    )
    abstract MainActivity ContributeMainActivity();
}
