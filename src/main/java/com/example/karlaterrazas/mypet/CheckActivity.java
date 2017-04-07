package com.example.karlaterrazas.mypet;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class CheckActivity extends AppCompatActivity implements View.OnClickListener{

    TextView tv_text1;
    EditText et_codigo;
    Button btn_enviar1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check);

        setTitle("Recuperar contraseña");

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        tv_text1 = (TextView) findViewById(R.id.tv_text1);
        et_codigo = (EditText) findViewById(R.id.et_codigo);
        btn_enviar1 = (Button) findViewById(R.id.btn_enviar1);
        btn_enviar1.setOnClickListener(this);
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
        Intent intent = new Intent(CheckActivity.this, RecoveryActivity.class);
        startActivity(intent);
        finish();
    }

    public void onClick(View v) {
        if (v == btn_enviar1)
            btn_enviar1_OnClick();
    }

    public void btn_enviar1_OnClick() {
        Intent intent = new Intent(CheckActivity.this, PasswordActivity.class);
        startActivity(intent);
        finish();
    }
}