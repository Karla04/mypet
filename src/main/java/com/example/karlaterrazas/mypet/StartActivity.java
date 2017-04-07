package com.example.karlaterrazas.mypet;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class StartActivity extends AppCompatActivity implements View.OnClickListener{

    ImageView iv_logo;
    Button btn_registrarse;
    Button btn_iniciar_sesion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        getSupportActionBar().hide();

        iv_logo = (ImageView) findViewById(R.id.iv_logo);
        btn_registrarse = (Button) findViewById(R.id.btn_registrarse);
        btn_registrarse.setOnClickListener(this);
        btn_iniciar_sesion = (Button) findViewById(R.id.btn_iniciar_sesion);
        btn_iniciar_sesion.setOnClickListener(this);
    }

    public void onClick (View v){
        if(v==btn_registrarse)
            btn_registrarse_OnClick();
        else if(v==btn_iniciar_sesion)
            btn_iniciar_sesion_OnClick();
    }

    public void btn_registrarse_OnClick(){
        Intent intent = new Intent(StartActivity.this,Registration.class);
        startActivity(intent);
        finish();
    }

    public void btn_iniciar_sesion_OnClick(){
        Intent intent = new Intent(StartActivity.this,LoginActivity.class);
        startActivity(intent);
        finish();
    }
}