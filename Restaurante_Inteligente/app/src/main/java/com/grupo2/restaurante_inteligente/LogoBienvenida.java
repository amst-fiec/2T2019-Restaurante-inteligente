package com.grupo2.restaurante_inteligente;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

public class LogoBienvenida extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logo_bienvenida);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(LogoBienvenida.this,MainActivity.class);
                startActivity(intent);
                finish();
            }
        },2500);
    }
}
