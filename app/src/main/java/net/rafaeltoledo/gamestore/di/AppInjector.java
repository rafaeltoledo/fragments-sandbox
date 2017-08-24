package net.rafaeltoledo.gamestore.di;

import net.rafaeltoledo.gamestore.StoreApp;

public class AppInjector {

    public static void init(AppComponent component, StoreApp app) {
        component.inject(app);
    }
}
