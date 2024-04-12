import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class CommentActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private CommentAdapter commentAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comment);

        String user = getIntent().getStringExtra("user");
        String post = getIntent().getStringExtra("post");

        setTitle("Comments for " + user);

        recyclerView = findViewById(R.id.recycler_view_comments);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        List<Comment> comments = generateSampleComments(user, post);
        commentAdapter = new CommentAdapter(comments);
        recyclerView.setAdapter(commentAdapter);
    }

    private List<Comment> generateSampleComments(String user, String post) {
        List<Comment> comments = new ArrayList<>();
        // Adicionar dados de exemplo de comentários relacionados a este usuário e post
        comments.add(new Comment(user, "Comment 1 for " + post));
        comments.add(new Comment("User2", "Comment 2 for " + post));
        comments.add(new Comment(user, "Comment 3 for " + post));
        return comments;
    }

    private static class CommentAdapter extends RecyclerView.Adapter<CommentAdapter.CommentViewHolder> {

        private List<Comment> comments;

        public CommentAdapter(List<Comment> comments) {
            this.comments = comments;
        }

        @Override
        public CommentViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return new CommentViewHolder(LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.list_item_comment, parent, false));
        }

        @Override
        public void onBindViewHolder(CommentViewHolder holder, int position) {
            Comment comment = comments.get(position);
            holder.textUserComment.setText(comment.getUser());
            holder.textComment.setText(comment.getComment());
        }

        @Override
        public int getItemCount() {
            return comments.size();
        }

        public static class CommentViewHolder extends RecyclerView.ViewHolder {
            TextView textUserComment;
            TextView textComment;

            public CommentViewHolder(android.view.View itemView) {
                super(itemView);
                textUserComment = itemView.findViewById(R.id.text_user_comment);
                textComment = itemView.findViewById(R.id.text_comment);
            }
        }
    }

    private static class Comment {
        private String user;
        private String comment;

        public Comment(String user, String comment) {
            this.user = user;
            this.comment = comment;
        }

        public String getUser() {
            return user;
        }

        public String getComment() {
            return comment;
        }
    }
}