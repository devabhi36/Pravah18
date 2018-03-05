package com.example.abhinay.pravah;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;

public class YourTeam extends AppCompatActivity {

    TextView t1, t2, t3, t4, t5, t6, t7, t8, t9, t10, t11, t12;
    EditText mem;

    private class dataBundle {

        String email;
        private dataBundle ( String email ) {
            this.email = email;
        }
    }

    private class anotherDataBundle {
        String email, emails;
        private anotherDataBundle ( String email, String emails ) {
            this.email = email;
            this.emails = emails;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_your_team);

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

        mem = (EditText)findViewById(R.id.members);

        dataBundle d = new dataBundle(MainActivity._email);
        new AsyncGetCaller().execute(d);
    }

    private class AsyncGetCaller extends AsyncTask<dataBundle, Void, String> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected String doInBackground(dataBundle... d) {

            String email = d[0].email;

            try {

                String link = MainActivity.host + "team.php";
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

            if (result.contains("+")) {

                //Toast.makeText(getApplicationContext(), result, Toast.LENGTH_SHORT).show();

                String[][] names = decodeResult(result);

                t1.setText(names[0][0]);
                t2.setText(names[0][1]);
                t3.setText(names[0][2]);
                t4.setText(names[0][3]);
                t5.setText(names[0][4]);
                t6.setText(names[0][5]);
                t7.setText(names[0][6]);
                t8.setText(names[0][7]);
                t9.setText(names[0][8]);
                t10.setText(names[0][9]);
                t11.setText(names[0][10]);
                t12.setText(names[0][11]);

                if ( Integer.parseInt(names[1][0]) > 0 )
                {
                    if ( result.contains("%") ) {

                        String[] o = {"-1", "-1", "-1", "-1", "-1", "-1", "-1", "-1", "-1", "-1", "-1", "-1"};
                        String email_ = result.substring(result.indexOf("%")+1, result.length());
                        int count = 0;
                        int last = 0;
                        for (int i = 0; i < email_.length(); i++) {
                            if (email_.substring(i, i + 1).equals("$")) {
                                o[count] = email_.substring(last, i);
                                last = i + 1;
                                count++;
                            }
                        }

                        MainActivity.team = o;
                    }
                }

            }
            else {
                Toast.makeText(getApplicationContext(), result, Toast.LENGTH_SHORT).show();
            }
        }
    }


    private String [ ][ ] decodeResult( String result )
    {
        String[ ] a = { "", "", "", "", "", "", "", "", "", "", "", "" };
        int count = 0;
        int last = 0;
        if ( result.contains("%") )
        {
            result = result.substring(0, result.indexOf("%"));
        }

        for ( int i = 0; i < result.length(); i++ )
        {
            if ( result.substring(i, i+1).equals("+") )
            {
                a[count] = result.substring(last, i);
                last = i + 1;
                count++;
            }
        }

        String [ ] y = new String [1];
        y[ 0 ] = String.valueOf(count);

        String [ ][ ] x = new String[12][1];
        x[0] = a;
        x[1] = y;

        return x;
    }

    public void addMembers ( View theButton )
    {
        String emails = fixString ( mem.getText().toString() );
        if ( emails.length() > 0 && emails.contains(";") )
        {
            String prefix = getPrefix();

            anotherDataBundle bundle = new anotherDataBundle(MainActivity._email, prefix + emails);
            new AsyncSetCaller().execute(bundle);
        }
        else
        {
            Toast.makeText(getApplicationContext(), "Invalid format.", Toast.LENGTH_SHORT).show();
        }

    }

    private String getPrefix ( )
    {
        String prefix = "";
        for ( int i = 0; i < MainActivity.team.length; i++ )
        {
            if ( MainActivity.team[i].equals("-1") )
            {
                break;
            }
            else
            {
                prefix = prefix + ""+ MainActivity.team[i] + ";";
            }
        }

        return prefix;
    }

    private class AsyncSetCaller extends AsyncTask<anotherDataBundle, Void, String> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected String doInBackground(anotherDataBundle... d) {

            String email = d[0].email;
            String emails = d[0].emails;

            try {

                String link = MainActivity.host + "addteam.php";
                String data_ = URLEncoder.encode("email", "UTF-8") + "=" + URLEncoder.encode(email, "UTF-8") +"&"+ URLEncoder.encode("emails", "UTF-8") + "=" + URLEncoder.encode(emails, "UTF-8");
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

            Toast.makeText(getApplicationContext(), result.substring(result.indexOf("$")+1, result.length()), Toast.LENGTH_SHORT).show();

            String[ ] a = { t1.getText().toString(), t2.getText().toString(), t3.getText().toString(), t4.getText().toString(), t5.getText().toString(), t6.getText().toString(), t7.getText().toString(), t8.getText().toString(), t9.getText().toString(), t10.getText().toString(), t11.getText().toString(), t12.getText().toString() };

            String names = result.substring(result.indexOf("$")+1, result.length());
            if ( names.length() > 0 ) {
                int count = 0;
                int last = 0;

                for (int i = 0; i < names.length(); i++) {
                    if (names.substring(i, i + 1).equals("+")) {
                        a[count] = names.substring(last, i);
                        last = i + 1;
                        count++;
                    }
                }

                t1.setText(a[0]);
                t2.setText(a[1]);
                t3.setText(a[2]);
                t4.setText(a[3]);
                t5.setText(a[4]);
                t6.setText(a[5]);
                t7.setText(a[6]);
                t8.setText(a[7]);
                t9.setText(a[8]);
                t10.setText(a[9]);
                t11.setText(a[10]);
                t12.setText(a[11]);

            }
        }
    }

    private String fixString ( String g )
    {
        if ( !((g.substring(g.length()-1, g.length())).equals(";")) )
        {
            g = g + ";";
        }

        return g;
    }
}
