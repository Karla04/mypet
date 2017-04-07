package com.example.karlaterrazas.mypet;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class PasswordActivity extends AppCompatActivity implements View.OnClickListener {

    TextView tv_ingresa1;
    EditText et_nueva;
    EditText et_confirma;
    Button btn_aceptar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_password);

        setTitle("Recuperar contraseña");

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        tv_ingresa1 = (TextView) findViewById(R.id.tv_ingresa1);
        et_nueva = (EditText) findViewById(R.id.et_nueva);
        et_confirma = (EditText) findViewById(R.id.et_confirma);
        btn_aceptar = (Button) findViewById(R.id.btn_aceptar);
        btn_aceptar.setOnClickListener(this);
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
        Intent intent = new Intent(PasswordActivity.this, CheckActivity.class);
        startActivity(intent);
        finish();
    }

    public void onClick(View v) {
        if (v == btn_aceptar)
            btn_aceptar_OnClick();
    }

    public void btn_aceptar_OnClick() {
        Intent intent = new Intent(PasswordActivity.this, StartActivity.class);
        startActivity(intent);
        finish();
    }
}