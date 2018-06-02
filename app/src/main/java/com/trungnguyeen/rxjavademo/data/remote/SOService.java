package com.trungnguyeen.rxjavademo.data.remote;

import com.trungnguyeen.rxjavademo.data.GitHubRepo;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by trungnguyeen on 4/5/18.
 */

public interface SOService {
    @GET("users/{user}/starred")
    Observable<List<GitHubRepo>> getStarredRepositories(@Path("user") String userName);

    @GET("users/{user}/starred")
    Call<List<GitHubRepo>> get(@Path("user") String userName);

}
