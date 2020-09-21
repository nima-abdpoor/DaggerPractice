package com.chinachino.daggerpractice.network.auth;

import com.chinachino.daggerpractice.model.User;

import io.reactivex.Flowable;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface AuthAPI {
    @GET("users/{id}")
    Flowable<User> getUsers(@Path("id") int id);
}
