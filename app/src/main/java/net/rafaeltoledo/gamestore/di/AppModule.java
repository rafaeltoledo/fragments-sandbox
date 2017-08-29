package net.rafaeltoledo.gamestore.di;

import net.rafaeltoledo.gamestore.data.api.FirebaseStoreApiImpl;
import net.rafaeltoledo.gamestore.data.api.StoreApi;

import dagger.Binds;
import dagger.Module;

@Module(includes = ViewModelModule.class)
public abstract class AppModule {

    @Binds
    public abstract StoreApi bindStoreApi(FirebaseStoreApiImpl impl);
}
