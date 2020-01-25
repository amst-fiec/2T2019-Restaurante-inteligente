package com.grupo2.restaurante_inteligente;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Menu extends AppCompatActivity {
    DatabaseReference db_reference;
    int contador = 0;
    HashMap<String, Object> hashMap = new HashMap<>();
    boolean valor = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        iniciarBaseDeDatos();
        escucharEButton();
    }

    public void iniciarBaseDeDatos(){
        db_reference = FirebaseDatabase.getInstance().getReference();
    }

    public void escucharEButton(){
        DatabaseReference registros = db_reference.child("Registros");
        registros.orderByKey().limitToLast(1).addValueEventListener (new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                contador++;
                for (DataSnapshot childDataSnapshot : dataSnapshot.getChildren()) {
                    hashMap = (HashMap<String, Object>) childDataSnapshot.getValue();
                    break;
                }
                if (contador == 2) {
                    consultarSilla(new Long((Long)hashMap.get("Mesa")).toString(),new Long((Long)hashMap.get("Silla")).toString());
                    DatabaseReference mesas = db_reference.child("Grupo").child("Mesa").child(new Long((Long)hashMap.get("Mesa")).toString());
                    DatabaseReference silla = mesas.child("Sillas").child(new Long((Long)hashMap.get("Silla")).toString());
                    if (valor) {
                        mesas.child("Disponible").setValue(false);
                        silla.child("Disponible").setValue(false);
                    } else {
                        mesas.child("Disponible").setValue(true);
                        silla.child("Disponible").setValue(true);
                    }
                    contador = 0;
                }
            }

            @Override
            public void onCancelled(DatabaseError error) {
                System.out.println(error.toException());
            }
        });
    }

    public boolean consultarSilla(String idMesa, String idSilla){
        DatabaseReference sillas = db_reference.child("Grupo").child("Mesa").child(idMesa).child("Sillas");
        sillas.child(idSilla).child("Disponible")
                .addValueEventListener (new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        if ((boolean)dataSnapshot.getValue()) {
                            valor =  true;
                        } else {
                            valor = false;
                        }
                    }

                    @Override
                    public void onCancelled(DatabaseError error) {
                        System.out.println(error.toException());
                    }
                });
        return valor;
    }


    public void verMesas (View view){
        Intent intent = new Intent(this, EstadoMesa.class);
        startActivity(intent);
    }

    public void verBateria (View view){
        Intent intent = new Intent(this, EstadoBateria.class);
        startActivity(intent);
    }

    /**
     * Se cierra al app, al ir de PerfilUsuario
     * @param view
     */
    public void verPerfil (View view){
        Intent intent = new Intent(this, PerfilUsuario.class);
        startActivity(intent);
    }

    /**
     * Todavia no esta implementado el cerrado de sesion, solo pasa
     * de activity
     *
     */
    public void cerrarSesion (View view){
        Intent intent = new Intent(this,MainActivity.class);
        startActivity(intent);

    }

    public void estadoRestaurante(View view){
        Intent intent = new Intent(this, EstadoRestaurante.class);
        startActivity(intent);

    }


}
