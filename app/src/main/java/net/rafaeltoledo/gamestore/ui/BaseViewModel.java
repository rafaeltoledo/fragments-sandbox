package net.rafaeltoledo.gamestore.ui;

import android.arch.lifecycle.ViewModel;

import io.reactivex.disposables.CompositeDisposable;

public abstract class BaseViewModel extends ViewModel {

    protected CompositeDisposable disposables = new CompositeDisposable();

    @Override
    protected void onCleared() {
        disposables.clear();
        super.onCleared();
    }
}
