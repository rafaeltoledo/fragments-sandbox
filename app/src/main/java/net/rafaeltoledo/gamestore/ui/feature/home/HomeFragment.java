package net.rafaeltoledo.gamestore.ui.feature.home;

import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;

import net.rafaeltoledo.gamestore.data.model.Banner;
import net.rafaeltoledo.gamestore.ui.BaseFragment;
import net.rafaeltoledo.gamestore.ui.BaseViewModel;

import java.util.List;

import javax.inject.Inject;

import timber.log.Timber;

public class HomeFragment extends BaseFragment {

    @Inject
    ViewModelProvider.Factory viewModelFactory;

    private HomeViewModel viewModel;

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        viewModel = ViewModelProviders.of(getActivity(), viewModelFactory)
                .get(HomeViewModel.class);

        viewModel.fetchBanners();

        viewModel.banners.observe(this, this::setupAdapter);

        getBaseViewModel().error.observe(this, error -> {
            Timber.tag("Error").e(String.valueOf(error));
        });

        getBaseViewModel().retriableError.observe(this, error -> {
            Timber.tag("Retriable Error").e(String.valueOf(error));
        });
    }

    private void setupAdapter(List<Banner> banners) {
        for (Banner banner : banners) {
            Timber.d(banner.getTitle());
            Timber.d(banner.getImage());
        }
    }

    @Override
    protected BaseViewModel getBaseViewModel() {
        return viewModel;
    }
}
