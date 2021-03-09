package com.rodrigojbarrera.restaurante;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Home extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

    }

    public void onClickButtonMozo(View view){
        Intent intentMozo = new Intent(this, Mozo.class);
        startActivity(intentMozo);
    }

    public void onClickButtonCocina(View view){
        Intent intentCocina = new Intent(this, Cocina.class);
        startActivity(intentCocina);
    }

}