package com.example.abhinay.pravah;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;

/**
 * Created by Harshal Dev on 03-03-2018.
 */

public class Team extends AppCompatActivity {

    TextView t1, t2, t3, t4, t5, t6, t7, t8, t9, t10, t11, t12;

    private class dataBundle {

        String email;
        private dataBundle ( String email ) {
            this.email = email;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.team);

        t1 = (TextView)findViewById(R.id.t1);
        t2 = (TextView)findViewById(R.id.t2);
        t3 = (TextView)findViewById(R.id.t3);
        t4 = (TextView)findViewById(R.id.t4);
        t5 = (TextView)findViewById(R.id.t5);
        t6 = (TextView)findViewById(R.id.t6);
        t7 = (TextView)findViewById(R.id.t7);
        t8 = (TextView)findViewById(R.id.t8);
        t9 = (TextView)findViewById(R.id.t9);
        t10 = (TextView)findViewById(R.id.t10);
        t11 = (TextView)findViewById(R.id.t11);
        t12 = (TextView)findViewById(R.id.t12);

        //dataBundle d = new dataBundle(MainActivity._email);
        //new AsyncCaller().execute(d);
    }

    private class AsyncCaller extends AsyncTask<dataBundle, Void, String> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected String doInBackground(dataBundle... d) {

            String email = d[0].email;

            try {

                String link = "http://192.168.43.239/team.php";
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

            Toast.makeText(getApplicationContext(), result, Toast.LENGTH_SHORT).show();
        }
    }
}