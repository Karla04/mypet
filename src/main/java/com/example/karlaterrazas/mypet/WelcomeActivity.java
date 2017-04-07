package com.example.karlaterrazas.mypet;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class WelcomeActivity extends AppCompatActivity implements View.OnClickListener {

    ImageView iv_usuario;
    TextView tv_bienvenido;
    Button btn_registra;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        getSupportActionBar().hide();

        iv_usuario = (ImageView) findViewById(R.id.iv_usuario);
        tv_bienvenido = (TextView) findViewById(R.id.tv_bienvenido);
        btn_registra = (Button) findViewById(R.id.btn_registra);
        btn_registra.setOnClickListener(this);

        Intent intent=getIntent();
        Bundle extras = intent.getExtras();
        if (extras != null) {//ver si contiene datos
            String nombre = (String)extras.get("nombre");//Obtengo el nombre
            tv_bienvenido.setText("Hola " +nombre);
        }
    }

    public void onClick(View v) {
        if (v == btn_registra)
            btn_regista_OnClick();
    }

    public void btn_regista_OnClick() {
        Intent intent = new Intent(WelcomeActivity.this, PetActivity.class);
        startActivity(intent);
        finish();
    }

}