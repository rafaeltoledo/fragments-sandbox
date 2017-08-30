package net.rafaeltoledo.gamestore.util;

import android.support.annotation.Nullable;

import com.bumptech.glide.Priority;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.Key;
import com.bumptech.glide.load.Options;
import com.bumptech.glide.load.data.DataFetcher;
import com.bumptech.glide.load.model.ModelLoader;
import com.bumptech.glide.load.model.ModelLoaderFactory;
import com.bumptech.glide.load.model.MultiModelLoaderFactory;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.StreamDownloadTask;

import java.io.IOException;
import java.io.InputStream;
import java.security.MessageDigest;

import timber.log.Timber;

// Adapted from Firebase-Storage-UI
public class FirebaseImageLoader implements ModelLoader<StorageReference, InputStream> {

    private static final String TAG = "FirebaseImageLoader";


    /**
     * Factory to create {@link FirebaseImageLoader}.
     */
    public static class Factory implements ModelLoaderFactory<StorageReference, InputStream> {

        @Override
        public ModelLoader<StorageReference, InputStream> build(MultiModelLoaderFactory factory) {
            return new FirebaseImageLoader();
        }

        @Override
        public void teardown() {
            // No-op
        }
    }

    @Nullable
    @Override
    public LoadData<InputStream> buildLoadData(StorageReference reference,
                                               int height,
                                               int width,
                                               Options options) {
        return new LoadData<>(
                new FirebaseStorageKey(reference),
                new FirebaseStorageFetcher(reference));
    }

    @Override
    public boolean handles(StorageReference reference) {
        return true;
    }

    private class FirebaseStorageKey implements Key {

        private StorageReference mRef;

        public FirebaseStorageKey(StorageReference ref) {
            mRef = ref;
        }

        @Override
        public void updateDiskCacheKey(MessageDigest digest) {
            digest.update(mRef.getPath().getBytes());
        }
    }

    private class FirebaseStorageFetcher implements DataFetcher<InputStream> {

        private StorageReference mRef;
        private StreamDownloadTask mStreamTask;
        private InputStream mInputStream;

        public FirebaseStorageFetcher(StorageReference ref) {
            mRef = ref;
        }

        @Override
        public void loadData(Priority priority,
                             final DataCallback<? super InputStream> callback) {
            mStreamTask = mRef.getStream();
            mStreamTask.addOnSuccessListener(snapshot -> {
                mInputStream = snapshot.getStream();
                callback.onDataReady(mInputStream);
            }).addOnFailureListener(callback::onLoadFailed);
        }

        @Override
        public void cleanup() {
            // Close stream if possible
            if (mInputStream != null) {
                try {
                    mInputStream.close();
                    mInputStream = null;
                } catch (IOException e) {
                    Timber.tag(TAG).w(e, "Could not close stream");
                }
            }
        }

        @Override
        public void cancel() {
            // Cancel task if possible
            if (mStreamTask != null && mStreamTask.isInProgress()) {
                mStreamTask.cancel();
            }
        }

        @Override
        public Class<InputStream> getDataClass() {
            return InputStream.class;
        }

        @Override
        public DataSource getDataSource() {
            return DataSource.REMOTE;
        }
    }
}