package net.rafaeltoledo.gamestore.ui.feature.home;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import net.rafaeltoledo.gamestore.R;
import net.rafaeltoledo.gamestore.ui.model.Banner;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

public class BannerAdapter extends PagerAdapter {

    private final List<Banner> banners;

    BannerAdapter(List<Banner> banners) {
        this.banners = banners;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup collection, int position) {
        LayoutInflater inflater = LayoutInflater.from(collection.getContext());
        ImageView banner = (ImageView) inflater.inflate(R.layout.item_banner, collection, false);
        Glide.with(collection.getContext()).load(banners.get(position).getImage()).into(banner);
        collection.addView(banner);
        return banner;
    }

    @Override
    public int getCount() {
        return banners.size();
    }

    @Override
    public void destroyItem(@NonNull ViewGroup collection, int position, @NonNull Object view) {
        collection.removeView((View) view);
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }
}
