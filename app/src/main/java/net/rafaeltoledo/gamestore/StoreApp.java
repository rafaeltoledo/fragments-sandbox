package net.rafaeltoledo.gamestore;

import android.app.Activity;
import android.app.Application;

import com.jakewharton.threetenabp.AndroidThreeTen;

import net.rafaeltoledo.gamestore.di.AppComponent;
import net.rafaeltoledo.gamestore.di.AppInjector;
import net.rafaeltoledo.gamestore.di.DaggerAppComponent;

import java.io.IOException;

import javax.inject.Inject;

import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.HasActivityInjector;
import io.reactivex.exceptions.UndeliverableException;
import io.reactivex.plugins.RxJavaPlugins;
import timber.log.Timber;

public class StoreApp extends Application implements HasActivityInjector {

    @Inject
    DispatchingAndroidInjector<Activity> activityInjector;

    @Override
    public void onCreate() {
        super.onCreate();
        AndroidThreeTen.init(this);

        prepareRxJavaErrorHandler();

        AppInjector.init(createAppComponent(), this);
    }

    protected AppComponent createAppComponent() {
        return DaggerAppComponent.builder()
                .application(this)
                .build();
    }

    @Override
    public AndroidInjector<Activity> activityInjector() {
        return activityInjector;
    }

    private void prepareRxJavaErrorHandler() {
        RxJavaPlugins.setErrorHandler(e -> {
            if (e instanceof UndeliverableException) {
                e = e.getCause();
            }

            if (e instanceof IOException || e instanceof InterruptedException) {
                return;
            }

            if (e instanceof NullPointerException || e instanceof IllegalArgumentException || e instanceof IllegalStateException) {
                Thread.currentThread()
                        .getUncaughtExceptionHandler()
                        .uncaughtException(Thread.currentThread(), e);
                return;
            }

            Timber.w(e, "Undeliverable exception received, not sure what to do.");
        });
    }
}
