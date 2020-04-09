package com.fianjulio.pam_tr;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


public class firebase extends AppCompatActivity {

    String rImg="aa";
//    String rLat="aaa";
//    String rLong="aaa";
//    String rNam="aaa";
//    String rHp="aaa";
    String[] rLok = new String[4];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_firebase);
        data_gambar("9");
        data_lok("2");
    }

    public String data_gambar(String indexnya){
        // Write a message to the database
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("data/"+indexnya+"/img");

        //myRef.setValue("Hello, World!");

        // Read from the database
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                rImg = dataSnapshot.getValue(String.class);
                Log.d("firebases0", "Value is: " + rImg);

            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w("firebases0", "Failed to read value.", error.toException());
            }
        });
        return rImg;
    }

    public String[] data_lok(String indexnya){

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference RefLat = database.getReference("data/"+indexnya+"/latitude");
        DatabaseReference RefLong = database.getReference("data/"+indexnya+"/longitude");
        DatabaseReference Refnam = database.getReference("data/"+indexnya+"/namaWisata");
        DatabaseReference Refno = database.getReference("data/"+indexnya+"/noHp");
        //final String[] valued = new String[1];
        //myRef.setValue("Hello, World!");

        // Read from the database
        RefLat.addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                rLok[0] = dataSnapshot.getValue(String.class);
                Log.d("firebasess", "latitude is: " + rLok[0]);
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w("firebasess", "Failed to read value.", error.toException());
            }
        });

        RefLong.addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                rLok[1] = dataSnapshot.getValue(String.class);
                Log.d("firebasess", "longitude is: " + rLok[1]);
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w("firebasess", "Failed to read value.", error.toException());
            }
        });

        Refnam.addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                rLok[2] = dataSnapshot.getValue(String.class);
                Log.d("firebasess", "namawisata is: " + rLok[2]);
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w("firebasess", "Failed to read value.", error.toException());
            }
        });

        Refno.addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                rLok[3] = dataSnapshot.getValue(String.class);
                Log.d("firebasess", "nohp is: " + rLok[3]);
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w("firebasess", "Failed to read value.", error.toException());
            }
        });
        return rLok;
    }

}
