package com.chinachino.daggerpractice.UI.post;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chinachino.daggerpractice.R;
import com.chinachino.daggerpractice.UI.main.Resource;
import com.chinachino.daggerpractice.Utils.VerticalSpacingItemDecoration;
import com.chinachino.daggerpractice.ViewModels.ViewModelProviderFactory;
import com.chinachino.daggerpractice.model.Post;

import java.util.List;

import javax.inject.Inject;

import dagger.android.support.DaggerFragment;

public class PostFragment extends DaggerFragment {

    private static final String TAG = "PostFragment";

    RecyclerView recyclerView;
    PostViewModel viewModel;

    @Inject
    PostsRecyclerAdapter adapter;

    @Inject
    ViewModelProviderFactory providerFactory;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_post,container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        recyclerView = view.findViewById(R.id.recycler_view);
        viewModel = ViewModelProviders.of(this,providerFactory).get(PostViewModel.class);
        initRecyclerView();
        subscribeObservers();
    }
    public void subscribeObservers(){
        viewModel.observePosts().removeObservers(getViewLifecycleOwner());
        viewModel.observePosts().observe(getViewLifecycleOwner(), (Resource<List<Post>> listResource) -> {
            if (listResource.data != null){
                switch (listResource.status){
                    case LOADING:{
                        Log.d(TAG, "subscribeObservers: Loading...");
                    }
                    case ERROR:{
                        Log.e(TAG, "subscribeObservers: " + listResource.message);
                    }
                    case SUCCESS:{
                        Log.d(TAG, "subscribeObservers: " + listResource.data);
                        adapter.setPosts(listResource.data);
                    }
                }
            }
        });
    }
    private void initRecyclerView(){
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.addItemDecoration(new VerticalSpacingItemDecoration(15));
        recyclerView.setAdapter(adapter);
    }
}
