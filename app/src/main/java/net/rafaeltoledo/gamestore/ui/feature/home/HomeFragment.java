package net.rafaeltoledo.gamestore.ui.feature.home;

import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import net.rafaeltoledo.gamestore.databinding.FragmentHomeBinding;
import net.rafaeltoledo.gamestore.ui.BaseFragment;
import net.rafaeltoledo.gamestore.ui.BaseViewModel;
import net.rafaeltoledo.gamestore.ui.model.Banner;

import java.util.List;

import javax.inject.Inject;

public class HomeFragment extends BaseFragment {

    @Inject
    ViewModelProvider.Factory viewModelFactory;

    private HomeViewModel viewModel;

    private FragmentHomeBinding binding;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup root = (ViewGroup) super.onCreateView(inflater, container, savedInstanceState);
        binding = FragmentHomeBinding.inflate(inflater, root, false);
        return wrapView(binding);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        viewModel = ViewModelProviders.of(getActivity(), viewModelFactory).get(HomeViewModel.class);

        viewModel.banners.observe(this, this::setupAdapter);
        setupErrorHandler();

        performRequest();
    }

    private void setupAdapter(List<Banner> banners) {
        binding.banners.setAdapter(new BannerAdapter(banners));
    }

    @Override
    protected BaseViewModel getBaseViewModel() {
        return viewModel;
    }

    @Override
    protected void performRequest() {
        viewModel.fetchBanners();
    }
}
