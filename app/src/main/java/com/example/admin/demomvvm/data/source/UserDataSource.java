package com.example.admin.demomvvm.data.source;

import android.arch.lifecycle.LiveData;

import com.example.admin.demomvvm.data.source.remote.config.response.SearchUserResponse;

/**
 * Created by TamTT on 9/10/2018.
 */

public interface UserDataSource {
    interface LocalDataSource{

    }

    interface RemoteDataSource{
        LiveData<SearchUserResponse> getAllUser(String searchTerm, String limit);
    }
}
