package com.example.restaurante_inteligente;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;

public class EstadoMesa extends AppCompatActivity {
    DatabaseReference db_reference;
    private LinearLayout contenedor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_estado_mesa);
        iniciarBaseDeDatos();
        consultarMesas();
    }

    public void iniciarBaseDeDatos(){
        db_reference = FirebaseDatabase.getInstance().getReference();
    }

    public void consultarMesas(){
        DatabaseReference mesas = db_reference.child("Grupo").child("Mesa");
        final Context that = this;
        mesas.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                contenedor = findViewById(R.id.contenedor);

                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    HashMap<String, Object> mesa = (HashMap<String, Object>) snapshot.getValue();
                    String nombre = snapshot.getKey();
                    String disponible = "Ocupada";
                    if ((boolean)mesa.get("Disponible")) {
                        disponible = "Disponible";
                    }
                    TextView x = new TextView(that);
                    String nombre_final = String.format("Mesa: %s, Estado: %s", nombre, disponible);
                    SpannableString redSpannable = new SpannableString(nombre_final);
                    SpannableString greenSpannable = new SpannableString(nombre_final);
                    redSpannable.setSpan(new ForegroundColorSpan(Color.RED), (nombre_final.length() - disponible.length()), nombre_final.length(), 0);
                    greenSpannable.setSpan(new ForegroundColorSpan(Color.GREEN), (nombre_final.length() - disponible.length()), nombre_final.length(), 0);
                    if ((boolean)mesa.get("Disponible")) {
                        x.setText(greenSpannable);
                    } else {
                        x.setText(redSpannable);
                    }

                    x.callOnClick();
                    contenedor.addView(x);
                }
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });
    }

}
