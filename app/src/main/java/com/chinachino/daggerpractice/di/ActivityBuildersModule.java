package com.chinachino.daggerpractice.di;

import com.chinachino.daggerpractice.AuthActivity;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class ActivityBuildersModule {
    @ContributesAndroidInjector
    abstract AuthActivity ContributeAuthActivity();
}
