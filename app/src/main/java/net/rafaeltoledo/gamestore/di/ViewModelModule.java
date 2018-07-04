package net.rafaeltoledo.gamestore.di;

import net.rafaeltoledo.gamestore.ui.feature.home.HomeViewModel;

import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
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
