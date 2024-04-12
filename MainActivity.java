import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private PostAdapter postAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        List<Post> posts = generateSampleData();
        postAdapter = new PostAdapter(posts);
        recyclerView.setAdapter(postAdapter);
    }

    private List<Post> generateSampleData() {
        List<Post> posts = new ArrayList<>();
        posts.add(new Post("User1", "This is post 1"));
        posts.add(new Post("User2", "This is post 2"));
        posts.add(new Post("User3", "This is post 3"));
        return posts;
    }

    private static class PostAdapter extends RecyclerView.Adapter<PostAdapter.PostViewHolder> {

        private List<Post> posts;

        public PostAdapter(List<Post> posts) {
            this.posts = posts;
        }

        @NonNull
        @Override
        public PostViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_post, parent, false);
            return new PostViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull PostViewHolder holder, int position) {
            final Post post = posts.get(position);
            holder.userPostTextView.setText(post.getUser());
            holder.postTextView.setText(post.getPost());

            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // Abrir a tela de comentários aqui
                    Intent intent = new Intent(v.getContext(), CommentActivity.class);
                    intent.putExtra("user", post.getUser());
                    intent.putExtra("post", post.getPost());
                    v.getContext().startActivity(intent);
                }
            });
        }

        @Override
        public int getItemCount() {
            return posts.size();
        }

        public static class PostViewHolder extends RecyclerView.ViewHolder {
            TextView userPostTextView;
            TextView postTextView;

            public PostViewHolder(@NonNull View itemView) {
                super(itemView);
                userPostTextView = itemView.findViewById(R.id.text_user_post);
                postTextView = itemView.findViewById(R.id.text_post);
            }
        }
    }

    private static class Post {
        private String user;
        private String post;

        public Post(String user, String post) {
            this.user = user;
            this.post = post;
        }

        public String getUser() {
            return user;
        }

        public String getPost() {
            return post;
        }
    }
}