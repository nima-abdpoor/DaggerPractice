package com.chinachino.daggerpractice.di.main;

import com.chinachino.daggerpractice.UI.post.PostsRecyclerAdapter;
import com.chinachino.daggerpractice.network.main.MainAPI;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;

@Module
public class MainModule {

    @Provides
    static PostsRecyclerAdapter providePostRecycler(){
        return new PostsRecyclerAdapter();
    }


    @Provides
    static MainAPI provideMainAPI(Retrofit retrofit){
        return retrofit.create(MainAPI.class);
    }
}
