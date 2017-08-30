package net.rafaeltoledo.gamestore.ui;

import android.arch.lifecycle.LifecycleFragment;

import net.rafaeltoledo.gamestore.di.Injectable;

public abstract class BaseFragment extends LifecycleFragment implements Injectable {

    protected abstract BaseViewModel getBaseViewModel();
}
