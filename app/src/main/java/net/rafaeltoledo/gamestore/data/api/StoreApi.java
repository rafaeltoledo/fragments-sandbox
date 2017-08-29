package net.rafaeltoledo.gamestore.data.api;

import net.rafaeltoledo.gamestore.data.model.Banner;

import java.util.List;

import io.reactivex.Single;

public interface StoreApi {

    Single<List<Banner>> fetchBanners();
}
