package dz.islem.mvvm_hilt.ui.detail;

import android.os.Bundle;
import android.widget.TextView;

import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import dagger.hilt.android.AndroidEntryPoint;
import dz.islem.mvvm_hilt.R;
import dz.islem.mvvm_hilt.data.model.Post;
import dz.islem.mvvm_hilt.ui.base.BaseActivity;

@AndroidEntryPoint
public class DetailActivity extends BaseActivity<DetailViewModel> {

    @BindView(R.id.recycler_view_comments)
    RecyclerView recyclerView;
    @BindView(R.id.tv_post_title)
    TextView textViewTitle;
    @BindView(R.id.tv_post_body)
    TextView textViewBody;
    @Inject
    DetailViewModelFactory detailViewModelFactory;
    @Inject
    CommentAdapter commentAdapter;
    private int postId;

    @Override
    protected DetailViewModel createViewModel() {
        return new ViewModelProvider(this, detailViewModelFactory).get(DetailViewModel.class);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        ButterKnife.bind(this);
        postId = getIntent().getIntExtra("postId", 1);
        setupRecyclerView();
        viewModel.getPost().observe(this, this::setPostView);
        viewModel.getComments().observe(this, comments -> commentAdapter.addComments(comments));
        viewModel.loadPost(postId);
        viewModel.loadPostComment(postId);

    }

    private void setPostView(Post post) {
        textViewTitle.setText(post.getTitle());
        textViewBody.setText(post.getBody());
    }


    private void setupRecyclerView() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        commentAdapter = new CommentAdapter();
        recyclerView.setAdapter(commentAdapter);
    }

}
