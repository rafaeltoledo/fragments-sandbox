package net.rafaeltoledo.gamestore.ui;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import net.rafaeltoledo.gamestore.data.api.StoreApi;

import java.io.IOException;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;

public abstract class BaseViewModel extends ViewModel {

    protected final StoreApi api;

    public final MutableLiveData<Boolean> error = new MutableLiveData<>();
    public final MutableLiveData<Boolean> retriableError = new MutableLiveData<>();
    public final MutableLiveData<Boolean> loading = new MutableLiveData<>();

    public BaseViewModel(StoreApi api) {
        this.api = api;
        error.setValue(false);
        retriableError.setValue(false);
        loading.setValue(false);
    }

    protected CompositeDisposable disposables = new CompositeDisposable();

    protected Consumer<Throwable> errorHandler = throwable -> {
        if (throwable instanceof IOException) {
            retriableError.postValue(true);
        } else {
            error.postValue(true);
        }
    };

    @Override
    protected void onCleared() {
        disposables.clear();
        super.onCleared();
    }
}
