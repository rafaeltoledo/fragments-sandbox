package net.rafaeltoledo.gamestore.di;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;

import java.util.Map;

import javax.inject.Inject;
import javax.inject.Provider;
import javax.inject.Singleton;

@Singleton
public class AppViewModelFactory implements ViewModelProvider.Factory {

    private final Map<Class<? extends ViewModel>, Provider<ViewModel>> creators;

    @Inject
    AppViewModelFactory(Map<Class<? extends ViewModel>, Provider<ViewModel>> creators) {
        this.creators = creators;
    }

    @SuppressWarnings("unchecked")
    @Override
    public <T extends ViewModel> T create(Class<T> modelClass) {
        Provider<? extends ViewModel> creator = creators.get(modelClass);

        if (creator == null) {
            for (Class<? extends ViewModel> key : creators.keySet()) {
                if (modelClass.isAssignableFrom(key)) {
                    creator = creators.get(key);
                    break;
                }
            }
        }

        if (creator == null) {
            throw new IllegalArgumentException("Unknown model class " + modelClass);
        }

        return (T) creator.get();
    }
}
