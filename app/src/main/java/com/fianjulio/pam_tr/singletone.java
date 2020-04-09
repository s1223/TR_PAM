package com.fianjulio.pam_tr;

import android.util.Log;

import java.util.ArrayList;

public class singletone {
    private static singletone a = null;
    ArrayList <String> data_gambar;
    private singletone(){
        data_gambar = new ArrayList<>();
    }

    public static singletone getInstance(){
        if (a == null) {
            a = new singletone();
        }
        return a;
    }

    public ArrayList<String> getData_gambar() {
        Log.d("jumlah_get",String.valueOf(data_gambar.size()));
        return data_gambar;
    }

    public void setData_gambar(ArrayList<String> data_gambar) {
        this.data_gambar = data_gambar;
        Log.d("jumlah_set",String.valueOf(data_gambar.size()));
    }
}
