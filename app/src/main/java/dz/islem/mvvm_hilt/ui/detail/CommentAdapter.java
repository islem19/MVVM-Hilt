package dz.islem.mvvm_hilt.ui.detail;

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
import dz.islem.mvvm_hilt.R;
import dz.islem.mvvm_hilt.data.model.Comment;

public class CommentAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<Comment> comments;

    @Inject
    public CommentAdapter(){
        comments = new ArrayList<>();
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_comment, parent,false);
        return new PostHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ((PostHolder)holder).setName(comments.get(position).getName());
        ((PostHolder)holder).setEmail(comments.get(position).getEmail());
        ((PostHolder)holder).setComment(comments.get(position).getBody());
    }

    @Override
    public int getItemCount() {
        return comments == null ?  0 : comments.size();
    }

    public Comment getComment(int postion){
        return comments.get(postion);
    }

    public void clear(){
        comments.clear();
        notifyDataSetChanged();
    }

    public void addComments(List<Comment> comments) {
        this.comments.addAll(comments);
        notifyDataSetChanged();

    }

    class PostHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_user)
        TextView textViewName;
        @BindView(R.id.tv_email)
        TextView textViewEmail;
        @BindView(R.id.tv_comment)
        TextView textViewComment;

        PostHolder(View view) {
            super(view);
            ButterKnife.bind(this,view);
        }

        void setName(String name){
            textViewName.setText(name);
        }
        void setEmail(String email){
            textViewEmail.setText(email);
        }
        void setComment(String comment){
            textViewComment.setText(comment);
        }
    }
}
