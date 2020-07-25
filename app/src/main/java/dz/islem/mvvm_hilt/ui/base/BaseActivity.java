package dz.islem.mvvm_hilt.ui.base;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

public abstract class BaseActivity <VM extends BaseViewModel> extends AppCompatActivity {


    protected VM viewModel;

    protected abstract VM createViewModel();

    @Override
    protected void onCreate(final Bundle bundle){
        super.onCreate(bundle);
        viewModel = createViewModel();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        viewModel.cleanupVM();
    }

}
