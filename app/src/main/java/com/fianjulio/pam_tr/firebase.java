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





    }

    public void get_images_FB(){

    }

//    public void tampilan(){
//        setContentView(R.layout.activity_firebase);
//
//        textView = findViewById(R.id.textView);
////        singletone a = singletone.getInstance();
////        String value = a.getS1();
//        textView.setText("halo");
//        singletone obj2 = singletone.getInstance();
//        ArrayList<String> isi_gambar = obj2.getData_gambar();
//        Log.d("jumlah",String.valueOf(isi_gambar.size()));
//
//        for (String abc : isi_gambar) {
//            Log.d("firebases4",abc);
//        }
//    }
}
