package com.fianjulio.pam_tr;

import com.squareup.picasso.Picasso;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class ImagesAdapter extends BaseAdapter{
    private Context mContext;
    singletone obj = singletone.getInstance();
    ArrayList<String> mThumbIds = obj.getData_gambar();
    int imageTotal = mThumbIds.size();
//    int imageTotal = 7;
//    public static String[] mThumbIds = {
//            "https://png.clipart.me/istock/previews/3895/38953970-france-national-flag-square-button-isolated-on-white-background.jpg",
//            "https://images.freeimages.com/images/premium/large-thumbs/4616/46163316-denmark-flag-square-glossy-button.jpg",
//            "https://fastly.4sqi.net/img/general/200x200/35684184_UbuzxuKzsCzDRzDNseU1HPc3VlFyrS4AJC9cZtSkxJg.jpg",
//            "https://images.freeimages.com/images/premium/large-thumbs/4621/46210652-namibia-flag-square-glossy-button.jpg",
//            "https://png.clipart.me/istock/previews/4620/46206954-malaysia-flag-square-glossy-button.jpg",
//            "https://fastly.4sqi.net/img/general/200x200/6534561_UZ6fkjMYQXUFf9ZFdAZyl_vq6dh4XTfHigkYRr-rM_k.jpg",
//            "https://ecs7.tokopedia.net/img/cache/200-square/product-1/2015/8/24/74044/74044_d2370465-56df-41cc-9f1b-7a963f179522.jpg",
//    };


    public ImagesAdapter(Context c) {
        mContext = c;
    }

    @Override
    public int getCount() {
        return imageTotal;
    }

    @Override
    public Object getItem(int position) {
        return mThumbIds.get(position);
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

