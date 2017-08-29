package net.rafaeltoledo.gamestore.ui.feature.home;

import android.arch.lifecycle.MutableLiveData;

import net.rafaeltoledo.gamestore.data.api.StoreApi;
import net.rafaeltoledo.gamestore.data.model.Banner;
import net.rafaeltoledo.gamestore.ui.BaseViewModel;

import java.util.List;

import javax.inject.Inject;

public class HomeViewModel extends BaseViewModel {

    public MutableLiveData<List<Banner>> banners = new MutableLiveData<>();

    @Inject
    HomeViewModel(StoreApi api) {
        super(api);
    }

    public void fetchBanners() {
        disposables.add(api.fetchBanners().subscribe(banners -> this.banners.postValue(banners), errorHandler));
    }
}
