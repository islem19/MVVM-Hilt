package dz.islem.mvvm_hilt.ui.main;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import dagger.hilt.android.qualifiers.ApplicationContext;
import dz.islem.mvvm_hilt.R;
import dz.islem.mvvm_hilt.data.model.Post;
import dz.islem.mvvm_hilt.ui.detail.DetailActivity;

public class MainAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private final Context context;
    private List<Post> posts;

    @Inject
    public MainAdapter(@ApplicationContext Context context){
        posts = new ArrayList<>();
        this.context = context;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_post, parent,false);
        return new PostHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ((PostHolder)holder).setTitle(posts.get(position).getTitle());
        ((PostHolder)holder).setBody(posts.get(position).getBody());
    }

    @Override
    public int getItemCount() {
        return posts == null ?  0 : posts.size();
    }

    public Post getPost(int postion){
        return posts.get(postion);
    }

    public void clear(){
        posts.clear();
        notifyDataSetChanged();
    }

    public void addPosts(List<Post> posts){
        this.posts.addAll(posts);
        notifyDataSetChanged();
    }

    class PostHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        @BindView(R.id.tv_post_title)
        TextView textViewTitle;
        @BindView(R.id.tv_post_body)
        TextView textViewBody;

        PostHolder(View view) {
            super(view);
            view.setOnClickListener(this);
            ButterKnife.bind(this,view);
        }

        void setTitle(String title){
            textViewTitle.setText(title);
        }
        void setBody(String body){
            textViewBody.setText(body);
        }

        @Override
        public void onClick(View view) {
            int itemPosition = getLayoutPosition();
            startDetails(itemPosition);

        }
    }

    private void startDetails(int itemPosition) {
        Post item = posts.get(itemPosition);
        Intent myIntent = new Intent(context, DetailActivity.class);
        myIntent.putExtra("postId", item.getId());
        context.startActivity(myIntent);
    }
}
