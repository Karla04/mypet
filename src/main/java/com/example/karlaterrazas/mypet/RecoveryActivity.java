package com.example.karlaterrazas.mypet;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class RecoveryActivity extends AppCompatActivity implements View.OnClickListener{

    EditText et_ingresa;
    Button btn_enviar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recovery);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        setTitle("Recuperar contraseña");

        et_ingresa = (EditText) findViewById(R.id.et_ingresa);
        btn_enviar = (Button) findViewById(R.id.btn_enviar);
        btn_enviar.setOnClickListener(this);
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

    public void Back(){
        Intent intent = new Intent(RecoveryActivity.this, LoginActivity.class);
        startActivity(intent);
        finish();
    }

    public void onClick(View v) {
        if (v == btn_enviar)
            btn_enviar_OnClcik();
    }

    public void btn_enviar_OnClcik() {
        Intent intent = new Intent(RecoveryActivity.this, CheckActivity.class);
        startActivity(intent);
        finish();
    }
}