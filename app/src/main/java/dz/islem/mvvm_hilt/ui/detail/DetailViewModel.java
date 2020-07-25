package dz.islem.mvvm_hilt.ui.detail;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import java.util.List;

import javax.inject.Inject;

import dz.islem.mvvm_hilt.data.DataManager;
import dz.islem.mvvm_hilt.data.model.Comment;
import dz.islem.mvvm_hilt.data.model.Post;
import dz.islem.mvvm_hilt.ui.base.BaseViewModel;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class DetailViewModel extends BaseViewModel {
    private final String TAG = "MainViewModel";
    private final DataManager dataManager;
    private MutableLiveData<Post> post = new MutableLiveData<>();
    private MutableLiveData<List<Comment>> comments = new MutableLiveData<>();

    @Inject
    public DetailViewModel(DataManager dataManager){
        this.dataManager = dataManager;
    }

    public MutableLiveData<Post> getPost() {
        return post;
    }

    public MutableLiveData<List<Comment>> getComments() {
        return comments;
    }

    public void loadPost(int id){
        compositeDisposable.add(
                dataManager.loadPostById(id)
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribeOn(Schedulers.io())
                        .subscribe( post -> {
                            if (post != null) this.post.postValue(post);
                            },error -> Log.e(TAG, "loadPosts: " + error )
                        ));
    }

    public void loadPostComment(int id){
        compositeDisposable.add(
                dataManager.loadCommentByPost(id)
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribeOn(Schedulers.io())
                        .subscribe( comments -> {
                                    if (comments != null) this.comments.postValue(comments);
                                },error -> Log.e(TAG, "loadPosts: " + error )
                        ));
    }
}
