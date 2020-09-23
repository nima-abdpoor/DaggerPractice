package com.chinachino.daggerpractice.UI.post;

import android.util.Log;

import androidx.lifecycle.ViewModel;

import com.chinachino.daggerpractice.SessionManager;
import com.chinachino.daggerpractice.network.main.MainAPI;

import javax.inject.Inject;


public class PostViewModel extends ViewModel {

    private static final String TAG = "PostViewModel";

    final SessionManager sessionManager;
    final MainAPI mainAPI;
    @Inject

    public PostViewModel(SessionManager sessionManager, MainAPI mainAPI) {
        this.sessionManager = sessionManager;
        this.mainAPI = mainAPI;
        Log.d(TAG, "PostViewModel: viewModel is working ... ");
    }
}
