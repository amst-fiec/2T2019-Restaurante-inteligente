package com.example.restaurante_inteligente;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EstadoBateria extends AppCompatActivity {
    DatabaseReference db_reference;
    private LinearLayout contenedor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_estado_bateria);
        iniciarBaseDeDatos();
        consultarBaterias();
    }

    public void iniciarBaseDeDatos(){
        db_reference = FirebaseDatabase.getInstance().getReference();
    }

    public void consultarBaterias(){
        DatabaseReference mesas = db_reference.child("Grupo").child("Mesa");
        final Context that = this;
        mesas.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                contenedor = findViewById(R.id.contenedor_bateria);

                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    HashMap<String, Object> mesa = (HashMap<String, Object>) snapshot.getValue();
                    List<Object> l = (List<Object>) mesa.get("Sillas");
                    for (Object entry : l) {
                        if (entry != null) {
                            HashMap<String, Object> silla = (HashMap<String, Object>) entry;
                            String nombre = snapshot.getKey();
                            String dispositivo = (String) silla.get("Dispositivo");
                            String bateria = (String) silla.get("Bateria");
                            TextView x = new TextView(that);
                            String nombre_final = String.format("Mesa: %s, Dispositivo: %s \nBateria: %s", nombre, dispositivo, bateria);
                            x.setText(nombre_final);
                            x.callOnClick();
                            contenedor.addView(x);
                        }
                    }
                }
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });
    }

}
