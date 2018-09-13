package com.example.admin.demomvvm.screen.main;

import android.databinding.DataBindingUtil;
import android.databinding.ObservableField;
import android.support.v7.util.DiffUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.example.admin.demomvvm.R;
import com.example.admin.demomvvm.data.model.User;
import com.example.admin.demomvvm.databinding.ItemLayoutBinding;

import java.util.List;

/**
 * Created by TamTT on 9/10/2018.
 */

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.UserViewHolder>{

    private List<User> mUsers;

    private UserClickListener mListener;

    public UserAdapter(UserClickListener listener) {
        mListener = listener;
    }

    @Override
    public UserViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ItemLayoutBinding binding = DataBindingUtil
                .inflate(LayoutInflater.from(parent.getContext())
                         , R.layout.item_layout, parent,false);
        binding.setListener(mListener);
        return new UserViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(UserViewHolder holder, int position) {
        holder.setBinding(mUsers.get(position));
    }

    @Override
    public int getItemCount() {
        return mUsers == null ? 0 : mUsers.size();
    }

    public void setProjectList(final List<User> users) {
        if (this.mUsers == null) {
            this.mUsers = users;
            notifyItemRangeInserted(0, mUsers.size());
        } else {
            DiffUtil.DiffResult result = DiffUtil.calculateDiff(new DiffUtil.Callback() {
                @Override
                public int getOldListSize() {
                    return UserAdapter.this.mUsers.size();
                }

                @Override
                public int getNewListSize() {
                    return mUsers.size();
                }

                @Override
                public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
                    return UserAdapter.this.mUsers.get(oldItemPosition).getId() ==
                            mUsers.get(newItemPosition).getId();
                }

                @Override
                public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
                    User user = mUsers.get(newItemPosition);
                    User old = mUsers.get(oldItemPosition);
                    return user.getId() == old.getId();
                }
            });
            this.mUsers = users;
            result.dispatchUpdatesTo(this);
        }
    }

    public static class UserViewHolder extends RecyclerView.ViewHolder{
        public ObservableField<String> name = new ObservableField<>();
        private ItemLayoutBinding mBinding;
        UserViewHolder(ItemLayoutBinding binding) {
            super(binding.getRoot());
            mBinding = binding;
        }

        void setBinding(User user) {
            mBinding.setUser(user);
            mBinding.executePendingBindings();
        }
    }
}
