package net.rafaeltoledo.gamestore.data.api;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.GenericTypeIndicator;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import net.rafaeltoledo.gamestore.data.model.ApiBanner;
import net.rafaeltoledo.gamestore.ui.model.Banner;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Single;
import timber.log.Timber;

@Singleton
public class FirebaseStoreApiImpl implements StoreApi {

    private final FirebaseDatabase database;
    private final FirebaseStorage storage;

    @Inject
    FirebaseStoreApiImpl() {
        this.storage = FirebaseStorage.getInstance();
        this.database = FirebaseDatabase.getInstance();
    }

    @Override
    public Single<List<Banner>> fetchBanners() {
        return Single.create(emitter -> database.getReference("banners").addValueEventListener(
                new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        List<ApiBanner> banners = dataSnapshot.getValue(new GenericTypeIndicator<List<ApiBanner>>() { });
                        if (banners != null) {
                            List<Banner> result = new ArrayList<>(banners.size());
                            for (ApiBanner banner : banners) {
                                StorageReference storageRef = storage.getReference().child(banner.getImage());
                                result.add(new Banner(storageRef, banner.getTitle()));

                            }
                            emitter.onSuccess(result);
                        } else {
                            emitter.onError(new NullPointerException());
                        }
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                        Timber.d(databaseError.toException());
                        emitter.onError(databaseError.toException());
                    }
                }));
    }
}
