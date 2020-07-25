package dz.islem.mvvm_hilt.ui.detail;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import javax.inject.Inject;

public class DetailViewModelFactory implements ViewModelProvider.Factory {


    private final DetailViewModel viewModel;

    @Inject
    public DetailViewModelFactory(DetailViewModel viewModel){
        this.viewModel = viewModel;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.isAssignableFrom(viewModel.getClass())) {
            return (T) viewModel;
        }
        throw new IllegalArgumentException("unknown viewmodel");
    }
}
