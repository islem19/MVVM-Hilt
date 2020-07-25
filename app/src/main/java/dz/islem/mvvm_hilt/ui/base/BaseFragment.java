package dz.islem.mvvm_hilt.ui.base;

import android.content.Context;

import androidx.fragment.app.Fragment;

public abstract class BaseFragment <VM extends BaseViewModel> extends Fragment {

    private VM viewModel;
    public abstract VM createViewModel();


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        viewModel = createViewModel();
    }

    @Override
    public void onDetach() {
        super.onDetach();
        viewModel.cleanupVM();
    }
}
