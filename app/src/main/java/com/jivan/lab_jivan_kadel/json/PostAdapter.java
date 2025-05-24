package com.jivan.lab_jivan_kadel.json;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.jivan.lab_jivan_kadel.R;

import java.util.List;

public class PostAdapter extends RecyclerView.Adapter<PostAdapter.ViewHolder> {
    List<Post> posts;

    public PostAdapter(List<Post> posts) {
        this.posts = posts;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_post, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Post post = posts.get(position);
        if (post != null && !post.getTitle().isEmpty() && !post.getBody().isEmpty()) {
            holder.tvPostId.setText(String.valueOf(post.getId()));
            holder.tvPostTitle.setText(post.getTitle());
            holder.tvPostBody.setText(post.getBody());
        }
    }

    @Override
    public int getItemCount() {
        return posts.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvPostId, tvPostTitle, tvPostBody;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvPostId = itemView.findViewById(R.id.tvPostId);
            tvPostTitle = itemView.findViewById(R.id.tvPostTitle);
            tvPostBody = itemView.findViewById(R.id.tvPostBody);

        }
    }


}
