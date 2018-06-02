package com.trungnguyeen.rxjavademo;


import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.trungnguyeen.rxjavademo.data.GitHubRepo;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by trungnguyeen on 6/2/18.
 */

public class GithubRepoAdapter extends RecyclerView.Adapter<GithubRepoAdapter.GitHubRepoViewHolder>{

    private ArrayList<GitHubRepo> gitHubRepos = new ArrayList<>();;

    @NonNull
    @Override
    public GitHubRepoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//        final LayoutInflater inflater = LayoutInflater.from(parent.getContext());
//        final View view = inflater.inflate(R.layout.item_github_repo, parent, false);
//        final GitHubRepoViewHolder viewHolder = new GitHubRepoViewHolder(view);
//        view.setTag(viewHolder);
//        return viewHolder;
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_github_repo, parent, false);
        return new GitHubRepoViewHolder(view);
    }

    public void add(GitHubRepo gitHubRepo) {
        gitHubRepos.add(gitHubRepo);
        notifyDataSetChanged();
    }

    @Override public long getItemId(int position) {
        return position;
    }
    public void setGitHubRepos(@Nullable List<GitHubRepo> repos) {
        if (repos == null) {
            return;
        }
        gitHubRepos.clear();
        gitHubRepos.addAll(repos);
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return gitHubRepos.size();
    }

    @Override
    public void onBindViewHolder(@NonNull GitHubRepoViewHolder holder, int position) {
        GitHubRepo gitHubRepo = gitHubRepos.get(position);
        holder.bindView(gitHubRepo);
    }

    public class GitHubRepoViewHolder extends RecyclerView.ViewHolder{
        private TextView textRepoName;
        private TextView textRepoDescription;
        private TextView textLanguage;
        private TextView textStars;

        public GitHubRepoViewHolder(View itemview) {
            super(itemview);
            textRepoName = itemview.findViewById(R.id.text_repo_name);
            textRepoDescription = itemview.findViewById(R.id.text_repo_description);
            textLanguage = itemview.findViewById(R.id.text_language);
            textStars = itemview.findViewById(R.id.text_stars);
        }

        public void bindView(GitHubRepo gitHubRepo) {
            textRepoName.setText(gitHubRepo.getName());
            textRepoDescription.setText(gitHubRepo.getDescription());
            textLanguage.setText("Language: " + gitHubRepo.getLanguage());
            textStars.setText("Stars: " + gitHubRepo.getStargazersCount());
        }
    }
}
