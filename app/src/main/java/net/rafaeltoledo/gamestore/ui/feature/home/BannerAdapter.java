package net.rafaeltoledo.gamestore.ui.feature.home;

import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import net.rafaeltoledo.gamestore.R;
import net.rafaeltoledo.gamestore.ui.model.Banner;

import java.util.List;

public class BannerAdapter extends PagerAdapter {

    private final List<Banner> banners;

    BannerAdapter(List<Banner> banners) {
        this.banners = banners;
    }

    @Override
    public Object instantiateItem(ViewGroup collection, int position) {
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
    public void destroyItem(ViewGroup collection, int position, Object view) {
        collection.removeView((View) view);
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }
}
