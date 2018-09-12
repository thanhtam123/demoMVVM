package com.example.admin.demomvvm.data.source.remote.config.service;

import com.example.admin.demomvvm.data.model.User;
import com.example.admin.demomvvm.data.source.remote.config.response.SearchUserResponse;

import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by TamTT on 9/10/2018.
 */

public interface GithubApi {
    @GET("/search/users")
    Single<SearchUserResponse> searchGithubUsers(@Query("q") String searchTerm,
                                                 @Query("per_page") String limit);

    @GET("/users/{username}")
    Single<User> getUser(@Path("username") String username);
}
