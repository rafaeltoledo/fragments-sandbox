package net.rafaeltoledo.gamestore.ui;

import android.arch.lifecycle.LifecycleFragment;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import net.rafaeltoledo.gamestore.R;
import net.rafaeltoledo.gamestore.databinding.TemplateScreenBinding;
import net.rafaeltoledo.gamestore.di.Injectable;

public abstract class BaseFragment extends LifecycleFragment implements Injectable {

    private static final int VIEW_LOADING = 0;
    private static final int VIEW_ERROR = 1;
    private static final int VIEW_CONTENT = 2;

    protected abstract BaseViewModel getBaseViewModel();
    protected abstract void performRequest();

    private TemplateScreenBinding template;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        template = TemplateScreenBinding.inflate(inflater, container, false);
        template.error.buttonRetry.setOnClickListener(v -> performRequest());
        return template.getRoot();
    }

    @Override
    public void onDestroyView() {
        template = null;
        super.onDestroyView();
    }

    protected ViewGroup getRoot() {
        return template.getRoot().findViewById(R.id.content);
    }

    protected View wrapView(ViewDataBinding binding) {
        ViewGroup root = (ViewGroup) template.getRoot();
        root.addView(binding.getRoot());
        return root;
    }

    protected void setupErrorHandler() {
        getBaseViewModel().loading.observe(this, loading -> {
            if (Boolean.TRUE.equals(loading)) {
                template.content.setDisplayedChild(VIEW_LOADING);
            } else {
                template.content.setDisplayedChild(VIEW_CONTENT);
            }
        });

        getBaseViewModel().error.observe(this, error -> {
            template.setRetriableError(true);
            template.content.setDisplayedChild(VIEW_ERROR);
        });

        getBaseViewModel().retriableError.observe(this, error -> {
            template.setRetriableError(false);
            template.content.setDisplayedChild(VIEW_ERROR);
        });
    }
}
