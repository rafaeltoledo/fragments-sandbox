package net.rafaeltoledo.gamestore.di;

import net.rafaeltoledo.gamestore.ui.feature.home.HomeFragment;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class FragmentsModule {

    @ContributesAndroidInjector
    public abstract HomeFragment contributeHomeFragment();
}
