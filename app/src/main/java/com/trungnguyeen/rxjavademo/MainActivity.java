package com.trungnguyeen.rxjavademo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;

import com.trungnguyeen.rxjavademo.data.remote.SOService;
import com.trungnguyeen.rxjavademo.utiles.ApiUtils;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();
    private Disposable disposable;
    private GithubRepoAdapter githubRepoAdapter = new GithubRepoAdapter();
    private RecyclerView recyclerView;
    private SOService mService = ApiUtils.getSOService();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.list_view_repos);
        recyclerView.setAdapter(githubRepoAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.addItemDecoration(new DividerItemDecoration(this,
                DividerItemDecoration.VERTICAL));
        final EditText editTextUsername = findViewById(R.id.edit_text_username);
        final Button buttonSearch = findViewById(R.id.button_search);
        buttonSearch.setOnClickListener(v -> {
            final String username = editTextUsername.getText().toString();
            if (!TextUtils.isEmpty(username)) {
                getStarredRepos(username);
            }
        });
    }

    @Override
    protected void onDestroy() {
        if (disposable != null && !disposable.isDisposed()) {
            disposable.dispose();
        }

        super.onDestroy();
    }


    private void getStarredRepos(String username) {
        disposable = mService.getStarredRepositories(username)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(
                                gitHubRepos -> {
                                    Log.i(TAG, "RxJava2: Response from server ...");
                                    githubRepoAdapter.setGitHubRepos(gitHubRepos);
                                },
                                t -> Log.i(TAG, "RxJava2, HTTP Error: " + t.getMessage())
                        );
    }
}
