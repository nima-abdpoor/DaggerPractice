package com.chinachino.daggerpractice.UI.post;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.LiveDataReactiveStreams;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.ViewModel;

import com.chinachino.daggerpractice.SessionManager;
import com.chinachino.daggerpractice.UI.main.Resource;
import com.chinachino.daggerpractice.model.Post;
import com.chinachino.daggerpractice.network.main.MainAPI;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.schedulers.Schedulers;


public class PostViewModel extends ViewModel {

    private static final String TAG = "PostViewModel";

    final SessionManager sessionManager;
    final MainAPI mainAPI;

    MediatorLiveData<Resource<List<Post>>> posts;

    @Inject
    public PostViewModel(SessionManager sessionManager, MainAPI mainAPI) {
        this.sessionManager = sessionManager;
        this.mainAPI = mainAPI;
        Log.d(TAG, "PostViewModel: viewModel is working ... ");
    }
    public LiveData<Resource<List<Post>>> observePosts(){
        if (posts == null){
            posts = new MediatorLiveData<>();
            posts.setValue(Resource.loading(null));

            final LiveData<Resource<List<Post>>> source = LiveDataReactiveStreams.fromPublisher(
                    mainAPI.getPostsFromUser(sessionManager.getMediatorLiveData().getValue().data.getId())
                    .onErrorReturn(throwable -> {
                        Log.e(TAG, "apply: ", throwable);
                        Post post = new Post();
                        post.setId(-1);
                        ArrayList<Post> posts = new ArrayList<>();
                        posts.add(post);
                        return posts;
                    })
                    .map(posts -> {
                        if (posts.size() >0 ){
                            if (posts.get(0).getId() == -1){
                                return Resource.error("something went wronge...",posts);
                            }
                        }
                        return Resource.success(posts);
                    })
                    .subscribeOn(Schedulers.io())
            );
            posts.addSource(source, listResource -> {
                posts.setValue(listResource);
                posts.removeSource(source);
            });
        }
        return posts;
    }
}
