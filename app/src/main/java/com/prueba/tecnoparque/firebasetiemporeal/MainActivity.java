package com.prueba.tecnoparque.firebasetiemporeal;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {


    TextView lblCielo, lblTemperatura,lblHumedad;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        lblCielo = (TextView)findViewById(R.id.lblCielo);
        lblTemperatura = (TextView)findViewById(R.id.lblTemperatura);
        lblHumedad = (TextView)findViewById(R.id.lblHumedad);

        DatabaseReference dbCielo =
                FirebaseDatabase.getInstance().getReference()
                        .child("prediccion-hoy")
                        .child("cielo");

        dbCielo.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String valor = dataSnapshot.getValue().toString();
                lblCielo.setText(valor);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.e("Sadainer", "Error!", databaseError.toException());
            }
        });

    }
}
