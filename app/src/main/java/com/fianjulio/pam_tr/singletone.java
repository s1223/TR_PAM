package com.fianjulio.pam_tr;

import android.util.Log;

import java.util.ArrayList;

public class singletone {
    private static singletone a = null;
    ArrayList <String> data_gambar;
    ArrayList <String> lat;
    ArrayList <String> longi;
    ArrayList <String> NamaTempat;
    ArrayList <String> NoHP;
    private singletone(){
        data_gambar = new ArrayList<>();
        lat = new ArrayList<>();
        longi = new ArrayList<>();
        NamaTempat = new ArrayList<>();
        NoHP = new ArrayList<>();
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

    public ArrayList<String> getLat() {
        return lat;
    }

    public void setLat(ArrayList<String> lat) {
        this.lat = lat;
    }

    public ArrayList<String> getLongi() {
        return longi;
    }

    public void setLongi(ArrayList<String> longi) {
        this.longi = longi;
    }

    public ArrayList<String> getNamaTempat() {
        return NamaTempat;
    }

    public void setNamaTempat(ArrayList<String> namaTempat) {
        NamaTempat = namaTempat;
    }

    public ArrayList<String> getNoHP() {
        return NoHP;
    }

    public void setNoHP(ArrayList<String> noHP) {
        NoHP = noHP;
    }
}
