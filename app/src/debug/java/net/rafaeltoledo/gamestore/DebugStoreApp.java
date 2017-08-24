package net.rafaeltoledo.gamestore;

import timber.log.Timber;

public class DebugStoreApp extends StoreApp {

    @Override
    public void onCreate() {
        super.onCreate();
        Timber.plant(new Timber.DebugTree());
    }
}
