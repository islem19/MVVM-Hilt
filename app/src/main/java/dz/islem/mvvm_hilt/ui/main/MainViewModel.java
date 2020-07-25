package dz.islem.mvvm_hilt.ui.main;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import java.util.List;

import javax.inject.Inject;

import dz.islem.mvvm_hilt.data.DataManager;
import dz.islem.mvvm_hilt.data.model.Post;
import dz.islem.mvvm_hilt.ui.base.BaseViewModel;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class MainViewModel extends BaseViewModel {
    private final String TAG = "MainViewModel";
    private final DataManager dataManager;
    private MutableLiveData<List<Post>> posts = new MutableLiveData<>();

    @Inject
    public MainViewModel(DataManager dataManager){
        this.dataManager = dataManager;
    }

    public MutableLiveData<List<Post>> getPosts() {
        return posts;
    }

    public void loadPosts(){
        compositeDisposable.add(
                dataManager.loadPosts()
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribeOn(Schedulers.io())
                        .subscribe( posts -> {
                            if (posts != null) this.posts.postValue(posts);
                            },error -> Log.e(TAG, "loadPosts: " + error )
                        ));
    }
}
