package com.example.admin.demomvvm.screen.main;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.example.admin.demomvvm.R;
import com.example.admin.demomvvm.data.model.User;
import com.example.admin.demomvvm.data.source.remote.config.response.SearchUserResponse;
import com.example.admin.demomvvm.databinding.MainLayoutBinding;
import com.example.admin.demomvvm.screen.detail.DetailActivity;

public class MainActivity extends AppCompatActivity {
    private MainLayoutBinding mBinding;
    private UserAdapter mAdapter;
    private UserClickListener mListener = new UserClickListener() {
        @Override
        public void onClick(User user) {
            Intent intent = DetailActivity.getIntentDetail(MainActivity.this, String.valueOf(user.getId()));
            startActivity(intent);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bind();
        mBinding.setIsLoading(true);
        MainViewModel mainViewModel = ViewModelProviders.of(MainActivity.this).get(MainViewModel.class);
        mainViewModel.getUserObservable("tam", "40").observe(this, new Observer<SearchUserResponse>() {
            @Override
            public void onChanged(@Nullable SearchUserResponse searchUserResponse) {
                Log.e("TAG_", searchUserResponse.getItems().toString());
                mBinding.setIsLoading(false);
                mAdapter.setProjectList(searchUserResponse.getItems());
            }
        });
    }

    public void bind(){
        mBinding = DataBindingUtil.setContentView(this,
                R.layout.main_layout);
        mAdapter = new UserAdapter(mListener);
        mBinding.recyclerUser.setAdapter(mAdapter);
        mBinding.setIsLoading(true);
        mBinding.getRoot();
    }

}
