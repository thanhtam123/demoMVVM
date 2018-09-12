package com.example.admin.demomvvm.screen.main;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import com.example.admin.demomvvm.data.source.remote.config.response.SearchUserResponse;
import com.example.admin.demomvvm.data.source.repository.UserRepository;

/**
 * Created by TamTT on 9/10/2018.
 */

public class MainViewModel extends AndroidViewModel {

    public MainViewModel(@NonNull Application application) {
        super(application);
    }

    LiveData<SearchUserResponse> getUserObservable(String q, String limit) {
        UserRepository mUserRepository = UserRepository.getInstance();
        return mUserRepository.getAllUser(q, limit);
    }
}
