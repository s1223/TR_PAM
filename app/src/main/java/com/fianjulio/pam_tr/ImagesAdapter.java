package com.fianjulio.pam_tr;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

public class ImagesAdapter<i> extends BaseAdapter{
    private Context mContext;
    int imageTotal = 40;


    firebase fb = new firebase();

    String[] mThumbIds = new String[40];

    public void gbr(){
        for(int i = 0; i< 40;i++){
            mThumbIds[i] = fb.data_gambar(String.valueOf(i+1));
        }
        Log.d("tesimg", "Value is: " + mThumbIds[2]);
        Log.d("tesimg", "imgadapter");
    }



    public ImagesAdapter(Context c) {
        mContext = c;
    }

    @Override
    public int getCount() {
        return imageTotal;
    }

    @Override
    public Object getItem(int position) {
        gbr();
        Log.d("tesk", "tesk");
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

