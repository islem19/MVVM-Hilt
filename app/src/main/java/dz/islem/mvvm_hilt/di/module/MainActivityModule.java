package dz.islem.mvvm_hilt.di.module;

import androidx.lifecycle.ViewModelProvider;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.android.components.ActivityComponent;
import dz.islem.mvvm_hilt.data.DataManager;
import dz.islem.mvvm_hilt.ui.main.MainViewModel;
import dz.islem.mvvm_hilt.ui.main.MainViewModelFactory;

@InstallIn(ActivityComponent.class)
@Module
public class MainActivityModule {

    @Provides
    static MainViewModel providesViewModel(DataManager dataManager){
        return new MainViewModel(dataManager);
    }

    @Provides
    static ViewModelProvider.Factory provideViewModelProvider(MainViewModel mainViewModel){
        return new MainViewModelFactory(mainViewModel);
    }
}
