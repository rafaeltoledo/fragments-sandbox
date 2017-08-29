package net.rafaeltoledo.gamestore.ui.feature.main;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

import net.rafaeltoledo.gamestore.R;
import net.rafaeltoledo.gamestore.databinding.ActivityMainBinding;
import net.rafaeltoledo.gamestore.ui.BaseActivity;
import net.rafaeltoledo.gamestore.ui.feature.home.HomeFragment;

import javax.inject.Inject;

import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.support.HasSupportFragmentInjector;

public class MainActivity extends BaseActivity implements HasSupportFragmentInjector {

    @Inject
    DispatchingAndroidInjector<Fragment> dispatchingAndroidInjector;

    private ActivityMainBinding binding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        if (savedInstanceState == null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .add(R.id.content, new HomeFragment())
                    .commit();
        }
    }

    @Override
    public AndroidInjector<Fragment> supportFragmentInjector() {
        return dispatchingAndroidInjector;
    }
}
