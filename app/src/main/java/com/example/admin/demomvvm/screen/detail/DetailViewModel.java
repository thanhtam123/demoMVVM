package com.example.admin.demomvvm.screen.detail;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import com.example.admin.demomvvm.data.model.User;
import com.example.admin.demomvvm.data.source.repository.UserRepository;

/**
 * Created by TamTT on 9/11/2018.
 */

public class DetailViewModel extends AndroidViewModel{

    private UserRepository mUserRepository;

    public DetailViewModel(@NonNull Application application) {
        super(application);
        mUserRepository = UserRepository.getInstance();
    }

    LiveData<User> getUser(String id){
        return mUserRepository.getUser(id);
    }
}
