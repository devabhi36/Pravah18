package com.iet.abhinay.pravah;

import android.content.Context;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;

public class YourTeam extends AppCompatActivity {
    public static int waiting = 0;
    ImageView loader, loader2;

    TextView t1, t2, t3, t4, t5, t6, t7, t8, t9, t10, t11, t12;
    EditText mem;
    Toolbar ttoolbar;

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
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        //        ttoolbar = (Toolbar) findViewById(R.id.ttoolbar);
//        ttoolbar.setTitle("प्रवाह");
//        ttoolbar.setTitleTextColor(Color.parseColor("#ffffff"));
        MainActivity.flag = 0;

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

            View view = YourTeam.this.getCurrentFocus();
            if (view != null) {
                InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
            }

            loader = (ImageView)findViewById(R.id.loader);
            loader.setImageResource(R.mipmap.loader);
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

                String[] o = {"-1", "-1", "-1", "-1", "-1", "-1", "-1", "-1", "-1", "-1", "-1", "-1"};
                MainActivity.team = o;
                if ( Integer.parseInt(names[1][0]) > 0 )
                {
                    if ( result.contains("%") ) {

                        String email_ = result.substring(result.indexOf("%")+1, result.length());
                        int count = 0;
                        int last = 0;
                        for (int i = 0; ( i < email_.length() ) && ( count < 12 ); i++) {
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
            else
            {
                t1.setText("No Team Members.");
            }

            loader.setVisibility(View.INVISIBLE);
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

        for ( int i = 0; ( i < result.length() ) && ( count < 12 ); i++ )
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
        if ( waiting == 0 ) {

            String emails = fixString(mem.getText().toString());
            if ( !emails.equals(";") ) {

                if (emails.length() > 0 && emails.contains(";")) {
                    String prefix = getPrefix();

                    if ( !checkRepeat( emails ) ) {

                        anotherDataBundle bundle = new anotherDataBundle(MainActivity._email, prefix + emails);
                        updateTeam(prefix + emails);
                        new AsyncSetCaller().execute(bundle);
                    }
                    else
                    {
                        Toast.makeText(getApplicationContext(), "Team member already added.", Toast.LENGTH_SHORT).show();
                    }
                }
                else {
                    Toast.makeText(getApplicationContext(), "Invalid format.", Toast.LENGTH_SHORT).show();
                }
            }
            else
            {
                Toast.makeText(getApplicationContext(), "No email ID entered.", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void updateTeam ( String team )
    {
        String[ ] o = { "-1", "-1", "-1", "-1", "-1", "-1", "-1", "-1", "-1", "-1", "-1", "-1" };

        int count = 0;
        int last = 0;
        for (int i = 0; i < team.length() && count < 12; i++) {
            if (team.substring(i, i + 1).equals(";")) {
                o[count] = team.substring(last, i);
                last = i + 1;
                count++;
            }
        }

        MainActivity.team = o;
    }

    private boolean checkRepeat( String emails )
    {
        boolean repeat = false;

        int count = 0;
        int last = 0;
        for ( int i = 0; i < emails.length() && count < 12; i++ )
        {
            if ( emails.substring(i, i+1).equals(";") ) {
                for (int j = 0; j < 12; j++)
                {
                    if ( MainActivity.team[j].equals(emails.substring(last, i)) ) {

                        repeat = true;
                        break;
                    }
                }

                last = i + 1;
                count++;
            }
        }

        return repeat;
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

            View view = YourTeam.this.getCurrentFocus();
            if (view != null) {
                InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
            }

            loader2 = (ImageView)findViewById(R.id.loader2);
            loader2.setImageResource(R.mipmap.loader);

            waiting = 1;
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

                loader2.setVisibility(View.INVISIBLE);
                waiting = 0;

                Toast.makeText(getApplicationContext(), result.substring(0, result.indexOf("$")), Toast.LENGTH_LONG).show();
                mem.setText("");
            }
        }
    }

    private String fixString ( String g )
    {
        if ( g.length() == 0 ) {

            return g + ";";
        }

        if ( !((g.substring(g.length()-1, g.length())).equals(";")) )
        {
            g = g + ";";
        }

        return g;
    }
}
