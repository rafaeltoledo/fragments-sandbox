package net.rafaeltoledo.gamestore.di;

import net.rafaeltoledo.gamestore.ui.feature.main.MainActivity;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module(includes = FragmentsModule.class)
public abstract class ActivitiesModule {

    @ContributesAndroidInjector
    public abstract MainActivity provideMainActivity();
}
