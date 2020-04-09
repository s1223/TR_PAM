package com.fianjulio.pam_tr;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;


public class firebase extends AppCompatActivity {
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        // Write a message to the database
        final FirebaseDatabase[] database = {FirebaseDatabase.getInstance()};
        DatabaseReference myRef = database[0].getReference("data");

        //myRef.setValue("Hello, World!");

        // Read from the database
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                singletone obj = singletone.getInstance();
                ArrayList<String> a = obj.getData_gambar();
                for (DataSnapshot ds : dataSnapshot.getChildren()){
                    String img = ds.child("img").getValue().toString();
                    a.add(img);
                    Log.d("firebases3",img);
                }
                obj.setData_gambar(a);
                if (obj.getData_gambar().size() == 40){
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

    public void tampilan(){
        setContentView(R.layout.activity_firebase);

        textView = findViewById(R.id.textView);
//        singletone a = singletone.getInstance();
//        String value = a.getS1();
        textView.setText("halo");
        singletone obj2 = singletone.getInstance();
        ArrayList<String> isi_gambar = obj2.getData_gambar();
        Log.d("jumlah",String.valueOf(isi_gambar.size()));

        for (String abc : isi_gambar) {
            Log.d("firebases4",abc);
        }
    }
}
