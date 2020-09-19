package com.chinachino.daggerpractice.di;

import android.app.Application;

import com.chinachino.daggerpractice.BaseActivity;

import dagger.BindsInstance;
import dagger.Component;
import dagger.android.AndroidInjector;
import dagger.android.support.AndroidSupportInjectionModule;

@Component(
        modules = {
                AndroidSupportInjectionModule.class,
                ActivityBuildersModule.class,
        }
)
public interface AppComponent extends AndroidInjector<BaseActivity> {

    @Component.Builder
    interface Builder {
        @BindsInstance
        Builder Application(Application application);

        AppComponent build();
    }

}
