package net.rafaeltoledo.gamestore.di;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;

import net.rafaeltoledo.gamestore.ui.feature.home.HomeViewModel;

import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;

@Module
public abstract class ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(HomeViewModel.class)
    public abstract ViewModel bindHomeViewModel(HomeViewModel viewModel);

    @Binds
    public abstract ViewModelProvider.Factory bindViewModelFactory(AppViewModelFactory factory);
}
