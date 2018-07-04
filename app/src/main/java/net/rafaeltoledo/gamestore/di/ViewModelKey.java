package net.rafaeltoledo.gamestore.di;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import androidx.lifecycle.ViewModel;
import dagger.MapKey;

@MapKey
@Retention(RetentionPolicy.RUNTIME)
public @interface ViewModelKey {

    Class<? extends ViewModel> value();
}
