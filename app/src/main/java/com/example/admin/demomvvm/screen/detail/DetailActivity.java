package com.example.admin.demomvvm.screen.detail;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.example.admin.demomvvm.Constant;
import com.example.admin.demomvvm.R;
import com.example.admin.demomvvm.data.model.User;
import com.example.admin.demomvvm.databinding.ActivityDetailBinding;

public class DetailActivity extends AppCompatActivity {
    private DetailViewModel mDetailViewModel;
    private ActivityDetailBinding mActivityDetailBinding;

    public static Intent getIntentDetail(Context context, String id) {
        Intent intent = new Intent(context, DetailActivity.class);
        intent.putExtra(Constant.EXTRA_USER, id);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        bind();
        mDetailViewModel = ViewModelProviders.of(DetailActivity.this)
                .get(DetailViewModel.class);
        String id = getIntent().getStringExtra(Constant.EXTRA_USER);

        mDetailViewModel.getUser(id).observe(DetailActivity.this, new Observer<User>() {
            @Override
            public void onChanged(@Nullable User user) {
                mActivityDetailBinding.setUser(user);
            }
        });
    }

    public void bind() {
        mActivityDetailBinding = DataBindingUtil.setContentView(this,
                R.layout.activity_detail);
    }
}
