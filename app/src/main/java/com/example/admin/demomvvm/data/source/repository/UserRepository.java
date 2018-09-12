package com.example.admin.demomvvm.data.source.repository;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.util.Log;

import com.example.admin.demomvvm.data.model.User;
import com.example.admin.demomvvm.data.source.UserDataSource;
import com.example.admin.demomvvm.data.source.remote.config.response.SearchUserResponse;
import com.example.admin.demomvvm.data.source.remote.config.service.GithubApi;
import com.example.admin.demomvvm.data.source.remote.config.service.GithubClient;

import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by TamTT on 9/10/2018.
 */

public class UserRepository implements UserDataSource.LocalDataSource,
        UserDataSource.RemoteDataSource {

    private static UserRepository sInstance;
    private GithubApi mGithubApi;

    public UserRepository() {
        mGithubApi = GithubClient.getInstance();
    }

    public static synchronized UserRepository getInstance() {
        if (sInstance == null) {
            sInstance = new UserRepository();
        }
        return sInstance;
    }

    @Override
    public LiveData<SearchUserResponse> getAllUser(String searchTerm, String limit) {
        final MutableLiveData<SearchUserResponse> data = new MutableLiveData<>();

        mGithubApi.searchGithubUsers(searchTerm, limit)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<SearchUserResponse>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        Log.e("TAG", "onSubscribe");
                    }

                    @Override
                    public void onSuccess(SearchUserResponse searchUserResponse) {
                        data.setValue(searchUserResponse);
                        Log.e("TAG", searchUserResponse.toString());
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e("TAG", e.toString());
                        data.setValue(null);
                    }
                });
        return data;
    }

    public LiveData<User> getUser(String id) {
        final MutableLiveData<User> detailUser = new MutableLiveData<>();

        mGithubApi.getUser(id).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<User>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        Log.e("TAG", "onSubscribe");
                    }

                    @Override
                    public void onSuccess(User user) {
                        Log.e("TAG", user.toString());
                        detailUser.setValue(user);
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e("TAG", e.toString());
                        detailUser.setValue(null);
                    }
                });
        return detailUser;
    }
}
