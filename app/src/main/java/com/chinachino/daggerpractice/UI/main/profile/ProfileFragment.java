package com.chinachino.daggerpractice.UI.main.profile;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

import com.chinachino.daggerpractice.R;
import com.chinachino.daggerpractice.ViewModels.ViewModelProviderFactory;

import javax.inject.Inject;

import dagger.android.support.DaggerFragment;

public class ProfileFragment extends DaggerFragment {

    @Inject
    ProfileViewModel viewModel;

    @Inject
    ViewModelProviderFactory providerFactory;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Toast.makeText(getActivity(), "profile fragment", Toast.LENGTH_SHORT).show();
        return inflater.inflate(R.layout.fragment_profile,container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
       viewModel = ViewModelProviders.of(this,providerFactory).get(ProfileViewModel.class);

    }
}
