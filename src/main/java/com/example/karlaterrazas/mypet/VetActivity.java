package com.example.karlaterrazas.mypet;

import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class VetActivity extends AppCompatActivity {

    private ListView lv_vet_lis;
    private ArrayAdapter adapter;
    private String getInfoVetURL = "http://130.100.4.87/mypet/getInfoVet.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vet);

        setTitle("Información del veterinario");

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder().permitNetwork().build());

        lv_vet_lis = (ListView) findViewById(R.id.lv_vet_list);
        adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1);
        lv_vet_lis.setAdapter(adapter);

        webServiceRest(getInfoVetURL);
    }


    private void webServiceRest(String requestURL) {
        try {
            URL url = new URL(requestURL);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String line = "";
            String webServiceResult = "";
            while ((line = bufferedReader.readLine()) != null) {
                webServiceResult += line;
            }
            bufferedReader.close();
            parseInformation(webServiceResult);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void parseInformation(String jsonResult) {
        JSONArray jsonArray = null;
        String id_veterinario;
        String nombre;
        String apellido_pat;
        String apellido_mat;
        String email;
        String cedula;
        String telefono_clinica;
        String telefono_emergencia;
        String telefono_personal;
        String id_mascota;
        try {
            jsonArray = new JSONArray(jsonResult);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        for (int i = 0; i < jsonArray.length(); i++) {
            try {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                id_veterinario = jsonObject.getString("id_veterinario");
                nombre = jsonObject.getString("nombre");
                apellido_pat = jsonObject.getString("apellido_pat");
                apellido_mat = jsonObject.getString("apellido_mat");
                email = jsonObject.getString("email");
                cedula = jsonObject.getString("cedula");
                telefono_clinica = jsonObject.getString("telefono_clinica");
                telefono_emergencia = jsonObject.getString("telefono_emergencia");
                telefono_personal = jsonObject.getString("telefono_personal");
                id_mascota = jsonObject.getString("id.mascota");
                adapter.add("" + nombre + "" + apellido_pat + " " + apellido_mat + "Email:\n " + email
                        + "Cédula Profesional:\n " + cedula + "Teléfono Clinica:\n " + telefono_clinica
                        + "Teléno Emergencias:\n " + telefono_emergencia + "Teléfono Personal:\n " + telefono_personal);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
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
        Intent intent = new Intent(VetActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }
}