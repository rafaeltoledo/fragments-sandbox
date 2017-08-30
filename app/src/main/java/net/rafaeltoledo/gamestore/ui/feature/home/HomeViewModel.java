package net.rafaeltoledo.gamestore.ui.feature.home;

import android.arch.lifecycle.MutableLiveData;

import net.rafaeltoledo.gamestore.data.api.StoreApi;
import net.rafaeltoledo.gamestore.ui.BaseViewModel;
import net.rafaeltoledo.gamestore.ui.model.Banner;

import java.util.List;

import javax.inject.Inject;

public class HomeViewModel extends BaseViewModel {

    public MutableLiveData<List<Banner>> banners = new MutableLiveData<>();

    @Inject
    HomeViewModel(StoreApi api) {
        super(api);
    }

    public void fetchBanners() {
        loading.postValue(Boolean.TRUE);
        disposables.add(api.fetchBanners()
                .doOnSuccess(ignored -> loading.postValue(Boolean.FALSE))
                .subscribe(banners -> this.banners.postValue(banners), errorHandler));
    }
}
