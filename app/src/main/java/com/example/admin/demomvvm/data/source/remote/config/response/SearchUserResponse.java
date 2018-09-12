package com.example.admin.demomvvm.data.source.remote.config.response;

import com.example.admin.demomvvm.data.model.User;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by TamTT on 9/10/2018.
 */

public class SearchUserResponse {

    public SearchUserResponse(List<User> items) {
        this.items = items;
    }

    @SerializedName("total_count")
    @Expose
    private Integer totalCount;

    @SerializedName("items")
    @Expose
    private List<User> items = new ArrayList<>();

    public Integer getTotalCount() {
        return totalCount;
    }

    public List<User> getItems() {
        return items;
    }

    public void setItems(List<User> items) {
        this.items = items;
    }

    @Override
    public String toString() {
        return "SearchUserResponse{" +
                "totalCount=" + totalCount +
                ", items=" + items +
                '}';
    }
}
