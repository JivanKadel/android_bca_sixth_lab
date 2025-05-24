package com.jivan.lab_jivan_kadel.json;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.jivan.lab_jivan_kadel.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class PostsActivity extends AppCompatActivity {
    RecyclerView rvPosts;
    PostAdapter postAdapter;
    List<Post> posts;

    private static final String POST_API_URL = "https://jsonplaceholder.typicode.com/posts";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_posts);

        rvPosts = findViewById(R.id.rvPosts);
        rvPosts.setLayoutManager(new LinearLayoutManager(this));

        posts = new ArrayList<>();


        fetchPosts();


        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            var paddingLeft = findViewById(R.id.main).getPaddingLeft();
            v.setPadding(paddingLeft, systemBars.top, paddingLeft, systemBars.bottom);
            return insets;
        });
    }

    @Override
    protected void onStart() {
        super.onStart();

        postAdapter = new PostAdapter(posts);
        rvPosts.setAdapter(postAdapter);
    }

    public void fetchPosts() {
        // set progress bar visible
        ProgressBar pbPostsLoading = findViewById(R.id.pbPostsLoading);
        pbPostsLoading.setVisibility(View.VISIBLE);

        // add request to queue
        RequestQueue queue = Volley.newRequestQueue(this);
        JsonArrayRequest postArrayRequest = new JsonArrayRequest(Request.Method.GET, POST_API_URL, null, response -> {
            pbPostsLoading.setVisibility(View.GONE);
            try {
                for (int i = 0; i < response.length(); i++) {
                    JSONObject postObject = response.getJSONObject(i);
                    int id = postObject.getInt("id");
                    String title = postObject.getString("title");
                    String body = postObject.getString("body");

                    posts.add(new Post(id, title, body));
                }
                postAdapter.notifyDataSetChanged();
            } catch (JSONException e) {
                Log.e("POST_FETCH_ERROR", "Post fetching issue: ", e);
                handleError(e.getMessage());
            }
        }, error -> {
            pbPostsLoading.setVisibility(View.GONE); // Hide loader
            handleError(error.toString());
        });

        queue.add(postArrayRequest);
    }

    private void handleError(String message) {
        Toast.makeText(PostsActivity.this, "Error: " + message, Toast.LENGTH_SHORT).show();
    }
}