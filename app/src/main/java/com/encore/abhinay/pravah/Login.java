package com.encore.abhinay.pravah;

import android.content.Context;
import android.content.Intent;
import android.net.wifi.p2p.WifiP2pManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.TimerTask;

public class Login extends AppCompatActivity {
    ImageView loader;
    Toolbar ltoolbar;
    public static int waiting = 0;

    String[ ] list = { "Collage Making", "Face Painting", "Mehandi Design", "Poster Making", "3D Rangoli/Painting", "T-shirt Painting", "", "", "", "Bandwars", "Fashion Jalwa", "Solo Dance", "Duet Dance", "Group Dance", "Mimicry/Standup Comedy", "Solo Singing", "Duet Singing", "Group Singing", "Skit/Play" };

    EditText reg_emial, reg_password;
    Button login;
    int c;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_login);
//        ltoolbar = (Toolbar) findViewById(R.id.ltoolbar);
//        setSupportActionBar(ltoolbar);
//        ltoolbar.setTitle("प्रवाह");
//        ltoolbar.setTitleTextColor(Color.parseColor("#ffffff"));
        reg_emial = (EditText)findViewById(R.id.reg_emial);
        reg_password = (EditText)findViewById(R.id.reg_password);
        login = (Button) findViewById(R.id.login);
    }

    private class dataBundle {

        String e, p;
        private dataBundle ( String e, String p ) {
            this.e = e;
            this.p = p;
        }
    }

    public void login(View view){

        if ( waiting == 0 ) {

            String vEmail = reg_emial.getText().toString();
            String vPassword = reg_password.getText().toString();

            if (vEmail.length() > 0 && vPassword.length() > 0) {

                if ((!vEmail.contains("@")) && (vEmail.length() < 3)) {
                    Toast.makeText(this, "Invalid email format! Enter: example@email.com", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (vPassword.length() < 8) {
                    Toast.makeText(this, "Password must be atleast 8 characters long.", Toast.LENGTH_SHORT).show();
                    return;
                }

                dataBundle bundle = new dataBundle(vEmail, vPassword);
                new AsyncCaller().execute(bundle);
            }
        }
    }

    private class AsyncCaller extends AsyncTask<dataBundle, Void, String> {

        @Override
        protected void onPreExecute() {

            View view = Login.this.getCurrentFocus();
            if (view != null) {
                InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
            }

            loader = (ImageView)findViewById(R.id.loader);
            loader.setImageResource(R.mipmap.loader);

            waiting = 1;
        }

        @Override
        protected String doInBackground(dataBundle... data) {
            String email = data[0].e;
            String password = data[0].p;

            try {

                String link = MainActivity.host + "login.php";
                String data_ = URLEncoder.encode("email", "UTF-8") + "=" + URLEncoder.encode(email, "UTF-8") + "&" + URLEncoder.encode("password", "UTF-8") + "=" + URLEncoder.encode(password, "UTF-8");
                URL url = new URL(link);
                URLConnection conn = url.openConnection();

                conn.setDoOutput(true);
                OutputStreamWriter wr = new OutputStreamWriter(conn.getOutputStream());

                wr.write(data_);
                wr.flush();

                BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));

                StringBuilder sb = new StringBuilder();
                String line = null;

                // Read Server Response
                while ((line = reader.readLine()) != null) {
                    sb.append(line);
                    break;
                }

                return sb.toString();
            } catch (Exception e) {
                return new String("Exception: " + e.getMessage());
            }
        }

        @Override
        protected void onPostExecute(String result) {

            if (result.equals("Couldn't find anyone registered with that email/password combination in our database.")) {
                Toast.makeText(getApplicationContext(), result, Toast.LENGTH_SHORT).show();
            }
            // Valid result
            else if (result.contains("+")) {
                String[] a = decodeResult(result);
                String[] e = decodeEvents(a[12]);

                MainActivity._name = a[0] + " " + a[1];
                MainActivity._dob = a[2];
                MainActivity._gender = a[3];
                MainActivity._email = a[4];
                MainActivity._mobile = "+91 " + a[5];
                MainActivity._fathername = a[6];
                MainActivity._mothername = a[7];
                MainActivity._aadhaar = a[8];
                MainActivity._zone = a[9];
                MainActivity._college = a[10];
                MainActivity._rollno = a[11];
                MainActivity._events1 = returnEvent(e[0]);
                MainActivity._events2 = returnEvent(e[1]);
                MainActivity._events3 = returnEvent(e[2]);
                MainActivity._youare = a[13];
                MainActivity._tshirt = a[14];
                MainActivity._transactionID = a[15];
                MainActivity._transactionStatus = a[16];

                Toast.makeText(getApplicationContext(), "Signed In, view your details from the Dashboard.", Toast.LENGTH_SHORT).show();

                MainActivity.flag = 1;
                MainActivity.new_flag = 1;

                Intent intent = new Intent(Login.this, MainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);

            } else {
                Toast.makeText(getApplicationContext(), "Unable to connect to server, check your internet connection.", Toast.LENGTH_SHORT).show();

            }

            waiting = 0;
            loader.setVisibility(View.INVISIBLE);
        }
    }

    private String returnEvent ( String e )
    {
        if ( !e.equals("-1") )
        {
            return list[Integer.parseInt(e)-1];
        }
        else {
            return "";
        }
    }

    private String [ ] decodeEvents ( String events )
    {
        String[ ] e = new String[3];
        int count = 0;
        int last = 0;
        for ( int i = 0; i < events.length(); i++ )
        {
            if ( events.substring(i, i+1).equals(";") )
            {
                if ( !events.substring(last, i).equals("-") ) {
                    e[count] = events.substring(last, i);
                } else
                {
                    e[count] = "-1";
                }
                last = i + 1;
                count++;
            }
        }

        return e;
    }

    private String [ ] decodeResult( String result )
    {
        String[ ] a = new String[17];
        int count = 0;
        int last = 0;
        for ( int i = 0; i < result.length(); i++ ) {
            if (result.substring(i, i + 1).equals("+")) {
                a[count] = result.substring(last, i);
                last = i + 1;
                count++;
            }
        }

        return a;
    }

    private class smallBundle {
        String e;
        private smallBundle ( String e ) { this.e = e; }
    }

    public void forgot ( View v )
    {
        if ( waiting == 0 ) {

            String vEmail = reg_emial.getText().toString();
            if ( vEmail.length() > 0 ){

                if (( !vEmail.contains("@") ) && (vEmail.length() < 3)) {
                    Toast.makeText(getApplicationContext(), "Invalid email format! Enter: example@email.com", Toast.LENGTH_SHORT).show();
                    return;
                }

                smallBundle bundle = new smallBundle(vEmail);
                new phpCaller().execute(bundle);
            }
        }
    }

    private class phpCaller extends AsyncTask<smallBundle, Void, String> {

        @Override
        protected void onPreExecute() {

            View view = Login.this.getCurrentFocus();
            if (view != null) {
                InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
            }

            loader = (ImageView)findViewById(R.id.loader);
            loader.setImageResource(R.mipmap.loader);

            waiting = 1;

        }

        @Override
        protected String doInBackground(smallBundle... data) {
            String email = data[0].e;

             try {

                 String link = MainActivity.host + "forgot.php";
                 String data_ = URLEncoder.encode("email", "UTF-8") + "=" + URLEncoder.encode(email, "UTF-8");
                 URL url = new URL(link);
                 URLConnection conn = url.openConnection();

                 conn.setDoOutput(true);
                 OutputStreamWriter wr = new OutputStreamWriter(conn.getOutputStream());

                 wr.write(data_);
                 wr.flush();

                 BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));

                 StringBuilder sb = new StringBuilder();
                 String line = null;

                 // Read Server Response
                 while ((line = reader.readLine()) != null) {
                     sb.append(line);
                     break;
                 }

                 return sb.toString();
             } catch (Exception e) {
                 return new String("Exception: " + e.getMessage());
             }
        }

        @Override
        protected void onPostExecute(String result) {

            waiting = 0;
            loader.setVisibility(View.INVISIBLE);

            Toast.makeText(getApplicationContext(), result, Toast.LENGTH_SHORT).show();
        }
    }
}
