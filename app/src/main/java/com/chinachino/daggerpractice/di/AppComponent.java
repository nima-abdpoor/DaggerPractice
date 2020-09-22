package com.chinachino.daggerpractice.di;

import android.app.Application;

import com.chinachino.daggerpractice.BaseApplication;
import com.chinachino.daggerpractice.SessionManager;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;
import dagger.android.AndroidInjector;
import dagger.android.support.AndroidSupportInjectionModule;
import dagger.android.support.DaggerApplication;

@Singleton
@Component(
        modules = {
                AndroidSupportInjectionModule.class,
                ActivityBuildersModule.class,
                AppModule.class,
                ViewModelFactoryModule.class,
        }
)
public interface AppComponent extends AndroidInjector<BaseApplication> {
    SessionManager sessionManager();

    @Component.Builder
    interface Builder {
        @BindsInstance
        Builder Application(Application application);

        AndroidInjector<? extends DaggerApplication> build();
    }

}
