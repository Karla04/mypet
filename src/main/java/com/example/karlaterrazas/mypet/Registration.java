package com.example.karlaterrazas.mypet;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Registration extends AppCompatActivity implements View.OnClickListener {

    EditText et_nombre;
    EditText et_apellido_p;
    EditText et_apellido_m;
    EditText et_correo_e;
    EditText et_contraseña;
    EditText et_confirmar;
    Button btn_registrar;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        setTitle("Regístrate en MyPet");

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        et_nombre = (EditText) findViewById(R.id.et_nombre);
        et_apellido_p = (EditText) findViewById(R.id.et_apellido_p);
        et_apellido_m = (EditText) findViewById(R.id.et_apellido_m);
        et_correo_e = (EditText) findViewById(R.id.et_correo_e);
        et_contraseña = (EditText) findViewById(R.id.et_contraseña);
        et_confirmar = (EditText) findViewById(R.id.et_confirmar);
        btn_registrar = (Button) findViewById(R.id.btn_registrar);
        btn_registrar.setOnClickListener(this);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            // Respond to the action bar's Up/Home button
            case android.R.id.home:
                // onBackPressed();}
                Back();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void Back() {
        Intent intent = new Intent(Registration.this, StartActivity.class);
        startActivity(intent);
        finish();
    }

    public void onClick(View v) {
        if (v == btn_registrar)
            btn_registar_OnClick();
    };

    public void btn_registar_OnClick() {
        Intent welcome_intent;
        welcome_intent = new Intent(Registration.this, WelcomeActivity.class);
        String nombre = et_nombre.getText().toString();
        String apellido_paterno = et_apellido_p.getText().toString();
        String apellido_materno = et_apellido_m.getText().toString();
        String correo_electronico = et_correo_e.getText().toString();
        welcome_intent.putExtra("nombre", nombre);
        startActivity(welcome_intent);

        finish();
    }
}