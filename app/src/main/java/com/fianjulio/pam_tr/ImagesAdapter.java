package com.fianjulio.pam_tr;

import com.squareup.picasso.Picasso;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

public class ImagesAdapter extends BaseAdapter{
    private Context mContext;
    int imageTotal = 7;
    public static String[] mThumbIds = {
            "http://192.168.31.66/GambarTRPAM/1.jpg",
            "http://192.168.31.66/GambarTRPAM/2.jpg",
            "http://192.168.31.66/GambarTRPAM/3.jpg",
            "http://192.168.31.66/GambarTRPAM/4.jpg",
            "http://192.168.31.66/GambarTRPAM/5.jpg",
            "http://192.168.31.66/GambarTRPAM/6.jpg",
            "http://192.168.31.66/GambarTRPAM/7.jpg",
    };

    public ImagesAdapter(Context c) {
        mContext = c;
    }

    @Override
    public int getCount() {
        return imageTotal;
    }

    @Override
    public Object getItem(int position) {
        return mThumbIds[position];
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ImageView imageView;
        if (convertView == null) {
            imageView = new ImageView(mContext);
            imageView.setLayoutParams(new GridView.LayoutParams(480, 480));
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            imageView.setPadding(8, 8, 8, 8);
        } else {
            imageView = (ImageView) convertView;
        }
        String url = (String) getItem(position);
        Picasso.with(mContext)
                .load(url)
                .placeholder(R.drawable.loader)
                .fit()
                .centerCrop().into(imageView);
        return imageView;
    }
}

