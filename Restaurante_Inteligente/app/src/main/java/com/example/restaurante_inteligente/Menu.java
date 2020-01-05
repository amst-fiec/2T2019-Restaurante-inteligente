package com.example.restaurante_inteligente;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class Menu extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
    }


    public void verMesas (View view){
        Intent intent = new Intent(this, EstadoMesa.class);
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

    public void verBateria (View view){
        //Intent intent = new Intent(this, Bateria.class);
        //startActivity(intent);
    }
}
