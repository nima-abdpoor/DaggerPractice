package com.chinachino.daggerpractice.di.auth;

import com.chinachino.daggerpractice.network.auth.AuthAPI;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;

@Module
public class AuthModule {
    @Provides
    static AuthAPI provideAuthAPI(Retrofit retrofit){
        return retrofit.create(AuthAPI.class);
    }
}