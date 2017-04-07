package com.example.karlaterrazas.mypet;

import android.content.Intent;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.app.LoaderManager.LoaderCallbacks;

import android.content.Loader;
import android.database.Cursor;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity implements LoaderCallbacks<Cursor>, OnClickListener {

    /**
     * Credenciales de pruebas
     * TODO: remuévelas cuando vayas a implementar una autenticación real.
     */
    private static final String DUMMY_EMAIL = "utec@gmail.com";
    private static final String DUMMY_PASSWORD = "mypet";

    /**
     * Keep track of the login task to ensure we can cancel it if requested.
     */
    //Simula una espera de n milsegundos la petición del servidor.
    private UserLoginTask mAuthTask = null; //Comprueba que no se hayan enviado datos aún

    // UI references.
    private ImageView mLogoView;
    private EditText mEmailView;
    private EditText mPasswordView;
    private TextInputLayout mFloatLabelEmail;
    private TextInputLayout mFloatLabelPassword;
    private View mProgressView;
    private View mLoginFormView;
    Button btn_olvidaste;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //Referencias de los views declarados
        mLogoView = (ImageView) findViewById(R.id.image_logo);
        mEmailView = (EditText) findViewById(R.id.email);
        mPasswordView = (EditText) findViewById(R.id.password);
        mFloatLabelEmail = (TextInputLayout) findViewById(R.id.float_label_email);
        mFloatLabelPassword = (TextInputLayout) findViewById(R.id.float_label_password);
        Button mSignInButton = (Button) findViewById(R.id.email_sign_in_button);
        btn_olvidaste = (Button) findViewById(R.id.btn_olvidaste);
        btn_olvidaste.setOnClickListener(this);
        mLoginFormView = findViewById(R.id.login_form);
        mProgressView = findViewById(R.id.login_progress);


        // Edición del campo de texto para password
        mPasswordView.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int id, KeyEvent keyEvent) {
                if (id == R.id.login || id == EditorInfo.IME_NULL) {
                    attemptLogin();
                    return true;
                }
                return false;
            }
        });

        mSignInButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                attemptLogin();
                startActivity(new Intent(LoginActivity.this, MainActivity.class));
                finish();
            }
        });
    }

    private void attemptLogin() {
        if (mAuthTask != null) {
            return;
        }

        // Reset errors. (Restablece errores)
        mFloatLabelEmail.setError(null);
        mFloatLabelPassword.setError(null);

        // Store values at the time of the login attempt. (Obtiene los datos del usuario como variables)
        String email = mEmailView.getText().toString();
        String password = mPasswordView.getText().toString();

        boolean cancel = false;
        View focusView = null;

        // Check for a valid password, if the user entered one.
        if (TextUtils.isEmpty(password)) {
            mFloatLabelPassword.setError(getString(R.string.error_field_required));
            focusView = mFloatLabelPassword;
            cancel = true;
        } else if (!isPasswordValid(password)) {
            mFloatLabelPassword.setError(getString(R.string.error_invalid_password));
            focusView = mFloatLabelPassword;
            cancel = true;
        }

        // Verificar si el campo tiene contenido.
        if (TextUtils.isEmpty(email)) {
            mFloatLabelEmail.setError(getString(R.string.error_field_required));
            focusView = mFloatLabelEmail;
            cancel = true;
        } else if (!isEmailValid(email)) {
            mFloatLabelEmail.setError(getString(R.string.error_invalid_email));
            focusView = mFloatLabelEmail;
            cancel = true;
        }

        if (cancel) {
            // There was an error; don't attempt login and focus the first
            // form field with an error.
            focusView.requestFocus();
        } else {
            // Show a progress spinner, and kick off a background task to
            // perform the user login attempt. (Sincronia)
            showProgress(true);
            mAuthTask = new UserLoginTask(email, password);
            mAuthTask.execute((Void) null);
        }

    }

    private void showProgress(boolean show) {
        //Muestra la barra de progreso
        mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
        //Oculta el formulario
        int visibility = show ? View.GONE : View.VISIBLE;
        mLogoView.setVisibility(visibility);
        mLoginFormView.setVisibility(visibility);
    }

    private boolean isEmailValid(String email) {
        return email.length() == 30; //Tamaño exactamente igual a 30
    }

    private boolean isPasswordValid(String password) {
        return password.length() > 4; //Tamaño no inferior o igual a 4
    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        return null;
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {

    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {

    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent(LoginActivity.this, RecoveryActivity.class);
        startActivity(intent);
        finish();
    }

    private class UserLoginTask extends AsyncTask<Void, Void, Integer> {
        private final String mEmail;
        private final String mPassword;

        UserLoginTask(String email, String password) {
            mEmail = email;
            mPassword = password;
        }

        @Override

        protected Integer doInBackground(Void... params) {

            try {
                // Simulate network access.
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                return 4;
            }

            if (!mEmail.equals(DUMMY_EMAIL)) {
                return 1;
            }

            if (!mPassword.equals(DUMMY_PASSWORD)) {
                return 2;
            }

            return 1;

        }

        @Override
        protected void onPostExecute(final Integer success) {
            mAuthTask = null;
            showProgress(false);

            switch (success) {
                case 1:
                    showAppointmentsScreen();
                    break;
                case 2:
                    showLoginError("Correo electrónico o contraseña inválidos");
                    break;
            }
        }

        private void showAppointmentsScreen() {
        }

        private void showLoginError(String error) {
            Toast.makeText(LoginActivity.this, error, Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            // Respond to the action bar's Up/Home button
            case android.R.id.home:
                //onBackPressed();}
                Back();
            return true;
        }
        return super.onOptionsItemSelected(item);
    };

    public void Back(){
        Intent intent=new Intent(LoginActivity.this,StartActivity.class);
        startActivity(intent);
        finish();
        }
    }


//
//import android.content.Intent;
//import android.os.Bundle;
//import android.support.v7.app.AppCompatActivity;
//import android.view.MenuItem;
//import android.view.View;
//import android.widget.Button;
//import android.widget.EditText;
//import android.widget.TextView;
//
//public class LoginActivity extends AppCompatActivity implements View.OnClickListener {
//
//    EditText et_correo_electronico;
//    EditText et_password;
//    TextView tv_log_in;
//    Button btn_iniciar_s;
//    Button btn_olvidaste;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_login);
//
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
//
//        et_correo_electronico = (EditText) findViewById(R.id.et_correo_electronico);
//        et_password = (EditText) findViewById(R.id.et_password);
//        tv_log_in = (TextView) findViewById(R.id.tv_log_in);
//        btn_iniciar_s = (Button) findViewById(R.id.btn_iniciar_s);
//        btn_iniciar_s.setOnClickListener(this);
//        btn_olvidaste = (Button) findViewById(R.id.btn_olvidaste);
//        btn_olvidaste.setOnClickListener(this);
//    }
//
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        switch (item.getItemId()) {
//            // Respond to the action bar's Up/Home button
//            case android.R.id.home:
//                // onBackPressed();}
//                Back();
//                return true;
//        }
//        return super.onOptionsItemSelected(item);
//    }
//
//    public void Back(){
//        Intent intent = new Intent(LoginActivity.this, StartActivity.class);
//        startActivity(intent);
//        finish();
//    }
//
//    public void onClick(View v) {
//        if (v == btn_olvidaste)
//            btn_olvidaste_OnClcik();
//        else if (v == btn_iniciar_s)
//            btn_iniciar_s_OnClick();
//    }
//
//    public void btn_olvidaste_OnClcik() {
//        Intent intent = new Intent(LoginActivity.this, RecoveryActivity.class);
//        startActivity(intent);
//        finish();
//    }
//
//    public void btn_iniciar_s_OnClick() {
//        if (true) {
//            startActivity(new Intent(LoginActivity.this, MainActivity.class));
//            finish();
//            return;
//        }
//    }
//}
