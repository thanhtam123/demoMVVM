package com.example.admin.demomvvm.data.source.remote.config.service;

import com.example.admin.demomvvm.Constant;

/**
 * Created by TamTT on 9/10/2018.
 */

public class GithubClient extends ServiceClient {
    private static GithubApi sGithubApiInstance;

    public static GithubApi getInstance() {
        if (sGithubApiInstance == null) {
            return createService(GithubApi.class, Constant.GITHUB_BASE_URL);
        }
        return sGithubApiInstance;
    }
}
