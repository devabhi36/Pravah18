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
import android.support.v4.app.FragmentTransaction;
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
    EditText reg_emial, reg_password;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_login);
        reg_emial = (EditText)findViewById(R.id.reg_emial);
        reg_password = (EditText)findViewById(R.id.reg_password);
    }

    public void login(View view){
        NavigationView navigationView= (NavigationView)view.findViewById(R.id.nav_view);
        Menu menuNav=navigationView.getMenu();

        MenuItem dash = menuNav.findItem(R.id.dashboard);
        dash.setVisible(true);

        Intent intent = new Intent(Login.this, MainActivity.class);
        startActivity(intent);
    }
}
