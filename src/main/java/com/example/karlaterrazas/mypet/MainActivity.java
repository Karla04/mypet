package com.example.karlaterrazas.mypet;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, ProfileFragment.OnFragmentInteractionListener,
        DatesFragment.OnFragmentInteractionListener, HistoryFragment.OnFragmentInteractionListener,
        MessagesFragment.OnFragmentInteractionListener, TelephonesFragment.OnFragmentInteractionListener,
        VetFragment.OnFragmentInteractionListener{

    TextView tv_user;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        setTitle("Inicio");

        tv_user = (TextView) findViewById(R.id.tv_user);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(

                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        }

    @Override
    public void onBackPressed() {
        MessageButtonOkCancel("MyPet", "¿Desea salir de la aplicación?", "Aceptar", "Cancelar");
        }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        menu.add(menu.NONE,100,menu.NONE,"Salir");
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        switch (id) {
            case 100:
                onBackPressed();
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        Fragment fragment = null;
        Boolean FragmentoSeleccionado = false;
            // Handle the camera action
        if (id == R.id.nav_profile) {

            Intent intent = new Intent(this, ProfileActivity.class);
            startActivity(intent);
//            Intent i = new Intent(this, Activity.class);
//            startActivity(i);
            finish();

        } else if (id == R.id.nav_dates) {

            Intent intent = new Intent(this, DatesActivity.class);
            startActivity(intent);
            finish();

        } else if (id == R.id.nav_history) {

            Intent intent = new Intent(this, HistoryActivity.class);
            startActivity(intent);
            finish();

        } else if (id == R.id.nav_messages) {

            fragment = new MessagesFragment();
            FragmentoSeleccionado = true;
            setTitle("Mensajes");

        } else if (id == R.id.nav_telefonos) {

            Intent intent = new Intent(this, TelephonesActivity.class);
            startActivity(intent);
            finish();

        } else if (id == R.id.nav_vet) {

            Intent intent = new Intent(this, VetActivity.class);
            startActivity(intent);
            finish();

        }else if (id == R.id.nav_log_out){

            Intent intent = new Intent(MainActivity.this, LoginActivity.class);
            startActivity(intent);
            finish();

        } if (FragmentoSeleccionado){
            getSupportFragmentManager().beginTransaction().replace(R.id.Contenedor, fragment).commit();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }

    public void MessageButtonOkCancel(String title, String message, String aceptText, String cancelText){
        AlertDialog.Builder alertDialog= new AlertDialog.Builder(this);
        alertDialog.setTitle(title);
        alertDialog.setMessage(message);
        alertDialog.setCancelable(false);
        alertDialog.setPositiveButton(aceptText, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface alertDialog, int id) {
                finish();
            }
        });

        alertDialog.setNegativeButton(cancelText, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface alertDialog, int id) {

            }
        });
        alertDialog.show();
    }


}
