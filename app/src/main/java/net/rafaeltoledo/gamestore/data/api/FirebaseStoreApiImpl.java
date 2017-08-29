package net.rafaeltoledo.gamestore.data.api;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.GenericTypeIndicator;
import com.google.firebase.database.ValueEventListener;

import net.rafaeltoledo.gamestore.data.model.Banner;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Single;

@Singleton
public class FirebaseStoreApiImpl implements StoreApi {

    private final FirebaseDatabase database;

    @Inject
    FirebaseStoreApiImpl() {
        this.database = FirebaseDatabase.getInstance();
    }

    @Override
    public Single<List<Banner>> fetchBanners() {
        return Single.create(emitter -> database.getReference("banners").addValueEventListener(
                new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        emitter.onSuccess(dataSnapshot.getValue(new GenericTypeIndicator<>()));
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                        emitter.onError(databaseError.toException());
                    }
                }));
    }
}
