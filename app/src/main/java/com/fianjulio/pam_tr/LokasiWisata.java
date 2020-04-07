package com.fianjulio.pam_tr;

import androidx.core.app.ActivityCompat;
import androidx.fragment.app.FragmentActivity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class LokasiWisata extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private Button Bcall, BGMaps;
    private TextView txtNamaLokasi;
    private String lat = "";
    private String longi = "";
    private String NamaTempat = "";
    private String NoHP = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lokasi_wisata);
        Bcall = findViewById(R.id.btn_telp);
        BGMaps = findViewById(R.id.btn_nav);
        txtNamaLokasi = findViewById(R.id.NamaLokasi);
        Intent a = getIntent();
        int id = a.getExtras().getInt("id");
        switch (id){
            case 0:
                lat = "-7.3219544";
                longi = "110.4937747";
                NamaTempat = "Warung Joglo Bu Rini";
                NoHP = "082136815488";
                break;
            case 1:
                lat = "-7.3264105";
                longi = "110.5006747";
                NamaTempat = "Selasar Kartini";
                NoHP = "082136815488";
                break;
        }

        txtNamaLokasi.setText(NamaTempat);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        BGMaps.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri gmmIntentUri = Uri.parse("google.navigation:q="+lat+","+longi);
                Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
                mapIntent.setPackage("com.google.android.apps.maps");
                startActivity(mapIntent);
            }
        });

        Bcall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent callIntent = new Intent(Intent.ACTION_CALL);
                callIntent.setData(Uri.parse("tel:"+NoHP));
                if (ActivityCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(getApplicationContext(),"Ijin ditolak sehingga tidak bisa melakukan panggilan.",Toast.LENGTH_LONG).show();
                    return;
                }
                startActivity(callIntent);
            }
        });
    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Sydney and move the camera
        LatLng lokasi = new LatLng(Double.valueOf(lat), Double.valueOf(longi));
        mMap.addMarker(new MarkerOptions().position(lokasi).title(NamaTempat));
        int zoomlevel = 16;
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(lokasi,zoomlevel));
    }
}
