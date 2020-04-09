package com.fianjulio.pam_tr;

import com.google.firebase.database.IgnoreExtraProperties;

@IgnoreExtraProperties
class Data {

    private String id;
    private String img;
    private String latitude;
    private String longitude;
    private String namaWisata;
    private String noHp;

    public Data() {
    }

    public Data(String id, String img, String latitude, String longitude, String namaWisata, String noHp) {
        this.id = id;
        this.img = img;
        this.latitude = latitude;
        this.longitude = longitude;
        this.namaWisata = namaWisata;
        this.noHp = noHp;
    }

    public String getId() {
        return id;
    }

    public String getImg() {
        return img;
    }

    public String getLatitude() {
        return latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public String getNamaWisata() {
        return namaWisata;
    }

    public String getNoHp() {
        return noHp;
    }
}
