package dz.islem.mvvm_hilt.di.module;

import android.content.Context;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.android.components.ApplicationComponent;
import dz.islem.mvvm_hilt.App;

@InstallIn(ApplicationComponent.class)
@Module
public class AppModule {

    @Singleton
    @Provides
    static Context provideContext(App app){
        return app;
    }

}
