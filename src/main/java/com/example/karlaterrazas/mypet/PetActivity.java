package com.example.karlaterrazas.mypet;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

public class PetActivity extends AppCompatActivity implements View.OnClickListener  {

    EditText et_nombre;
    TextView tv_sexo;
    RadioButton rb_hembra;
    RadioButton rb_macho;
    EditText et_fecha;
    Button btn_registro;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pet);

        setTitle("Regístra a tu mascota");

        et_nombre = (EditText) findViewById(R.id.et_nombre);
        tv_sexo = (TextView) findViewById(R.id.tv_sexo);
        rb_hembra = (RadioButton) findViewById(R.id.rb_hembra);
        rb_macho = (RadioButton) findViewById(R.id.rb_macho);
        et_fecha = (EditText) findViewById(R.id.et_fecha);
        btn_registro = (Button) findViewById(R.id.btn_registro);
        btn_registro.setOnClickListener(this);
    }

    public void onClick(View v) {
        if (v == btn_registro)
            btn_registro_OnClick();
    }

    public void btn_registro_OnClick() {
        Intent intent = new Intent(PetActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }

}