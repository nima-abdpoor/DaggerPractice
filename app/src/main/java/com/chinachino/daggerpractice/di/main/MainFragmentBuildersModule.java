package com.chinachino.daggerpractice.di.main;

import com.chinachino.daggerpractice.UI.main.profile.ProfileFragment;
import com.chinachino.daggerpractice.UI.post.PostFragment;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class MainFragmentBuildersModule {

    @ContributesAndroidInjector
    abstract ProfileFragment contributeProfileFragment();

    @ContributesAndroidInjector
    abstract PostFragment contributePostFragment();
}
