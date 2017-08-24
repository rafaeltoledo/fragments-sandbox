package net.rafaeltoledo.gamestore.di;

import net.rafaeltoledo.gamestore.StoreApp;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;
import dagger.android.AndroidInjectionModule;

@Singleton
@Component(modules = {
        AndroidInjectionModule.class
})
public interface AppComponent {

    void inject(StoreApp app);

    @Component.Builder
    interface Builder {

        AppComponent build();

        @BindsInstance
        Builder application(StoreApp app);
    }
}
