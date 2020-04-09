package com.fianjulio.pam_tr;

import androidx.core.app.ActivityCompat;
import androidx.fragment.app.FragmentActivity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
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
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

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
        get_lat_from_FB();
    }

    public void tampilan(){
        setContentView(R.layout.activity_lokasi_wisata);
        Bcall = findViewById(R.id.btn_telp);
        BGMaps = findViewById(R.id.btn_nav);
        txtNamaLokasi = findViewById(R.id.NamaLokasi);
        Intent a = getIntent();
        int id = a.getExtras().getInt("id");
        singletone obj = singletone.getInstance();
        lat = obj.getLat().get(id);
        longi = obj.getLongi().get(id);
        NamaTempat = obj.getNamaTempat().get(id);
        NoHP = obj.getNoHP().get(id);

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

    public void get_lat_from_FB(){
        // Write a message to the database
        final FirebaseDatabase[] database = {FirebaseDatabase.getInstance()};
        DatabaseReference myRef = database[0].getReference("data");

        //myRef.setValue("Hello, World!");

        // Read from the database
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                singletone obj = singletone.getInstance();
                ArrayList<String> lat = obj.getLat();
                ArrayList<String> longi = obj.getLongi();
                ArrayList<String> NamaTempat = obj.getNamaTempat();
                ArrayList<String> NoHP = obj.getNoHP();
                int ssss = 1;
                for (DataSnapshot ds : dataSnapshot.getChildren()){
                    String latitude = ds.child("latitude").getValue().toString();
                    String longitude = ds.child("longitude").getValue().toString();
                    String namaWisata = ds.child("namaWisata").getValue().toString();
                    String noHp = ds.child("noHp").getValue().toString();
                    if (!lat.contains(latitude)){
                        lat.add(latitude);
                    }else {
                        Log.d("status","latitude data ke "+ssss+" ada yang double");
                    }
                    if (!longi.contains(longitude)){
                        longi.add(longitude);
                    }else {
                        Log.d("status","longitude data ke "+ssss+" ada yang double");
                    }
                    if (!NamaTempat.contains(namaWisata)){
                        NamaTempat.add(namaWisata);
                    }else {
                        Log.d("status","namaWisata data ke "+ssss+" ada yang double");
                    }
                    if (!NoHP.contains(noHp)){
                        NoHP.add(noHp);
                    }else{
                        Log.d("status","NO HP data ke "+ssss+" ada yang double");
                    }
                    Log.d("status","masih ambil data ke "+ssss);
                    ssss += 1;
                }
                Log.d("status","dah selesai ambil data");
                obj.setLat(lat);
                obj.setLongi(longi);
                obj.setNamaTempat(NamaTempat);
                obj.setNoHP(NoHP);
                Log.d("status","obj.getNoHP().size() = "+String.valueOf(obj.getNoHP().size()));
                if (obj.getNoHP().size() == 40){
                    Log.d("status","menampilkan tampilan");
                    tampilan();
                }
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w("firebases0", "Failed to read value.", error.toException());
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
