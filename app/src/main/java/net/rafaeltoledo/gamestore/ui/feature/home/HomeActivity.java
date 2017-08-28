package net.rafaeltoledo.gamestore.ui.feature.home;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;

import net.rafaeltoledo.gamestore.R;
import net.rafaeltoledo.gamestore.databinding.ActivityHomeBinding;
import net.rafaeltoledo.gamestore.ui.BaseActivity;

public class HomeActivity extends BaseActivity {

    private ActivityHomeBinding binding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_home);
    }
}
