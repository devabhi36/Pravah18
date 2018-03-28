package com.iet.abhinay.pravah;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

public class Notifications extends AppCompatActivity {
    TextView title, message;
    ImageView loader;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notifications);
        title = (TextView) findViewById(R.id.ntitle);
        message = (TextView) findViewById(R.id.nmessage);

        new notificationCaller().execute();
    }

    private class notificationCaller extends AsyncTask<Void, Void, String> {

        @Override
        protected void onPreExecute() {

            loader = (ImageView)findViewById(R.id.loader);
            loader.setImageResource(R.mipmap.loader);
        }

        @Override
        protected String doInBackground(Void... v) {

            try {

                String link = MainActivity.host + "notify.php";

                URL url = new URL(link);
                URLConnection conn = url.openConnection();

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

            //Toast.makeText(getApplicationContext(), result, Toast.LENGTH_SHORT).show();
            title.setText(result.substring(0, result.indexOf("+")));
            message.setText(result.substring(result.indexOf("+")+1, result.length()));

            loader.setVisibility(View.INVISIBLE);
        }
    }
}
