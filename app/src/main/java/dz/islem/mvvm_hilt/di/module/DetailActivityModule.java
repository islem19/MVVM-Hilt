package dz.islem.mvvm_hilt.di.module;

import androidx.lifecycle.ViewModelProvider;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.android.components.ActivityComponent;
import dz.islem.mvvm_hilt.data.DataManager;
import dz.islem.mvvm_hilt.ui.detail.DetailViewModel;
import dz.islem.mvvm_hilt.ui.detail.DetailViewModelFactory;

@InstallIn(ActivityComponent.class)
@Module
public class DetailActivityModule {

    @Provides
    static DetailViewModel providesViewModel(DataManager dataManager){
        return new DetailViewModel(dataManager);
    }

    @Provides
    static ViewModelProvider.Factory provideViewModelProvider(DetailViewModel detailViewModel){
        return new DetailViewModelFactory(detailViewModel);
    }
}
