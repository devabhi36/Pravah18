package com.example.abhinay.pravah;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.text.DateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class Login extends AppCompatActivity {
    EditText reg_email, reg_password;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_login);

        reg_email = (EditText)findViewById(R.id.reg_email);
        reg_password = (EditText)findViewById(R.id.reg_password);
    }

    public void login(View view){

        String valueEmail = reg_email.getText().toString();
        String valuePassword = reg_password.getText().toString();

        if ( ( valueEmail.length() ) > 0 && ( valuePassword.length() > 0 ) ) {

            if ( !valueEmail.contains("@") && ( valueEmail.length() < 3 ) ) {
                Toast.makeText(this, "Invalid email format!", Toast.LENGTH_SHORT).show();
                return;
            }
            
            if ( valuePassword.length() < 8 ) {
                Toast.makeText(this, "Password must be atleast 8 characters long!", Toast.LENGTH_SHORT).show();
                return;
            }

            dataBundle bundle = new dataBundle(valueEmail, valuePassword);
            new AsyncCaller().execute(bundle);
        }
        else {
            Toast.makeText(this, "Both fields mandatory.", Toast.LENGTH_SHORT).show();
        }
    }

    private class dataBundle {
        String e, p;

        private dataBundle( String e, String p ) {
            this.e = e;
            this.p = p;
        }
    }

    private class AsyncCaller extends AsyncTask<dataBundle, Void, String> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected String doInBackground(dataBundle... data) {
            String email = data[0].e;
            String password = data[0].p;

            try{

                String link = "http://192.168.43.239/login.php";
                String data_  = URLEncoder.encode("email", "UTF-8") + "=" + URLEncoder.encode(email, "UTF-8") +"&"+ URLEncoder.encode("password", "UTF-8") + "=" + URLEncoder.encode(password, "UTF-8");

                URL url = new URL(link);
                URLConnection conn = url.openConnection();

                conn.setDoOutput(true);
                OutputStreamWriter wr = new OutputStreamWriter(conn.getOutputStream());

                wr.write( data_ );
                wr.flush();

                BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));

                StringBuilder sb = new StringBuilder();
                String line = null;

                // Read Server Response
                while((line = reader.readLine()) != null) {
                    sb.append(line);
                    break;
                }

                return sb.toString();
            }
            catch(Exception e) {
                return new String("Exception: " + e.getMessage());
            }
        }

        @Override
        protected void onPostExecute(String result) {

            Toast.makeText(getApplicationContext(), result, Toast.LENGTH_SHORT).show();
        }
    }
}