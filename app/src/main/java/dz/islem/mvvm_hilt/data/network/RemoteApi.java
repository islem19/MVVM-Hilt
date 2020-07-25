package dz.islem.mvvm_hilt.data.network;


import java.util.List;

import dz.islem.mvvm_hilt.data.model.Comment;
import dz.islem.mvvm_hilt.data.model.Post;
import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface RemoteApi {

    @GET("/posts")
    Observable<List<Post>> loadPosts();

    @GET("/posts/{id}")
    Observable<Post> loadPostById(@Path("id") int id);

    @GET("/posts/{id}/comments")
    Observable<List<Comment>> loadCommentByPost(@Path("id") int id);
}
